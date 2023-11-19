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
}
