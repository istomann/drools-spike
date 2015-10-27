package fi.ima.drools_spike.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;
	private Date dateOfBirth;
	private boolean valid = true;
	private List<String> invalidReasons = new ArrayList<String>();
	private Address address;
	private List<Permission> permissions = new ArrayList<Permission>();

	public Customer() {
		super();
	}

	public Customer(String customerId, Date dateOfBirth, Address address) {
		super();
		this.customerId = customerId;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
	public List<String> getInvalidReasons() {
		return invalidReasons;
	}

	public void setInvalidReasons(List<String> invalidReasons) {
		this.invalidReasons = invalidReasons;
	}

	public void invalidate(String invalidReason) {
		setValid(false);
		getInvalidReasons().add(invalidReason);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public void addPermission(Permission permission) {
		getPermissions().add(permission);
		permission.setCustomer(this);
	}

}
