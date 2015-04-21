package com.db.crud;

public class NullDocumentException extends RuntimeException {

	private static final long serialVersionUID = 2942378367179849171L;

	public NullDocumentException() {
		super("The document you want to insert into db is empty!");
	}

}
