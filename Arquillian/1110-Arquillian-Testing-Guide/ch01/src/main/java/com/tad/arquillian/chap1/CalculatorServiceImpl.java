package com.tad.arquillian.chap1;

import java.util.Optional;
import java.util.stream.IntStream;

public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public void computeSum(CalculatorData d) {
		int sum = Optional.ofNullable(d.getData())
				.filter(f -> f.length != 0)
				.map(array -> IntStream.of(array).sum())
				.orElse(0);
		
		d.setResult(sum);
		
		Utils.printStack(d+"@"+System.identityHashCode(d));
	}

}
