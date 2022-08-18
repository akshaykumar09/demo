package com.ConsignmentNoteNumberGenerator.demo.Exceptions;

public class ConsignmentException extends Exception{

	private static final long serialVersionUID = 1L;

	    public ConsignmentException(String message) {
	        super(message);
	    }

	    public ConsignmentException() {
	        super();
	    }

	    public ConsignmentException(final String message, final Throwable cause) {
	        super(message, cause);
	    }
	public ConsignmentException(final Throwable cause) {
	        super(cause);
	    }

	}