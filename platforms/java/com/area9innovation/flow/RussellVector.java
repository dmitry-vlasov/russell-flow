package com.area9innovation.flow;

import java.util.ArrayList;

public class RussellVector extends NativeHost {
	
	public static final Object[] rvector2array(Object v) {
		ArrayList vector = (ArrayList)v;
		Object[] ret = new Object[vector.size()];
		for (int i = 0; i < ret.length; ++ i) {
			ret[i] = vector.get(ret.length - (i + 1));
		}
		return ret;
	}
}
