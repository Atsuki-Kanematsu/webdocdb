package org.webdocdb.core.exception;

public class DocumentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DocumentNotFoundException() {
		super();
	}

	public DocumentNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public DocumentNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DocumentNotFoundException(String arg0) {
		super(arg0);
	}

	public DocumentNotFoundException(Throwable arg0) {
		super(arg0);
	}

}
