package org.webdocdb.core.util;

public class StringUtil {

	public static boolean isEmpty(String value) {
		if (value == null || value.equals("")) {
			return true;
		}
		return false;
	}
	
	public static String concats(String[] values, String separator) {
		StringBuffer sb = new StringBuffer();
		for (String value : values) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(value);
		}
		return sb.toString();
	}
}
