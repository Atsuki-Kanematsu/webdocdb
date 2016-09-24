package org.webdocdb.core.util;

public class ArrayUtil {

	public static <T> boolean contains(T[] values, T value) {
		for (T t: values) {
			if (t.equals(value)) {
				return true;
			}
		}
		return false;
	}
}
