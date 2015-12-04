package com.joeint.vivid.Quiz;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private String quotient;
	private int remainder;
	List<String> productArray = new ArrayList<>();
	List<String> subtrahendArray = new ArrayList<>();
	
	public String getQuotient() {
		return quotient;
	}
	public void setQuotient(String quotient) {
		this.quotient = quotient;
	}
	public int getRemainder() {
		return remainder;
	}
	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}
	public List<String> getProductArray() {
		return productArray;
	}
	public void setProductArray(List<String> productArray) {
		this.productArray = productArray;
	}
	public List<String> getSubtrahendArray() {
		return subtrahendArray;
	}
	public void setSubtrahendArray(List<String> subtrahendArray) {
		this.subtrahendArray = subtrahendArray;
	}

}
