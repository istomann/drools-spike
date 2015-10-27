package fi.ima.drools_spike.engine;

import org.kie.api.io.ResourceType;

public class ArkkuRuleRequest {

	private String resourceName;
	private ResourceType resourceType;
	private ArkkuFactBase factBase;

	public ArkkuRuleRequest(String resourceName, ResourceType resourceType, ArkkuFactBase factBase) {
		super();
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.factBase = factBase;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public ArkkuFactBase getFactBase() {
		return factBase;
	}

	public void setFactBase(ArkkuFactBase factBase) {
		this.factBase = factBase;
	}

}
