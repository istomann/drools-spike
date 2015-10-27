package fi.ima.drools_spike.dto;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String country;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String country) {
		super();
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
