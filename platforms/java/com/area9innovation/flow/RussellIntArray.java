package com.area9innovation.flow;

public class RussellIntArray extends NativeHost {
	static public int[] make(int size) { return new int[size]; }
	static public int size(int[] arr) { return arr.length; }
	static public int get(int[] arr, int i) { return arr[i]; }
	static public void set(int[] arr, int i, int v) { arr[i] = v; }
}
