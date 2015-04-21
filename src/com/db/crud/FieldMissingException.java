package com.db.crud;

public class FieldMissingException extends RuntimeException {

	 
	private static final long serialVersionUID = 1102191848642362863L;

	public FieldMissingException(String field) {

		super("Can not find the filed [" + field + "] in the map.");
		
	}
	
	
 

}
