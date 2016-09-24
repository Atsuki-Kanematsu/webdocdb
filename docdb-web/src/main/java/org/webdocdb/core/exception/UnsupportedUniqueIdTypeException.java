package org.webdocdb.core.exception;

@SuppressWarnings("serial")
public class UnsupportedUniqueIdTypeException extends RuntimeException {

	public UnsupportedUniqueIdTypeException() {
		super();
	}

	public UnsupportedUniqueIdTypeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnsupportedUniqueIdTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedUniqueIdTypeException(String message) {
		super(message);
	}

	public UnsupportedUniqueIdTypeException(Throwable cause) {
		super(cause);
	}

}
