package com.tad.arquillian.chap1;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CalculatorForm {
	private int x;
	private int y;
	private int z;
	private int sum;
	
	public int getX(){ return this.x; }
	public void setX(int x){ this.x = x; }

	public int getY(){ return this.y; }
	public void setY(int y){ this.y = y; }

	public int getZ(){ return this.z; }
	public void setZ(int z){ this.z = z; }

	public int getSum(){ return this.sum; }
	public void setSum(int sum){ this.sum = sum; }
	@Override
	public String toString() {
		return "CalculatorForm [x=" + x + ", y=" + y + ", z=" + z + ", sum=" + sum + "]";
	}
}
