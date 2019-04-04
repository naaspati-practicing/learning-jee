package com.tad.arquillian.chap1;

import static org.junit.Assert.*;

import java.util.Random;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CalculatorTest {
	
	@Deployment
	public static JavaArchive pack () {
		return ShrinkWrap.create(JavaArchive.class, "foo.jar")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addPackage(CalculatorData.class.getPackage());
	} 
	
	@Inject CalculatorForm form;
	@Inject CalculatorController controller;

	@Test
	public void testInjectedCalculator() {
		Random r = new Random();
		int x = r.nextInt();
		int y = r.nextInt();
		int z = r.nextInt();
		
		form.setX(x);
		form.setY(y);
		form.setZ(z);
		
		Utils.printStack("test: "+form+"@"+System.identityHashCode(form));
		
		controller.sum();
		assertEquals(x+y+z, form.getSum());
	}
}
