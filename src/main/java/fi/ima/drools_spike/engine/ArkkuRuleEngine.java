package fi.ima.drools_spike.engine;

import java.util.List;

import javax.annotation.Resource;

import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service(value = ArkkuRuleEngine.KEY)
public class ArkkuRuleEngine {

	public static final String KEY = "ArkkuRuleEngine";
	
	@Resource(name = ArkkuKnowledgeBaseFactory.KEY)
	ArkkuKnowledgeBaseFactory kbFactory;
	
	public void evaluate(ArkkuRuleRequest request) {
		ArkkuKnowledgeBase knowledgeBase = kbFactory.getKnowledgeBase(request.getResourceName(), request.getResourceType());
		KieSession knowledgeSession = knowledgeBase.createSession();
		
		List<Object> facts = request.getFactBase().getFacts();
        for (Object fact : facts) {
        	knowledgeSession.insert(fact);
        }
		
		knowledgeSession.fireAllRules();
	}
	
}
