package org.webdocdb.core.exception;

public class CollectionAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CollectionAccessException() {
		super();
	}

	public CollectionAccessException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CollectionAccessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CollectionAccessException(String arg0) {
		super(arg0);
	}

	public CollectionAccessException(Throwable arg0) {
		super(arg0);
	}

}
