package com.area9innovation.flow;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// A STREAMING FILE WRITER, because setFileContent takes the whole file as one
// string. Metamath export measured on 2026-08-13: 4 s at 23 MB, 16 s at 35 MB,
// 8 min at 116 MB — super-linear, and the same single string is why the JVM
// peaked at 17 GB and the kernel OOM killer fired. Writing declaration by
// declaration through a buffered stream is linear and needs no big allocation.
public class RussellStream extends NativeHost {
	private static final ConcurrentHashMap<Integer, BufferedWriter> writers = new ConcurrentHashMap<>();
	private static final AtomicInteger nextId = new AtomicInteger(1);

	// Open `path` for writing; returns a handle, or 0 on failure.
	public static final Integer streamOpen(String path) {
		try {
			BufferedWriter w = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(path, false), StandardCharsets.UTF_8),
				1 << 20);
			Integer id = nextId.getAndIncrement();
			writers.put(id, w);
			return id;
		} catch (IOException e) {
			return 0;
		}
	}

	public static final Boolean streamWrite(Integer handle, String s) {
		BufferedWriter w = writers.get(handle);
		if (w == null) return false;
		try {
			w.write(s);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static final Boolean streamClose(Integer handle) {
		BufferedWriter w = writers.remove(handle);
		if (w == null) return false;
		try {
			w.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
