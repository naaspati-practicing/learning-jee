package com.tad.arquillian.chap1;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CalculatorController {

	@Inject
	private CalculatorForm form;
	@Inject
	private CalculatorService service;
	
	public void sum() {
		CalculatorData data = new CalculatorData(form.getX(), form.getY(), form.getZ());
		service.computeSum(data);
		form.setSum(data.getResult());
		
		Utils.printStack(form+"@"+System.identityHashCode(form));
	}
}
