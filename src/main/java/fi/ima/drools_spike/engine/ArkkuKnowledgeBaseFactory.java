package fi.ima.drools_spike.engine;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.io.ResourceType;
import org.springframework.stereotype.Service;

@Service(value = ArkkuKnowledgeBaseFactory.KEY)
public class ArkkuKnowledgeBaseFactory {
	
	public static final String KEY = "ArkkuKnowledgeBaseFactory";
	
	private Map<String, ArkkuKnowledgeBase> knowledgebases = new HashMap<String, ArkkuKnowledgeBase>();
	
	public ArkkuKnowledgeBaseFactory() {
		System.setProperty("drools.dateformat","yyyy-MM-dd");
	}
	
	public ArkkuKnowledgeBase getKnowledgeBase(String droolsResName, ResourceType resourceType) {
		ArkkuKnowledgeBase arkkuKnowledgeBase = knowledgebases.get(droolsResName);
		if (arkkuKnowledgeBase == null) {
			arkkuKnowledgeBase = createKnowledgeBase(droolsResName, resourceType);
		}
		return arkkuKnowledgeBase;
	}
	
	
	private synchronized ArkkuKnowledgeBase createKnowledgeBase(String droolsResName, ResourceType resourceType) {
		ArkkuKnowledgeBase arkkuKnowledgeBase = knowledgebases.get(droolsResName);
		if (arkkuKnowledgeBase == null) {
			arkkuKnowledgeBase = new ArkkuKnowledgeBase(droolsResName, resourceType);
			knowledgebases.put(droolsResName, arkkuKnowledgeBase);
		}
		return arkkuKnowledgeBase;
	}
	
}
