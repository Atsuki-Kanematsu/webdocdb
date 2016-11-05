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

	public static String toLowerCamel(String value) {
		int pos = 0;
		do {
			if (value.startsWith("_")) {
				value = value.substring(1);
			}
		} while (value.startsWith("_"));
		
		value = String.format("%s%s", value.substring(0, 1).toLowerCase(), value.substring(1));
		pos = value.indexOf("_");
		do {
			pos = value.indexOf("_");
			if (pos < 0) {
				break;
			}
			String before = value.substring(0, pos);
			String wordTop = value.substring(pos + 1, pos + 2).toUpperCase();
			String after = value.substring(pos + 2);
			value = String.format("%s%s%s", before, wordTop, after);
			pos = value.indexOf("_");
		} while(pos >= 0);
		
		return value;
	}

	public static String toUpperCamel(String value) {
		int pos = 0;
		do {
			if (value.startsWith("_")) {
				value = value.substring(1);
			}
		} while (value.startsWith("_"));
		
		value = String.format("%s%s", value.substring(0, 1).toUpperCase(), value.substring(1));
		pos = value.indexOf("_");
		do {
			pos = value.indexOf("_");
			String before = value.substring(0, pos);
			String wordTop = value.substring(pos + 1, pos + 2).toUpperCase();
			String after = value.substring(pos + 2);
			value = String.format("%s%s%s", before, wordTop, after);
			pos = value.indexOf("_");
		} while(pos >= 0);
		
		return value;
	}

}
