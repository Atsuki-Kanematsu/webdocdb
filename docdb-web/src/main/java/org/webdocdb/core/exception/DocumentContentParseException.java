package org.webdocdb.core.exception;

public class DocumentContentParseException extends Throwable {

	private static final long serialVersionUID = 1L;

	public DocumentContentParseException() {
		super();
	}

	public DocumentContentParseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DocumentContentParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentContentParseException(String message) {
		super(message);
	}

	public DocumentContentParseException(Throwable cause) {
		super(cause);
	}

}
