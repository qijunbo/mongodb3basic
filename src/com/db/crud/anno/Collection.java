package com.db.crud.anno;

public @interface Collection {

	/**
	 * @return which collection shall this POJO be stored
	 */
	String name();
}
