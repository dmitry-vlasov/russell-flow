package com.area9innovation.flow;

import java.util.HashMap;

public class NativeHashMap extends NativeHost {

	public static final <K, V> HashMap<K, V> init() {
		return new HashMap<K, V>();
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Func1<Integer, K> hash(Object map) {
		return new Func1<Integer, K>() {
			public Integer invoke(K k) {
				return k.hashCode();
			}
		};
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Object set(Object map, K key, V val) {
		((HashMap<K, V>) map).put(key, val);
		return null;
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> V get(Object map, K key, V val) {
		return ((HashMap<K, V>) map).getOrDefault(key, val);
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Boolean contains(Object map, K key) {
		return ((HashMap<K, V>) map).containsKey(key);
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Object remove(Object map, Object key) {
		HashMap<Object, Object> hashMap = (HashMap<Object, Object>) map;
		hashMap.remove(key);
		return null;
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> int size(Object map) {
		return ((HashMap<K, V>) map).size();
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Object clear(Object map) {
		((HashMap<K, V>) map).clear();
		return null;
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Object clone(Object map) {
		return ((HashMap<K, V>) map).clone();
	}

	@SuppressWarnings (value="unchecked")
	public static final <K, V> Object iter(Object map, Func2<Object, K, V> fn) {
		((HashMap<K, V>) map).forEach(new java.util.function.BiConsumer<K, V>() {
			public void accept(K k, V v) {
				fn.invoke(k, v);
			}
		});
		return null;
	}
}
