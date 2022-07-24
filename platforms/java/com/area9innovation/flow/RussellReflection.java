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
			return meth_name;
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
					Function func = method2func(fn_name, meth);
					if (func != null) {
						name2func.put(fn_name, method2func(fn_name, meth));
					}
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

	private static final HashMap<String, Function> name2func = new HashMap<String, Function>();

	public static final void clearRuntimeFunctions() {
		name2func.clear();
	}

	public static final int countRuntimeFunctions() {
		return name2func.size();
	}

	public static final String[] runtimeFunctionNames() {
		java.util.Set<String> key_set = name2func.keySet();
		Object[] key_arr = key_set.toArray();
		String[] keys = new String[key_arr.length];
		for (int i = 0; i < key_arr.length; ++i) {
			keys[i] = (String)key_arr[i];
		}
		return keys;
	}

	public static final boolean hasRuntimeFunction(String fn) {
		return (name2func.get(fn) != null);
	}

	public static final Function getRuntimeFunction(String fn) {
		return name2func.get(fn);
	}

	@SuppressWarnings("unchecked")
	public static final Object callRuntimeFunction(String fn, Object[] args) {
		Function func = name2func.get(fn);
		if (func == null) {
			System.out.println("Function " + fn + " is not registered");
			System.exit(-1);
			return null;
		} else {
			try {
				if (func instanceof Func0) {
					return ((Func0)func).invoke();
				} else if (func instanceof Func1) {
					return ((Func1)func).invoke(args[0]);
				} else if (func instanceof Func2) {
					return ((Func2)func).invoke(args[0], args[1]);
				} else if (func instanceof Func3) {
					return ((Func3)func).invoke(args[0], args[1], args[2]);
				} else if (func instanceof Func4) {
					return ((Func4)func).invoke(args[0], args[1], args[2], args[3]);
				} else if (func instanceof Func5) {
					return ((Func5)func).invoke(args[0], args[1], args[2], args[3], args[4]);
				} else if (func instanceof Func6) {
					return ((Func6)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5]);
				} else if (func instanceof Func7) {
					return ((Func7)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
				} else if (func instanceof Func8) {
					return ((Func8)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
				} else if (func instanceof Func9) {
					return ((Func9)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
				} else if (func instanceof Func10) {
					return ((Func10)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
				} else if (func instanceof Func11) {
					return ((Func11)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10]);
				} else if (func instanceof Func12) {
					return ((Func12)func).invoke(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11]);
				} else {
					throw new Exception("function arity " + args.length + " is not supported");
				}
			} catch (Exception e) {
				System.out.println("Function " + fn + " is not found");
				System.out.println(e.getMessage());
				System.out.println(exceptionStackTrace(e));
				System.exit(-1);
				return null;
			}
		}
	}

	private static final Function method2func(String name, Method meth) {
		int arity = meth.getParameterCount();
		try {
			if (arity == 0) {
				return (Func0)(() -> {
					try {
						return meth.invoke(null);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 1) {
				return (Func1)((Object a1) -> {
					try {
						return meth.invoke(null, a1);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 2) {
				return (Func2)((Object a1, Object a2) -> {
					try {
						return meth.invoke(null, a1, a2);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 3) {
				return (Func3)((Object a1, Object a2, Object a3) -> {
					try {
						return meth.invoke(null, a1, a2, a3);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 4) {
				return (Func4)((Object a1, Object a2, Object a3, Object a4) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 5) {
				return (Func5)((Object a1, Object a2, Object a3, Object a4, Object a5) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 6) {
				return (Func6)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 7) {
				return (Func7)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6, a7);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							"arg 7: " + a7 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 8) {
				return (Func8)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6, a7, a8);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							"arg 7: " + a7 + "\n" +
							"arg 8: " + a8 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 9) {
				return (Func9)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8, Object a9) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6, a7, a8, a9);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							"arg 7: " + a7 + "\n" +
							"arg 8: " + a8 + "\n" +
							"arg 9: " + a9 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 10) {
				return (Func10)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8, Object a9, Object a10) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							"arg 7: " + a7 + "\n" +
							"arg 8: " + a8 + "\n" +
							"arg 9: " + a9 + "\n" +
							"arg 10: " + a10 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 11) {
				return (Func11)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8, Object a9, Object a10, Object a11) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							"arg 7: " + a7 + "\n" +
							"arg 8: " + a8 + "\n" +
							"arg 9: " + a9 + "\n" +
							"arg 10: " + a10 + "\n" +
							"arg 11: " + a11 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else if (arity == 12) {
				return (Func12)((Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8, Object a9, Object a10, Object a11, Object a12) -> {
					try {
						return meth.invoke(null, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
					} catch (ReflectiveOperationException e) {
						System.err.println(
							"at calling " + name + ":\n" +
							"arg 1: " + a1 + "\n" +
							"arg 2: " + a2 + "\n" +
							"arg 3: " + a3 + "\n" +
							"arg 4: " + a4 + "\n" +
							"arg 5: " + a5 + "\n" +
							"arg 6: " + a6 + "\n" +
							"arg 7: " + a7 + "\n" +
							"arg 8: " + a8 + "\n" +
							"arg 9: " + a9 + "\n" +
							"arg 10: " + a10 + "\n" +
							"arg 11: " + a11 + "\n" +
							"arg 12: " + a12 + "\n" +
							exceptionStackTrace(e)
						);
						return null;
					}
				});
			} else {
				// Not implemented yet
				System.err.println("Making a function " + name + " of arity: " + arity + " is not implementer yet");
				return null;
			}
		} catch (java.lang.IllegalArgumentException ex) {
			System.err.println("While adding a function: " + name + "\n" + ex.getMessage());
			return null;
		}
	}
}
