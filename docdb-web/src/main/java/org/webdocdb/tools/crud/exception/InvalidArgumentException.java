package org.webdocdb.tools.crud.exception;

@SuppressWarnings("serial")
public class InvalidArgumentException extends Throwable {

	public InvalidArgumentException() {
		super();
	}

	public InvalidArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

}
