package com.db.model;

import java.util.Date;

public class Agenda {
	
	Date date;

	Item[] detail;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Item[] getDetail() {
		return detail;
	}

	public void setDetail(Item[] detail) {
		this.detail = detail;
	}
	
	
	
}
