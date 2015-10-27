package fi.ima.drools_spike;

import org.joda.time.DateTime;
import org.kie.api.io.ResourceType;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fi.ima.drools_spike.dto.Address;
import fi.ima.drools_spike.dto.Customer;
import fi.ima.drools_spike.dto.DrivingLicense;
import fi.ima.drools_spike.dto.Permission;
import fi.ima.drools_spike.engine.ArkkuRuleEngine;
import fi.ima.drools_spike.engine.ArkkuRuleRequest;

public class SpikeRunner {

	ArkkuRuleEngine engine;

	public SpikeRunner(ArkkuRuleEngine engine) {
		this.engine = engine;
	}

	public void runWithDroolsFile() {
		run("spike-rules.drl", ResourceType.DRL);
	}
	
	public void runWithDecisionTable() {
		run("spike-decision-table.xls", ResourceType.DTABLE);
	}
	
	private void run(String resourceName, ResourceType resourceType) {
		CustomerFactBase factBase = createFactBase2();
		engine.evaluate(new ArkkuRuleRequest(resourceName, resourceType, factBase));
		logResults(factBase);		
	}

	private CustomerFactBase createFactBase() {
		Customer customer = new Customer("C1", new DateTime(2001, 6, 4, 0, 0).toDate(), new Address("fi"));
		customer.addPermission(new Permission("B", "fi"));
		DrivingLicense license = new DrivingLicense("C1", "B", "fi");

		CustomerFactBase factBase = new CustomerFactBase(customer);
		factBase.addFact(license);

		return factBase;
	}

	private CustomerFactBase createFactBase2() {
		Customer customer = new Customer("C1", new DateTime(2001, 6, 4, 0, 0).toDate(), new Address("de"));
		customer.addPermission(new Permission("B", "fi"));
		DrivingLicense license = new DrivingLicense("C1", "B", "fi");

		CustomerFactBase factBase = new CustomerFactBase(customer);
		factBase.addFact(license);

		return factBase;
	}	
	
	private void logResults(CustomerFactBase factBase) {
		Customer customer = factBase.getCustomer();
		
		StringBuilder reasons = new StringBuilder();
		for (String reason : customer.getInvalidReasons()) {
			reasons.append(reason).append(", ");
		}
		
		System.out.println("Customer after evaluation, valid=" + customer.isValid() + ", reasons=" + reasons.toString());
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = null;
		try {
			applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
			ArkkuRuleEngine engine = applicationContext.getBean(ArkkuRuleEngine.KEY, ArkkuRuleEngine.class);

			SpikeRunner runner = new SpikeRunner(engine);
			runner.runWithDroolsFile();
			//runner.runWithDecisionTable();
		} finally {
			applicationContext.close();
		}

	}

}
