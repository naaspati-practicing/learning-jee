package book.pojos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlayerDetails {
	private int age;
	private LocalDate birthday;
	private String birthplace;
	private String residence;
	private int height;
	private int weight;
	private PlayerMoreDetails moreDetails;
	
	public int getAge() {
		return age;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		this.age = (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int width) {
		this.weight = width;
	}
	public PlayerMoreDetails getMoreDetails() {
		return moreDetails;
	}
	public void setMoreDetails(PlayerMoreDetails moreDetails) {
		this.moreDetails = moreDetails;
	}
}
