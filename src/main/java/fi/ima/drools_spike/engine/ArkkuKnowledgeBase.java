package fi.ima.drools_spike.engine;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class ArkkuKnowledgeBase {

	private Logger log = Logger.getLogger(ArkkuKnowledgeBase.class);

	private KieBase droolsKnowledgeBase;

	public ArkkuKnowledgeBase(String resourceName) {
		this(resourceName, ResourceType.DRL);
	}
	
	public ArkkuKnowledgeBase(String resourceName, ResourceType resourceType) {
		if (ResourceType.DRL.equals(resourceType)) {
			String drools = getResourceFileContents(resourceName);
			droolsKnowledgeBase = loadRules(drools);
		} else if (ResourceType.DTABLE.equals(resourceType)) {
			droolsKnowledgeBase = loadDecisionTable(resourceName);
		} else {
			throw new IllegalArgumentException("Resource type " + resourceType + " not valid in ArkkuKnowledgeBase");
		}
	}

	public KieSession createSession() {
		return droolsKnowledgeBase.newKieSession();
	}

	KieBase loadRules(String drools) {
		KnowledgeBase kieBase = KnowledgeBaseFactory.newKnowledgeBase();
		
		Resource droolsResource = ResourceFactory.newByteArrayResource(drools.getBytes());

		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(droolsResource, org.kie.api.io.ResourceType.DRL);
		validate(kBuilder);
		
		kieBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
		return kieBase;
	}
	
	KieBase loadDecisionTable(String decisionTableResourceName) {
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("rules/" + decisionTableResourceName);
		SpreadsheetCompiler sc = new SpreadsheetCompiler();
		StringBuffer drl= new StringBuffer(sc.compile(input, InputType.XLS));
		
		System.out.println(drl.toString());
		return loadRules(drl.toString());
	}

	KieBase loadDecisionTableDirect(String decisionTableResourceName) {
		logDecisionTableAsDrools(decisionTableResourceName);
		KnowledgeBase kieBase = KnowledgeBaseFactory.newKnowledgeBase();
		
		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);

		KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kBuilder.add(ResourceFactory.newClassPathResource("rules/" + decisionTableResourceName), ResourceType.DTABLE, dtconf);
		validate(kBuilder);

		kieBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
		return kieBase;
	}
	
	private void logDecisionTableAsDrools(String decisionTableResourceName) {
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("rules/" + decisionTableResourceName);
		SpreadsheetCompiler sc = new SpreadsheetCompiler();
		StringBuffer drl= new StringBuffer(sc.compile(input, InputType.XLS));
		System.out.println(drl.toString());
	}

	String getResourceFileContents(String resourceName) {
		String name = "rules/" + resourceName;
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		try {
			if (input == null) {
				throw new RuntimeException("Input stream for " + resourceName + " is null for " + name + " when using Thread.currentThread().getContextClassLoader()");
			}
			String s = IOUtils.toString(input);
			return s;
		} catch (IOException e) {
			throw new RuntimeException("Error in reading file contents for " + name, e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.debug("Closing an input stream failed.", e);
				}
			}
		}
	}

	private void validate(KnowledgeBuilder kbuilder) {
		if (kbuilder.hasErrors()) {
			StringBuilder msg = new StringBuilder();
			for (KnowledgeBuilderError ebe : kbuilder.getErrors()) {
				msg.append("Error:").append(ebe.getMessage()).append(". ");
			}
			throw new IllegalArgumentException("KnowledgeBuilder failed while compiling a drools resource of RiskRule " + ". Errors: " + msg.toString());
		}
	}

}
