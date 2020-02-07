package com.cts.customer_statement_service.model;

public class ResponseRecord {

	private int transactionRef;
	private String description;
	public int getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(int transactionRef) {
		this.transactionRef = transactionRef;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
