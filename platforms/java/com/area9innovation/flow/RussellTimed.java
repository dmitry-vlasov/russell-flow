package com.area9innovation.flow;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadFactory;


public class RussellTimed extends NativeHost {
	static private class TaskTimer implements Runnable {
		private long time_limit;
		private Thread controlled;
		private volatile boolean cancelled = false;
		private TaskTimer(long time_limit, Thread controlled) {
			this.time_limit = time_limit;
			this.controlled = controlled;
		}
		public void cancel() {
			cancelled = true;
		}
		@Override
		public void run() {
			if (!cancelled && time_limit > 0 && System.currentTimeMillis() > time_limit) {
				controlled.interrupt();
			}
		}
	}

	// DAEMON worker threads: the JVM exits when the last NON-daemon thread ends,
	// and these pools are never shut down explicitly - so with default (non-daemon)
	// workers an UNCAUGHT exception killing main left the JVM alive forever at 0%
	// CPU, with the crash text trapped in the block-buffered stdout: a crash
	// masquerading as a silent hang with empty output. The pool tasks are pure
	// computation (no graceful drain needed), so daemon workers are safe: when
	// main dies, the process exits and the exception is printed.
	private static ThreadFactory daemonFactory(String name) {
		final java.util.concurrent.atomic.AtomicInteger n = new java.util.concurrent.atomic.AtomicInteger(0);
		return r -> {
			Thread t = new Thread(r, name + "-" + n.incrementAndGet());
			t.setDaemon(true);
			return t;
		};
	}

	private static ConcurrentHashMap<Long, ScheduledExecutorService> timers =
		new ConcurrentHashMap<Long, ScheduledExecutorService>();

	private static ScheduledExecutorService getTimer() {
		long thread_id = Thread.currentThread().getId();
		if (!timers.containsKey(thread_id)) {
			timers.put(thread_id, Executors.newSingleThreadScheduledExecutor(daemonFactory("russell-timer")));
		}
		return timers.get(thread_id);
	}

	private static ConcurrentHashMap<Long, ExecutorService> executors =
		new ConcurrentHashMap<Long, ExecutorService>();

	private static ExecutorService getExecutor() {
		long thread_id = Thread.currentThread().getId();
		if (!executors.containsKey(thread_id)) {
			executors.put(thread_id, Executors.newSingleThreadExecutor(daemonFactory("russell-timed")));
		}
		return executors.get(thread_id);
	}

	private static final int NTHREDS = Runtime.getRuntime().availableProcessors();
	private static ExecutorService threadpool = Executors.newFixedThreadPool(NTHREDS, daemonFactory("russell-worker"));

	private static final ScheduledFuture<?> startInterruptTimer(TaskTimer task) {
		return getTimer().scheduleAtFixedRate(task, 0, 100, TimeUnit.MILLISECONDS);
	}

	private static final ScheduledFuture<?> startInterruptTimer(long time_limit) {
		return startInterruptTimer(new TaskTimer(System.currentTimeMillis() + time_limit, Thread.currentThread()));
	}
/*
	public static final <R> R evalTimed0(Func0<R> fn, R defval, double time_limit) {
        try {
            return getExecutor().submit(() -> {
				startInterruptTimer((long)time_limit);
				return fn.invoke();
			}).get((long)time_limit, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
			return defval;
        }
	}

	public static final <R, T> R evalTimed1(Func1<R,T> fn, T arg, R defval, double time_limit) {
        try {
            return getExecutor().submit(() -> { // func
				startInterruptTimer((long)time_limit);
				return fn.invoke(arg);
			}).get((long)time_limit, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
			return defval;
		}
	}

	public static final <R, T1, T2> R evalTimed2(Func2<R,T1, T2> fn, T1 arg1, T2 arg2, R defval, double time_limit) {
        try {
            return getExecutor().submit(() -> {
				startInterruptTimer((long)time_limit);
				return fn.invoke(arg1, arg2);
			}).get((long)time_limit, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
			return defval;
		}
	}
*/
	public static final <R, T1, T2, T3> R evalTimed3(Func3<R,T1,T2,T3> fn, T1 arg1, T2 arg2, T3 arg3, R defval, double time_limit) {
		Future<R> future = getExecutor().submit(() -> {
			Thread.interrupted(); // clear stale interrupt flag from a previous timed task on this thread
			TaskTimer task = new TaskTimer(System.currentTimeMillis() + (long)time_limit, Thread.currentThread());
			ScheduledFuture<?> timer = startInterruptTimer(task);
			try {
				return fn.invoke(arg1, arg2, arg3);
			} finally {
				task.cancel();        // prevent any in-flight timer firing from interrupting
				timer.cancel(false);  // stop future firings
				Thread.interrupted(); // clear any interrupt that slipped through before cancel
			}
		});
		try {
			return future.get((long)time_limit, TimeUnit.MILLISECONDS);
		} catch (TimeoutException e) {
			future.cancel(true);  // interrupt the lambda so its finally block runs and stops the timer
			return defval;        // genuine timeout: the task is still running, give up waiting
		} catch (InterruptedException e) {
			// This (calling) thread was interrupted while waiting — propagate the interrupt flag and
			// treat as a timeout outcome (a higher-level timer is shutting us down).
			Thread.currentThread().interrupt();
			return defval;
		} catch (ExecutionException e) {
			// The timed task itself threw. If it was interrupted by OUR timeout timer, that is a
			// genuine timeout — return defval. Any OTHER throwable is a real bug and MUST be
			// propagated to the calling (main) thread, never silently swallowed.
			Throwable cause = e.getCause();
			if (cause instanceof InterruptedException) {
				return defval;
			}
			if (cause instanceof RuntimeException) {
				throw (RuntimeException) cause;
			}
			if (cause instanceof Error) {
				throw (Error) cause;
			}
			throw new RuntimeException("Exception in timed task", cause != null ? cause : e);
		}
	}
/*
	public static final <R, T1, T2, T3, T4> R evalTimed4(Func4<R,T1,T2,T3,T4> fn, T1 arg1, T2 arg2, T3 arg3, T4 arg4, R defval, double time_limit) {
        try {
            return getExecutor().submit(() -> {
				startInterruptTimer((long)time_limit);
				return fn.invoke(arg1, arg2, arg3, arg4);
			}).get((long)time_limit, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
			return defval;
		}
	}

	public static final <R, T1, T2, T3, T4, T5> R evalTimed5(Func5<R,T1,T2,T3,T4,T5> fn, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, R defval, double time_limit) {
		try {
            return getExecutor().submit(() -> {
				startInterruptTimer((long)time_limit);
				return fn.invoke(arg1, arg2, arg3, arg4, arg5);
			}).get((long)time_limit, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
			return defval;
		}
	}
*/
	// Like the stdlib `concurrent`, but a worker exception is PROPAGATED to the calling (main)
	// thread instead of being printStackTrace'd and swallowed (which silently returns a partial/
	// empty result array and hides real bugs). OutOfMemoryError still hard-exits (unrecoverable).
	@SuppressWarnings("unchecked")
	public static final Object[] concurrentStrict(Object threadPool, Object[] tasks) {
		List<Callable<Object>> tasks2 = new ArrayList<Callable<Object>>();
		for (int i = 0; i < tasks.length; i++) {
			Func0<Object> task = (Func0<Object>) tasks[i];
			tasks2.add(new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					try {
						Object result = task.invoke();
						FlowRuntime.eventLoop();
						return result;
					} catch (OutOfMemoryError e) {
						System.exit(255);
						return null;
					}
				}
			});
		}

		Object[] resArr = new Object[0];
		try {
			List<Object> res = new ArrayList<Object>();
			ExecutorService threadPool2 = (ExecutorService) threadPool;
			for (Future<Object> future : threadPool2.invokeAll(tasks2)) {
				res.add(future.get());
			}
			resArr = res.toArray();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			// A worker task threw — propagate it to the main thread rather than swallow.
			Throwable cause = e.getCause();
			if (cause instanceof RuntimeException) {
				throw (RuntimeException) cause;
			}
			if (cause instanceof Error) {
				throw (Error) cause;
			}
			throw new RuntimeException("Exception in concurrent task", cause != null ? cause : e);
		}
		return resArr;
	}

	@SuppressWarnings("unchecked")
	public static final Object[] concurrentTimeout(Object[] tasks, double global_limeout) {

		List<Callable<Object>> tasks2 = new ArrayList<Callable<Object>>();
		List<Long> timeouts = new ArrayList<Long>();
		List<Object> defvals = new ArrayList<Object>();
		final long started = System.currentTimeMillis();
		final long global_limit = (global_limeout > 0) ? started + (long)global_limeout : -1;

		for (int i = 0; i < tasks.length; i++) {
			Struct task = (Struct) tasks[i];
			Func0<Object> fn = (Func0<Object>) task.getFields()[0];
			long timeout = ((Double) task.getFields()[1]).longValue();
			Object defval = task.getFields()[2];
			tasks2.add(() -> {
				if (global_limit > 0 && System.currentTimeMillis() > global_limit) {
					return defval;
				} else {
					startInterruptTimer(timeout);
					return fn.invoke();
				}
			});
			timeouts.add(timeout);
			defvals.add(defval);
		}

		Object[] resArr = new Object[0];

		try {
			List<Object> res = new ArrayList<Object>();
			int index = 0;
			for (Future<Object> future : threadpool.invokeAll(tasks2)) {
				Long timeout = timeouts.get(index);
				try {
					res.add(future.get(timeout, TimeUnit.MILLISECONDS));
				} catch (Exception e) {
					res.add(defvals.get(index));
				}
				index += 1;
			}
			resArr = res.toArray();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // propagate interrupt; caller (evalTimed3) will clear it
		} catch (Exception e) {
			System.out.println("General exception in concurrentTimeout: " + e.getMessage());
			e.printStackTrace();
		}

		return resArr;
	}

	@SuppressWarnings("unchecked")
	public static final Object[] sequentialTimeout(Object[] tasks, double global_limeout) {

		List<Callable<Object>> tasks2 = new ArrayList<Callable<Object>>();
		List<Long> timeouts = new ArrayList<Long>();
		List<Object> defvals = new ArrayList<Object>();
		final long started = System.currentTimeMillis();
		final long global_limit = (global_limeout > 0) ? started + (long)global_limeout : -1;

		for (int i = 0; i < tasks.length; i++) {
			//Func0<Object> task = (Func0<Object>) tasks[i];
			Struct task = (Struct) tasks[i];
			Func0<Object> fn = (Func0<Object>) task.getFields()[0];
			long timeout = ((Double) task.getFields()[1]).longValue();
			Object defval = task.getFields()[2];
			tasks2.add(() -> {
				if (global_limit > 0 && System.currentTimeMillis() > global_limit) {
					return defval;
				} else {
					startInterruptTimer(timeout);
					return fn.invoke();
				}
			});
			timeouts.add(timeout);
			defvals.add(defval);
		}

		Object[] resArr = new Object[0];

		try {
			List<Object> res = new ArrayList<Object>();
			int index = 0;
			ExecutorService executor = getExecutor();
			for (Future<Object> future : executor.invokeAll(tasks2)) {
				Long timeout = timeouts.get(index);
				try {
					res.add(future.get(timeout, TimeUnit.MILLISECONDS));
				} catch (Exception e) {
					res.add(defvals.get(index));
				}
				index += 1;
			}
			resArr = res.toArray();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resArr;
	}
}
