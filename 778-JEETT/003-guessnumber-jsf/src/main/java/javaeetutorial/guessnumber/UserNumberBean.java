package javaeetutorial.guessnumber;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserNumberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int randomNumber;
	private Integer userInput;
	
	private int max = 10;
	private int min = 0;
	
	public UserNumberBean() {
		Random random = new Random();
		randomNumber = random.nextInt(max + 1);
		
		System.out.println("Duke's number: " + randomNumber);
	}

	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public Integer getUserInput() {
		return userInput;
	}
	public void setUserInput(Integer userInput) {
		this.userInput = userInput;
	}
	public String getResponse() {
		if(userInput == null)
			return "no input";
		int compare = Integer.compare(userInput, randomNumber); 
		if(compare == 0)
		return "Yay! You got it!";
		
		return "Sorry, "+userInput+" is incorrect. ("+(compare < 0 ? "low" : "high")+")";
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
}

