package com.cts.customer_statement_service.model;

public class RequestRecord {

	private int transactionRef;
	private String accountNum;
	private Long startBalance;
	private Long mutation;
	private String description;
	private Long endBalance;
	public int getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(int transactionRef) {
		this.transactionRef = transactionRef;
	}
	public Long getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(Long startBalance) {
		this.startBalance = startBalance;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public Long getMutation() {
		return mutation;
	}
	public void setMutation(Long mutation) {
		this.mutation = mutation;
	}
	public Long getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(Long endBalance) {
		this.endBalance = endBalance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
