package com.area9innovation.flow;

import java.util.Arrays;

public class RussellLongArray extends NativeHost {
	public class LongArray {
		public long[] arr;
		public int beg;
		public int end;
	}
	static public final long makeLong(int low, int high) {
		return (long)high << 32 + (long)low;
	}
	static public final int longLow(long l) {
		return (int)l & 0xFFFFFFFF;
	}
	static public final int longHigh(long l) {
		return (int)l >> 32;
	}

	static public final long[] make(int size) { return new long[size]; }
	static public final int size(long[] arr) { return arr.length; }
	static public final long get(long[] arr, int i) { return arr[i]; }
	static public final Object set(long[] arr, int i, long v) { arr[i] = v; }

	public static final long[] concat(long[] a, long[] b) {
		long[] rv = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, rv, a.length, b.length);
		return rv;
	}
	public static final long[] pushFront(Object v, long[] b) {
		long[] rv = new long[b.length + 1];
		rv[0] = (long)v;
		System.arraycopy(b, 0, rv, 1, b.length);
		return rv;
	}
	public static final long[] pushBack(long a[], Object v) {
		long[] rv = new long[a.length + 1];
		System.arraycopy(a, 0, rv, 0, a.length);
		rv[a.length] = (long)v;
		return rv;
	}
	@SuppressWarnings("unchecked")
	public static final <T> Object[] map(long[] arr, Func1<T, Object> clos) {
		Object[] rv = new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			rv[i] = clos.invoke(arr[i]);
		}
		return rv;
	}

	@SuppressWarnings("unchecked")
	public static final Object iter(long[] arr, Func1<Object, Object> clos) {
		for (int i = 0; i < arr.length; i++)
			clos.invoke(arr[i]);
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final <T> Object[] mapi(long[] arr, Func2<T, Integer, Object> clos) {
		Object[] rv = new Object[arr.length];
		for (int i = 0; i < arr.length; i++)
			rv[i] = clos.invoke(arr[i]);
		return rv;
	}

	@SuppressWarnings("unchecked")
	public static final Object iteri(long[] arr, Func2<Object, Integer, Object> clos) {
		for (int i = 0; i < arr.length; i++)
			clos.invoke(i, arr[i]);
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final int iteriUntil(long[] arr, Func2<Boolean, Integer, Object> clos) {
		for (int i = 0; i < arr.length; i++)
			if (clos.invoke(i, arr[i]))
				return i;
		return arr.length;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T fold(long[] arr, T init, Func2<T, T, Object> clos) {
		for (int i = 0; i < arr.length; i++)
			init = clos.invoke(init, arr[i]);
		return init;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T foldi(long[] arr, T init, Func3<T, Integer, T, Object> clos) {
		for (int i = 0; i < arr.length; i++)
			init = clos.invoke(i, init, arr[i]);
		return init;
	}

	@SuppressWarnings("unchecked")
	public static final  long[] filter(long[] arr, Func1<Boolean, Object> test) {
		boolean[] tmp = new boolean[arr.length];
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (tmp[i] = test.invoke(arr[i]))
				count++;
		long[] out = new long[count];
		for (int i = 0, j = 0; i < arr.length; i++)
			if (tmp[i])
				out[j++] = arr[i];
		return out;
	}
}
