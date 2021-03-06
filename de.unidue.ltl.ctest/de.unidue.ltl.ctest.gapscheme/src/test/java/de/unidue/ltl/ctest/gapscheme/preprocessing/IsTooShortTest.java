package de.unidue.ltl.ctest.gapscheme.preprocessing;

import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasBuilder;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import junit.framework.TestCase;

public class IsTooShortTest extends TestCase {
	
	@Test
	public void testSingleChar() throws UIMAException {		
		JCas jcas = JCasFactory.createJCas();		
		JCasBuilder jcasBuilder = new JCasBuilder(jcas);
		
		Token tooShort = jcasBuilder.add("I", Token.class);		
		Token longEnough = jcasBuilder.add("Idiosynkrasie", Token.class);
		
		jcasBuilder.close();

		IsTooShort criterion = new IsTooShort();
		
		assertTrue(criterion.test(tooShort));
		assertFalse(criterion.test(longEnough));
	}
}
