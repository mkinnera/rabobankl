package com.cts.rabobank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestRecord {
	@XmlAttribute(name = "reference")
	private int transactionRef;
	private String accountNum;
	private Long startBalance;
	private Long mutation;
	private String description;
	private Long endBalance;
	private boolean isValid;

	private static final NumberFormat formatter = new DecimalFormat("#0.00");

	public void checkBalanceValidation(){
		this.isValid=Long.parseLong(formatter.format((this.getStartBalance() + this.getMutation()))) == Long.parseLong(formatter.format(this.getEndBalance()));
	}


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

	@JsonIgnore
	@JsonProperty(value = "valid")
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean valid) {
		isValid = valid;
	}
}
