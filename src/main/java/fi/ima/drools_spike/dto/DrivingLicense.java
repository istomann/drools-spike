package fi.ima.drools_spike.dto;

import java.io.Serializable;

public class DrivingLicense implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;
	private String permissionClass;
	private String country;

	public DrivingLicense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DrivingLicense(String customerId, String permissionClass, String country) {
		super();
		this.customerId = customerId;
		this.permissionClass = permissionClass;
		this.country = country;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPermissionClass() {
		return permissionClass;
	}

	public void setPermissionClass(String permissionClass) {
		this.permissionClass = permissionClass;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
