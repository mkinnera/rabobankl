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
public class ValidationRequest {
	@XmlAttribute(name = "reference")
	private int transactionRef;

	private String accountNumber;
	private double startBalance;

	private double mutation;

	private String description;

	private double endBalance;
	private boolean isValid;

	private static final NumberFormat formatter = new DecimalFormat("#0.00");

	public void checkBalanceValidation(){
		this.isValid=Double.parseDouble(formatter.format((this.getStartBalance() + this.getMutation()))) == Double.parseDouble(formatter.format(this.getEndBalance()));
	}

	@JsonProperty(value="Reference")
	public int getTransactionRef() {
		return transactionRef;
	}
	public void setTransactionRef(int transactionRef) {
		this.transactionRef = transactionRef;
	}
	@JsonProperty(value="Start Balance")
	public double getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@JsonProperty(value="Mutation")
	public double getMutation() {
		return mutation;
	}
	public void setMutation(double mutation) {
		this.mutation = mutation;
	}
	@JsonProperty(value="End Balance")
	public double getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	@JsonProperty(value = "valid")
	public boolean isValid() {
		return isValid;
	}

}
