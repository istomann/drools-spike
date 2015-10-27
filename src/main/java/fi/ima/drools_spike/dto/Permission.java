package fi.ima.drools_spike.dto;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

	private String permissionClass;

	private String permissionCountry;

	private Customer customer;

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(String permissionClass, String permissionCountry) {
		super();
		this.permissionClass = permissionClass;
		this.permissionCountry = permissionCountry;
	}

	public String getPermissionClass() {
		return permissionClass;
	}

	public void setPermissionClass(String permissionClass) {
		this.permissionClass = permissionClass;
	}

	public String getPermissionCountry() {
		return permissionCountry;
	}

	public void setPermissionCountry(String permissionCountry) {
		this.permissionCountry = permissionCountry;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
