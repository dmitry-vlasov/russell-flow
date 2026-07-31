// HASH COLLISIONS: YES
// timestamp: 1726347685000

package mizar.original.mizar;

import com.area9innovation.flow.*;

@SuppressWarnings("unchecked")
final public class Module_loop {
	public static final Object f_while(Func0<Boolean> acondition, Func0<Object> aaction) {
		TAIL_CALL: for(;;) {
		if (((boolean)acondition.invoke())) {
			aaction.invoke();
			{
				continue TAIL_CALL;
			}
		} else {
			return null;
		}
	}
	}
}
