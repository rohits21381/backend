package com.skyrics.app.entities;

import lombok.Data;

@Data
public class PamentInformation {

	private String billingAddress;
	private String saveInformation;
	private String pamentType;
	private String nameOnCard;
	private String creditCardNumber;
	private String expiration;
	private String cvv;
}
