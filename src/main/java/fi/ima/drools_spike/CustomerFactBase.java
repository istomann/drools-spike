package fi.ima.drools_spike;

import fi.ima.drools_spike.dto.Customer;
import fi.ima.drools_spike.engine.ArkkuFactBase;

public class CustomerFactBase extends ArkkuFactBase {

	private Customer customer;

	public CustomerFactBase(Customer customer) {
		super();
		this.customer = customer;
		
		super.addFact(customer);
		super.addFact(customer.getAddress());
		super.addFacts(customer.getPermissions());
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
}
