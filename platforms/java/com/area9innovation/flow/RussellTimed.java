package com.area9innovation.flow;

import java.util.ArrayList;
//import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

public class RussellTimed extends NativeHost {
	@SuppressWarnings (value="unchecked")
	public static final <K, V> boolean iterHashMapTimed(Object map, Func2<Object, K, V> fn, double time_limit) {
		HashMap<K, V> hmap = (HashMap<K, V>)map;
		final long started = System.currentTimeMillis();
		final long limit = (time_limit > 0) ? started + (long)time_limit : -1;
		final Set<Map.Entry<K, V>> set = hmap.entrySet();
		final Iterator<Map.Entry<K, V>> iter = set.iterator();
		boolean interrupted = false;
		while (iter.hasNext()) {
			Map.Entry<K, V> entry = iter.next();
			fn.invoke(entry.getKey(), entry.getValue());
			if (limit > 0 && System.currentTimeMillis() > limit) {
				interrupted = true;
				break;
			}
		}
		return interrupted;
	}

	/*@SuppressWarnings("unchecked")
	public static final <T1,T2> Object[] map(Object[] arr, Func1<T1,T2> clos, double time_limit) {
		final long started = System.currentTimeMillis();
		final long limit = (time_limit > 0) ? started + (long)time_limit : -1;
		Object[] rv = new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			rv[i] = clos.invoke((T2)arr[i]);
		}
		return rv;
	}*/

	@SuppressWarnings("unchecked")
	public static final <T> boolean iter(Object[] arr, Func1<Object,T> clos, double time_limit) {
		final long started = System.currentTimeMillis();
		boolean interrupted = false;
		final long limit = (time_limit > 0) ? started + (long)time_limit : -1;
		for (int i = 0; i < arr.length; i++) {
			clos.invoke((T)arr[i]);
			if (limit > 0 && System.currentTimeMillis() > limit) {
				interrupted = true;
				break;
			}
		}
		return interrupted;
	}

	/*@SuppressWarnings("unchecked")
	public static final <T1,T2> Object[] mapi(Object[] arr, Func2<T1,Integer,T2> clos, double time_limit) {
		final long started = System.currentTimeMillis();
		final long limit = (time_limit > 0) ? started + (long)time_limit : -1;
		Object[] rv = new Object[arr.length];
		boolean interrupted = false;
		for (int i = 0; i < arr.length; i++) {
			rv[i] = clos.invoke(i, (T2)arr[i]);
			if (limit > 0 && System.currentTimeMillis() > limit) {
				interrupted = true;
				break;
			}
		}
		return rv;
	}*/


	public static final Object[] rvector2array(Object v) {
		ArrayList vector = (ArrayList)v;
		Object[] ret = new Object[vector.size()];
		for (int i = 0; i < ret.length; ++ i) {
			ret[i] = vector.get(ret.length - (i + 1));
		}
		return ret;
	}

	public static final <R> R evalTimed0(Func0<R> fn, R defval, double time_limit) {
		class FuncWrapped<R> implements Runnable {
			private final Func0<R> fn;
			public R retval;
			private FuncWrapped(Func0<R> fn, R defval) {
				this.fn = fn;
				this.retval = defval;
			}
			@Override
			public void run() {
				retval = fn.invoke();
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FuncWrapped<R> func = new FuncWrapped<R>(fn, defval);
        Future future = executor.submit(func);
        try {
            future.get((long)time_limit, TimeUnit.MILLISECONDS);
            executor.shutdown();
        } catch (TimeoutException e) {
            executor.shutdown();
        } catch (Exception e) {
			executor.shutdown();
			System.out.println(e.fillInStackTrace().toString());
			System.exit(-1);
		}
		return func.retval;
	}

	public static final <R, T> R evalTimed1(Func1<R,T> fn, T arg, R defval, double time_limit) {
		class FuncWrapped<R, T> implements Runnable {
			private final Func1<R, T> fn;
			public R retval;
			private final T arg;
			private FuncWrapped(Func1<R, T> fn, R defval, T arg) {
				this.fn = fn;
				this.retval = defval;
				this.arg = arg;
			}
			@Override
			public void run() {
				retval = fn.invoke(arg);
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FuncWrapped<R,T> func = new FuncWrapped<R,T>(fn, defval, arg);
        Future future = executor.submit(func);
        try {
            future.get((long)time_limit, TimeUnit.MILLISECONDS);
            executor.shutdown();
        } catch (TimeoutException e) {
            executor.shutdown();
        } catch (Exception e) {
			executor.shutdown();
			System.out.println(e.fillInStackTrace().toString());
			System.exit(-1);
		}
		return func.retval;
	}

	public static final <R, T1, T2> R evalTimed2(Func2<R,T1, T2> fn, T1 arg1, T2 arg2, R defval, double time_limit) {
		class FuncWrapped<R, T1, T2> implements Runnable {
			private final Func2<R, T1, T2> fn;
			public R retval;
			private final T1 arg1;
			private final T2 arg2;
			private FuncWrapped(Func2<R, T1, T2> fn, R defval, T1 arg1, T2 arg2) {
				this.fn = fn;
				this.retval = defval;
				this.arg1 = arg1;
				this.arg2 = arg2;
			}
			@Override
			public void run() {
				retval = fn.invoke(arg1, arg2);
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FuncWrapped<R,T1, T2> func = new FuncWrapped<R,T1,T2>(fn, defval, arg1, arg2);
        Future future = executor.submit(func);
        try {
            future.get((long)time_limit, TimeUnit.MILLISECONDS);
            executor.shutdown();
        } catch (TimeoutException e) {
            executor.shutdown();
        } catch (Exception e) {
			executor.shutdown();
			System.out.println(e.fillInStackTrace().toString());
			System.exit(-1);
		}
		return func.retval;
	}

	public static final <R, T1, T2, T3> R evalTimed3(Func3<R,T1,T2,T3> fn, T1 arg1, T2 arg2, T3 arg3, R defval, double time_limit) {
		class FuncWrapped<R, T1, T2, T3> implements Runnable {
			private final Func3<R, T1, T2, T3> fn;
			public R retval;
			private final T1 arg1;
			private final T2 arg2;
			private final T3 arg3;
			private FuncWrapped(Func3<R, T1, T2, T3> fn, R defval, T1 arg1, T2 arg2, T3 arg3) {
				this.fn = fn;
				this.retval = defval;
				this.arg1 = arg1;
				this.arg2 = arg2;
				this.arg3 = arg3;
			}
			@Override
			public void run() {
				retval = fn.invoke(arg1, arg2, arg3);
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FuncWrapped<R,T1, T2, T3> func = new FuncWrapped<R,T1,T2,T3>(fn, defval, arg1, arg2, arg3);
        Future future = executor.submit(func);
        try {
            future.get((long)time_limit, TimeUnit.MILLISECONDS);
            executor.shutdown();
        } catch (TimeoutException e) {
            executor.shutdown();
        } catch (Exception e) {
			executor.shutdown();
			System.out.println(e.fillInStackTrace().toString());
			System.exit(-1);
		}
		return func.retval;
	}

	public static final <R, T1, T2, T3, T4> R evalTimed4(Func4<R,T1,T2,T3,T4> fn, T1 arg1, T2 arg2, T3 arg3, T4 arg4, R defval, double time_limit) {
		class FuncWrapped<R, T1, T2, T3, T4> implements Runnable {
			private final Func4<R, T1, T2, T3, T4> fn;
			public R retval;
			private final T1 arg1;
			private final T2 arg2;
			private final T3 arg3;
			private final T4 arg4;
			private FuncWrapped(Func4<R, T1, T2, T3, T4> fn, R defval, T1 arg1, T2 arg2, T3 arg3, T4 arg4) {
				this.fn = fn;
				this.retval = defval;
				this.arg1 = arg1;
				this.arg2 = arg2;
				this.arg3 = arg3;
				this.arg4 = arg4;
			}
			@Override
			public void run() {
				retval = fn.invoke(arg1, arg2, arg3, arg4);
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FuncWrapped<R,T1, T2, T3, T4> func = new FuncWrapped<R,T1,T2,T3,T4>(fn, defval, arg1, arg2, arg3, arg4);
        Future future = executor.submit(func);
        try {
            future.get((long)time_limit, TimeUnit.MILLISECONDS);
            executor.shutdown();
        } catch (TimeoutException e) {
            executor.shutdown();
        } catch (Exception e) {
			executor.shutdown();
			System.out.println(e.fillInStackTrace().toString());
			System.exit(-1);
		}
		return func.retval;
	}

	public static final <R, T1, T2, T3, T4, T5> R evalTimed5(Func5<R,T1,T2,T3,T4,T5> fn, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5, R defval, double time_limit) {
		class FuncWrapped<R, T1, T2, T3, T4, T5> implements Runnable {
			private final Func5<R, T1, T2, T3, T4, T5> fn;
			public R retval;
			private final T1 arg1;
			private final T2 arg2;
			private final T3 arg3;
			private final T4 arg4;
			private final T5 arg5;
			private FuncWrapped(Func5<R, T1, T2, T3, T4, T5> fn, R defval, T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
				this.fn = fn;
				this.retval = defval;
				this.arg1 = arg1;
				this.arg2 = arg2;
				this.arg3 = arg3;
				this.arg4 = arg4;
				this.arg5 = arg5;
			}
			@Override
			public void run() {
				retval = fn.invoke(arg1, arg2, arg3, arg4, arg5);
			}
		}
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FuncWrapped<R,T1, T2, T3, T4, T5> func = new FuncWrapped<R,T1,T2,T3,T4,T5>(fn, defval, arg1, arg2, arg3, arg4, arg5);
        Future future = executor.submit(func);
        try {
            future.get((long)time_limit, TimeUnit.MILLISECONDS);
            executor.shutdown();
        } catch (TimeoutException e) {
            executor.shutdown();
        } catch (Exception e) {
			executor.shutdown();
			System.out.println(e.fillInStackTrace().toString());
			System.exit(-1);
		}
		return func.retval;
	}
}