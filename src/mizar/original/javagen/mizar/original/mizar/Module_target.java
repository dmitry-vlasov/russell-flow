// HASH COLLISIONS: YES
// timestamp: 1748701794000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_target {
	public static Object[] g_allTargets;
	public static Reference<String> g_browser;
	public static Reference<String> g_deviceType;
	public static Reference<String> g_flashVersion;
	public static Reference<Boolean> g_isAndroid;
	public static Reference<Boolean> g_isIos;
	public static Reference<Boolean> g_isLinux;
	public static Reference<Boolean> g_isMacOSX;
	public static boolean g_isPlatformFix;
	public static Reference<Boolean> g_isWindows;
	public static Reference<String> g_majorOsPart;
	public static Reference<String> g_minorOsPart;
	public static Reference<String> g_resolution;
	public static Reference<String> g_userAgent;
	public static int g_screenDPI;
	public static double g_screenDensity;
	public static boolean g_cgi;
	public static boolean g_cpp;
	public static boolean g_csharp;
	public static boolean g_flash;
	public static boolean g_java;
	public static boolean g_js;
	public static boolean g_jslibrary;
	public static boolean g_mobile;
	public static boolean g_nativevideo;
	public static boolean g_neko;
	public static boolean g_nodejs;
	public static boolean g_nwjs;
	public static boolean g_opengl;
	public static boolean g_qt;
	public static boolean g_xaml;
	public static boolean g_nogui;
	public static boolean g_safariBrowser;
	public static int g_bootTargetInit;
	public static void init_bootTargetInit() {
		if (Module_devmode.f_isDevMode()) {
			Module_runtime.f_println(((((((((((((((((((((((((((("target: name="+Native.getTargetName())+", windows=")+Module_dynamic.f_toString(Module_target.f_windows()))+", windowsxp=")+Module_dynamic.f_toString(Module_target.f_windowsxp()))+", macosx=")+Module_dynamic.f_toString(Module_target.f_macosx()))+", linux=")+Module_dynamic.f_toString(Module_target.f_linux()))+", ios=")+Module_dynamic.f_toString(Module_target.f_ios()))+", android=")+Module_dynamic.f_toString(Module_target.f_android()))+", mobile=")+Module_dynamic.f_toString(Module_target.g_mobile))+", screenDPI=")+Module_dynamic.f_toString(Module_target.g_screenDPI))+", getOsFlow=")+Module_dynamic.f_toString(Module_target.f_getOsFlow()))+", getFlashVersion=")+Module_dynamic.f_toString(Module_target.f_getFlashVersion()))+", getBrowser=")+Module_dynamic.f_toString(Module_target.f_getBrowser()))+", getResolution=")+Module_dynamic.f_toString(Module_target.f_getResolution()))+", getUserAgent=")+Module_dynamic.f_toString(Module_target.f_getUserAgent())));
		} else {
		}
		g_bootTargetInit=0;
	}
	public static void init() {
		g_allTargets=Module_string.f_strSplit(Native.getTargetName(), ",");
		g_browser=((Reference<String>)(new Reference("")));
		g_deviceType=((Reference<String>)(new Reference("")));
		g_flashVersion=((Reference<String>)(new Reference("")));
		g_isAndroid=((Reference<Boolean>)(new Reference(false)));
		g_isIos=((Reference<Boolean>)(new Reference(false)));
		g_isLinux=((Reference<Boolean>)(new Reference(false)));
		g_isMacOSX=((Reference<Boolean>)(new Reference(false)));
		g_isPlatformFix=!Module_url_parameter.f_isUrlParameterFalse("platform_fix");
		g_isWindows=((Reference<Boolean>)(new Reference(false)));
		g_majorOsPart=((Reference<String>)(new Reference("")));
		g_minorOsPart=((Reference<String>)(new Reference("")));
		g_resolution=((Reference<String>)(new Reference("")));
		g_userAgent=((Reference<String>)(new Reference("")));
		final Func1<Boolean,String> l1_$0 = (Func1<Boolean, String>)(String av) -> {
			return ((Boolean)Module_string.f_startsWith(av, "dpi="));
		};
		final Struct l2_gsymswitch0 = Module_array.f_find(Module_target.g_allTargets, ((Func1<Boolean,Object>)(Func1)l1_$0));
		Struct l3__tmp = l2_gsymswitch0;
		switch (l3__tmp.getTypeId()) {
		case 80/*None*/: {
			g_screenDPI=90;
			break;
		}
		case 88/*Some*/: {
			final Struct_Some l4__tmp = (Struct_Some)l3__tmp;
			final Object l5_v = l4__tmp.f_value;
			final String l6_v = ((String)(l4__tmp).f_value);
			g_screenDPI=Module_math.f_s2i(Module_string.f_strRight(l6_v, 4));
			break;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l3__tmp.getTypeName());
		}
		final Func1<Boolean,String> l8_$7 = (Func1<Boolean, String>)(String av) -> {
			return ((Boolean)Module_string.f_startsWith(av, "density="));
		};
		final Struct l9_gsymswitch1 = Module_array.f_find(Module_target.g_allTargets, ((Func1<Boolean,Object>)(Func1)l8_$7));
		Struct l10__tmp = l9_gsymswitch1;
		switch (l10__tmp.getTypeId()) {
		case 80/*None*/: {
			g_screenDensity=1.0;
			break;
		}
		case 88/*Some*/: {
			final Struct_Some l11__tmp = (Struct_Some)l10__tmp;
			final Object l12_v = l11__tmp.f_value;
			final String l13_v = ((String)(l11__tmp).f_value);
			final double l14_parsedValue = Module_math.f_s2d(Module_string.f_strRight(l13_v, 8));
			if (Module_math.f_equalDoubles(l14_parsedValue, 0.0)) {
				g_screenDensity=1.0;
			} else {
				g_screenDensity=l14_parsedValue;
			}
			break;
		}
		default:
			throw new RuntimeException("Unexpected struct in switch: "+l10__tmp.getTypeName());
		}
		g_cgi=Module_target.f_hasTargetName("cgi");
		g_cpp=Module_target.f_hasTargetName("c++");
		g_csharp=Module_target.f_hasTargetName("csharp");
		g_flash=Module_target.f_hasTargetName("flash");
		g_java=Module_target.f_hasTargetName("java");
		g_js=Module_target.f_hasTargetName("js");
		g_jslibrary=Module_target.f_hasTargetName("jslibrary");
		g_mobile=(Module_target.f_hasTargetName("mobile")||Module_url_parameter.f_isUrlParameterTrue("overridemobile"));
		g_nativevideo=Module_target.f_hasTargetName("nativevideo");
		g_neko=Module_target.f_hasTargetName("neko");
		g_nodejs=Module_target.f_hasTargetName("nodejs");
		g_nwjs=Module_target.f_hasTargetName("nwjs");
		g_opengl=Module_target.f_hasTargetName("opengl");
		g_qt=Module_target.f_hasTargetName("qt");
		g_xaml=Module_target.f_hasTargetName("xaml");
		g_nogui=(((Module_target.g_neko||Module_target.g_cgi)||(Module_target.g_cpp&&!Module_target.f_hasTargetName("gui")))||(!(Module_url_parameter.f_getUrlParameter("nogui")).equals("")));
		g_safariBrowser=(Module_target.g_js&&Module_string.f_strContains(Native.toLowerCase(Module_target.f_getBrowser()), "safari"));
		init_bootTargetInit();
	}
	public static final boolean f_android() {
		Module_target.f_initOsParts();
		return ((boolean)Module_target.g_isAndroid.value);
	}
	public static final String f_getBrowser() {
		Module_target.f_initOsParts();
		return Module_target.g_browser.value;
	}
	public static final String f_getDeviceType() {
		Module_target.f_initOsParts();
		return Module_target.g_deviceType.value;
	}
	public static final String f_getFlashVersion() {
		Module_target.f_initOsParts();
		return Module_target.g_flashVersion.value;
	}
	public static final boolean f_getIsIosPlatform() {
		return false;
	}
	public static final boolean f_getIsMacOSPlatform() {
		return false;
	}
	public static final String f_getOsFlow() {
		Module_target.f_initOsParts();
		return ((Module_target.g_majorOsPart.value+" ")+Module_target.g_minorOsPart.value);
	}
	public static final String f_getResolution() {
		Module_target.f_initOsParts();
		return Module_target.g_resolution.value;
	}
	public static final String f_getUserAgent() {
		Module_target.f_initOsParts();
		return Module_target.g_userAgent.value;
	}
	public static final boolean f_hasTargetName(String as) {
		return Module_array.f_contains(Module_target.g_allTargets, as);
	}
	public static final Object f_initOsParts() {
		if ((Module_target.g_majorOsPart.value).equals("")) {
			Object l0_os;
			if (Native.hostCallExists("getOs")) {
				l0_os=Native.hostCall("getOs", SingletonStructs.arr_empty);
			} else {
				if (Module_target.f_hasTargetName("iOS")) {
					l0_os="iOS";
				} else {
					if (Module_target.f_hasTargetName("android")) {
						l0_os="Android";
					} else {
						if (Module_target.f_hasTargetName("windows")) {
							l0_os="Windows";
						} else {
							if (Module_target.f_hasTargetName("linux")) {
								l0_os="Linux";
							} else {
								if (Module_target.f_hasTargetName("macosx")) {
									l0_os="MacOSX";
								} else {
									l0_os="";
								}
							}
						}
					}
				}
			}
			final Object[] l1_osParts = Module_string.f_strSplit(((String)l0_os), ",");
			Module_target.g_majorOsPart.value = (((Native.length(l1_osParts)>0)&&(!(((String)(l1_osParts[0]))).equals("")))?((String)(l1_osParts[0])):"other");
			Module_target.g_minorOsPart.value = (((Native.length(l1_osParts)>1)&&(!(((String)(l1_osParts[1]))).equals("")))?((String)(l1_osParts[1])):"other");
			Module_target.g_isWindows.value = ((Boolean)((Module_target.g_majorOsPart.value).equals("Windows")||(Module_target.g_isPlatformFix&&Module_target.f_isWindowsPlatform())));
			final Object l2_userAgentCheck = Native.hostCall("getUserAgent", SingletonStructs.arr_empty);
			((Reference<Object>)(Reference)Module_target.g_userAgent).value = ((!(Module_dynamic.f_toString(l2_userAgentCheck)).equals("{}"))?l2_userAgentCheck:"other");
			Module_target.g_isMacOSX.value = ((Boolean)(((Module_target.g_majorOsPart.value).equals("MacOSX")||(Module_target.g_isPlatformFix&&Module_target.f_getIsMacOSPlatform()))||Module_string.f_strContains(Native.toLowerCase(Module_target.g_userAgent.value), "mac os x")));
			Module_target.g_isLinux.value = ((Boolean)(((Module_target.g_majorOsPart.value).equals("Linux")||(Module_target.g_isPlatformFix&&Module_target.f_getIsIosPlatform()))||Module_string.f_strContains(Native.toLowerCase(Module_target.g_userAgent.value), "linux")));
			Module_target.g_isIos.value = ((Boolean)((Module_target.g_majorOsPart.value).equals("iOS")||(Module_target.g_isPlatformFix&&Module_target.f_getIsIosPlatform())));
			Module_target.g_isAndroid.value = ((Boolean)((Module_target.g_majorOsPart.value).equals("Android")||(Module_target.g_isPlatformFix&&Module_target.f_isAndroidPlatform())));
			final Object l3_flashCheck = Native.hostCall("getVersion", SingletonStructs.arr_empty);
			((Reference<Object>)(Reference)Module_target.g_flashVersion).value = ((!(Module_dynamic.f_toString(l3_flashCheck)).equals("{}"))?l3_flashCheck:"other");
			final Object l4_browserCheck = Native.hostCall("getBrowser", SingletonStructs.arr_empty);
			((Reference<Object>)(Reference)Module_target.g_browser).value = ((!(Module_dynamic.f_toString(l4_browserCheck)).equals("{}"))?l4_browserCheck:"other");
			final Object l5_resolutionCheck = Native.hostCall("getResolution", SingletonStructs.arr_empty);
			((Reference<Object>)(Reference)Module_target.g_resolution).value = ((!(Module_dynamic.f_toString(l5_resolutionCheck)).equals("{}"))?l5_resolutionCheck:"other");
			final Object l6_deviceTypeCheck = Native.hostCall("getDeviceType", SingletonStructs.arr_empty);
			((Reference<Object>)(Reference)Module_target.g_deviceType).value = ((!(Module_dynamic.f_toString(l6_deviceTypeCheck)).equals("{}"))?l6_deviceTypeCheck:"other");
			return null;
		} else {
			return null;
		}
	}
	public static final boolean f_ios() {
		Module_target.f_initOsParts();
		return ((boolean)Module_target.g_isIos.value);
	}
	public static final boolean f_isAndroidPlatform() {
		return false;
	}
	public static final boolean f_isWindowsPlatform() {
		return false;
	}
	public static final boolean f_linux() {
		Module_target.f_initOsParts();
		return ((boolean)Module_target.g_isLinux.value);
	}
	public static final boolean f_macosx() {
		Module_target.f_initOsParts();
		return ((boolean)Module_target.g_isMacOSX.value);
	}
	public static final boolean f_windows() {
		Module_target.f_initOsParts();
		return ((boolean)Module_target.g_isWindows.value);
	}
	public static final boolean f_windowsxp() {
		final String l0_ua = Native.toLowerCase(Module_target.f_getUserAgent());
		return (Module_string.f_strContains(l0_ua, "windows nt 5.1")||Module_string.f_strContains(l0_ua, "windows xp"));
	}
}
