package com.area9innovation.flow;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;


public class RussellTimed extends NativeHost {
	static private class TaskTimer implements Runnable {
		private long time_limit;
		private Thread controlled;
		private TaskTimer(long time_limit, Thread controlled) {
			this.time_limit = time_limit;
			this.controlled = controlled;
		}
		@Override
		public void run() {
			if (System.currentTimeMillis() > time_limit) {
				controlled.interrupt();
			}
		}
	}

	private static ConcurrentHashMap<Long, ScheduledExecutorService> timers = 
		new ConcurrentHashMap<Long, ScheduledExecutorService>();

	private static ScheduledExecutorService getTimer() {
		long thread_id = Thread.currentThread().getId();
		if (!timers.containsKey(thread_id)) {
			timers.put(thread_id, Executors.newSingleThreadScheduledExecutor());
		}
		return timers.get(thread_id);
	}

	private static ConcurrentHashMap<Long, ExecutorService> executors = 
		new ConcurrentHashMap<Long, ExecutorService>();

	private static ExecutorService getExecutor() {
		long thread_id = Thread.currentThread().getId();
		if (!executors.containsKey(thread_id)) {
			executors.put(thread_id, Executors.newSingleThreadExecutor());
		}
		return executors.get(thread_id);
	}

	private static final int NTHREDS = Runtime.getRuntime().availableProcessors();
	private static ExecutorService threadpool = Executors.newFixedThreadPool(NTHREDS);

	private static final void startInterruptTimer(long time_limit) {
		getTimer().scheduleAtFixedRate(
			new TaskTimer(System.currentTimeMillis() + time_limit, Thread.currentThread()), 
			0, 100, TimeUnit.MILLISECONDS
		);
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
		try {
            return getExecutor().submit(() -> {
				startInterruptTimer((long)time_limit);
				return fn.invoke(arg1, arg2, arg3);
			}).get((long)time_limit, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
			return defval;
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
			e.printStackTrace();
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
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resArr;
	}
}
