package com.area9innovation.flow;

//import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RussellTimed extends NativeHost {
	@SuppressWarnings (value="unchecked")
	public static final <K, V> Object iterHashMapTimed(Object map, Func2<Object, K, V> fn, double time_limit) {
		HashMap<K, V> hmap = (HashMap<K, V>)map;
		final long started = System.currentTimeMillis();
		final long limit = (time_limit > 0) ? started + (long)time_limit : -1;
		final Set<Map.Entry<K, V>> set = hmap.entrySet();
		final Iterator<Map.Entry<K, V>> iter = set.iterator();
		while (iter.hasNext()) {
			Map.Entry<K, V> entry = iter.next();
			fn.invoke(entry.getKey(), entry.getValue());
			if (limit > 0 && System.currentTimeMillis() > limit) {
				break;
			}
		}
		return null;
	}
}