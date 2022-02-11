package com.area9innovation.flow;

import com.area9innovation.flow.Struct;
import com.area9innovation.flow.Function;
import com.area9innovation.flow.Reference;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;

public class RussellReflection extends NativeHost {

	private static final String exceptionStackTrace(Throwable ex) {
		StringWriter stackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(stackTrace));
		return stackTrace.toString();
	}

	private static final String stripFuncName(Method meth) {
		String meth_name = meth.getName();
		if (meth_name.startsWith("f_")) {
			return meth_name.substring(2, meth_name.length());
		} else {
			return "";
		}
	}

	public static final boolean hasRuntimeModule(String module) {
		try {
			Class.forName(module);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static final Object registerRuntimeModule(String module) {
		try {
			Class module_class = Class.forName(module);
			ArrayList<Method> functions = new ArrayList<Method>();
			for (Method meth : module_class.getMethods()) {
				String fn_name = stripFuncName(meth);
				if (fn_name != "") {
					//System.out.println("REGISTERING FUNC: " + fn_name);
					func2method.put(fn_name, meth);
				}
			}
		} catch (Exception e) {
			System.out.println("Class " + module + " is not found");
			System.out.println(e.getMessage());
			System.out.println(exceptionStackTrace(e));
			System.exit(-1);
		}
		return null;
	}

	private static final HashMap<String, Method> func2method = new HashMap<String, Method>();

	public static final boolean hasRuntimeFunction(String fn) {
		Method meth = func2method.get(fn);
		return (meth != null);
	}

	public static final Object callRuntimeFunction(String fn, Object[] args) {
		Method meth = func2method.get(fn);
		if (meth == null) {
			System.out.println("Function " + fn + " is not registered");
			System.exit(-1);
			return null;
		} else {
			try {
				return meth.invoke(null, args);
			} catch (Exception e) {
				System.out.println("Function " + fn + " is not found");
				System.out.println(e.getMessage());
				System.out.println(exceptionStackTrace(e));
				System.exit(-1);
				return null;
			}
		}
	}

	public static final String runtimeValueType(Object value) {
		if (value == null) {
			return "void";
		} else if (value instanceof Integer) {
			return "int";
		} else if (value instanceof Double) {
			return "double";
		} else if (value instanceof Boolean) {
			return "bool";
		} else if (value instanceof String) {
			return "string";
		} else if (value instanceof Struct) {
			return ((Struct)value).getTypeName();
		} else if (value instanceof Function) {
			return "function";
		} else if (value instanceof Object[]) {
			return "array";
		} else if (value instanceof Reference) {
			return "ref";
		} else {
			return "undef";
		}
	}
}
