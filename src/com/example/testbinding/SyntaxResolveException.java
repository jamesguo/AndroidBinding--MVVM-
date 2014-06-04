package com.example.testbinding;

public class SyntaxResolveException extends Exception {
	private static final long serialVersionUID = -5339580312141946507L;

	public SyntaxResolveException() {
		super();
	}

	public SyntaxResolveException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public SyntaxResolveException(String detailMessage) {
		super(detailMessage);
	}

	public SyntaxResolveException(Throwable throwable) {
		super(throwable);
	}
}
