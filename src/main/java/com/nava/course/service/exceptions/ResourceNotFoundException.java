package com.nava.course.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		// TODO Auto-generated constructor stub

		super("Resource Not Found id " + id);

	}

}
