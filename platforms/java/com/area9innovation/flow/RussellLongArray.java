package com.area9innovation.flow;

import java.util.Arrays;

public class RussellLongArray extends NativeHost {
	public class LongArray {
		public long[] arr;
		public int beg;
		public int end;
	}
	static public final long[] make(int size) { return new long[size]; }
	static public final int size(long[] arr) { return arr.length; }
	static public final int getLow(long[] arr, int i) { return (int)arr[i] & 0xFFFFFFFF; }
	static public final int getHigh(long[] arr, int i) { return (int)arr[i] >> 32; }
	static public final void set(long[] arr, int i, int low, int high) { arr[i] = (long)high << 32 + (long)low; }
	public static final long[] concat(long[] a, long[] b) {
		long[] rv = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, rv, a.length, b.length);
		return rv;
	}
	public static final long[] pushFront(int low, int high, long[] b) {
		long[] rv = new long[b.length + 1];
		rv[0] = (long)high << 32 + (long)low;
		System.arraycopy(b, 0, rv, 1, b.length);
		return rv;
	}
	public static final long[] pushBack(long a[], int low, int high) {
		long[] rv = new long[a.length + 1];
		System.arraycopy(a, 0, rv, 0, a.length);
		rv[a.length] = (long)high << 32 + (long)low;
		return rv;
	}
	@SuppressWarnings("unchecked")
	public static final <T> Object[] map(long[] arr, Func2<T, Integer, Integer> clos) {
		Object[] rv = new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			rv[i] = clos.invoke(arr[i] & 0xFFFFFFFF, arr[i] >> 32);
		}
		return rv;
	}

	@SuppressWarnings("unchecked")
	public static final Object iter(long[] arr, Func2<Object, Integer, Integer> clos) {
		for (int i = 0; i < arr.length; i++)
			clos.invoke(arr[i] & 0xFFFFFFFF, arr[i] >> 32);
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final <T> Object[] mapi(long[] arr, Func3<T, Integer, Integer, Integer> clos) {
		Object[] rv = new Object[arr.length];
		for (int i = 0; i < arr.length; i++)
			rv[i] = clos.invoke(i, arr[i] & 0xFFFFFFFF, arr[i] >> 32);
		return rv;
	}

	@SuppressWarnings("unchecked")
	public static final Object iteri(long[] arr, Func3<Object, Integer, Integer, Integer> clos) {
		for (int i = 0; i < arr.length; i++)
			clos.invoke(i, arr[i] & 0xFFFFFFFF, arr[i] >> 32);
		return null;
	}

	@SuppressWarnings("unchecked")
	public static final int iteriUntil(long[] arr, Func3<Boolean, Integer, Integer, Integer> clos) {
		for (int i = 0; i < arr.length; i++)
			if (clos.invoke(i, arr[i] & 0xFFFFFFFF, arr[i] >> 32))
				return i;
		return arr.length;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T fold(long[] arr, T init, Func3<T, T, Integer, Integer> clos) {
		for (int i = 0; i < arr.length; i++)
			init = clos.invoke(init, arr[i] & 0xFFFFFFFF, arr[i] >> 32);
		return init;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T foldi(long[] arr, T init, Func4<T, Integer, T, Integer, Integer> clos) {
		for (int i = 0; i < arr.length; i++)
			init = clos.invoke(i, init, arr[i] & 0xFFFFFFFF, arr[i] >> 32);
		return init;
	}

	@SuppressWarnings("unchecked")
	public static final  long[] filter(long[] arr, Func2<Boolean, Integer, Integer> test) {
		boolean[] tmp = new boolean[arr.length];
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (tmp[i] = test.invoke(arr[i] & 0xFFFFFFFF, arr[i] >> 32))
				count++;
		long[] out = new long[count];
		for (int i = 0, j = 0; i < arr.length; i++)
			if (tmp[i])
				out[j++] = arr[i];
		return out;
	}
}
