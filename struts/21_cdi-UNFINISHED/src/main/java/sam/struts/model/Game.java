package sam.struts.model;

import javax.enterprise.inject.Model;

@Model
public class Game {
	private int x;
	private int y;
	private int sum;
	
	public boolean check() { return x + y == sum;}
	public String supposedToBe() { return String.valueOf(sum);}

	public int getX() {return x;}
	public void setX(int x) {this.x = x;}

	public int getY() {return y;}
	public void setY(int y) {this.y = y;}

	public int getSum() {return sum;}
	public void setSum(int sum) {this.sum = sum;}
	

}
