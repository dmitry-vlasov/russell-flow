package com.area9innovation.flow;

import java.util.ArrayList;
//import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
}