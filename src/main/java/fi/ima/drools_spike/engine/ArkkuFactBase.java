package fi.ima.drools_spike.engine;

import java.util.List;
import java.util.ArrayList;

public class ArkkuFactBase {

	public List<Object> facts;

	public ArkkuFactBase() {
		facts = new ArrayList<Object>();
	}

	public ArkkuFactBase(List<Object> facts) {
		this.facts = facts;
	}

	public void addFact(Object fact) {
		facts.add(fact);
	}
	
	public void addFacts(List<? extends Object> facts) {
		this.facts.addAll(facts);
	}

	public List<Object> getFacts() {
		return facts;
	}

	public void setFacts(List<Object> facts) {
		this.facts = facts;
	}

}
