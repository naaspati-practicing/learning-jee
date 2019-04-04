package com.tad.arquillian.chap1;

import java.util.Arrays;

public class CalculatorData {
	private int[] data;
	private int result;
	
	public CalculatorData(int...data) {
		this.data = data;
	}
	
	public int[] getData(){ return this.data; }
	public void setData(int[] data){ this.data = data; }

	public int getResult(){ return this.result; }
	public void setResult(int result){ this.result = result; }
	
	@Override
	public String toString() {
		return "CalculatorData [data=" + Arrays.toString(data) + ", result=" + result + "]";
	}
}
