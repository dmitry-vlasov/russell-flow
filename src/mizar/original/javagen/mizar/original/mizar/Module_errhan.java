// HASH COLLISIONS: YES
// timestamp: 1785496853000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_errhan {
	public static final Object f_mizAssert(int aerrorCode, boolean acond) {
		if (!acond) {
			return Native.failWithError(("Mizar assertion failed: "+Module_string.f_i2s(aerrorCode)));
		} else {
			return null;
		}
	}
	public static final Object f_mizRunTimeError(int aerrorCode) {
		return Native.failWithError(("Mizar RunTimeError "+Module_string.f_i2s(aerrorCode)));
	}
}
