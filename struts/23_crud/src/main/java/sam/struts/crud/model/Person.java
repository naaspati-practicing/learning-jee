package sam.struts.crud.model;

public class Person {
	private int personId;
    private String firstName;
    private String lastName;
    private String sport;
    private String gender;
    private Country country = new Country("", "");
    private boolean over21;
    private String[] carModels;
    private String email;
    private String phoneNumber;
    
    public Person()  { }
    public Person(Integer id, String firstName, String lastName, String sport, 
                String gender, Country country, boolean over21, String[] carModels, 
                String email, String phoneNumber) {
        this.personId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.gender = gender;
        this.country = country;
        this.over21 = over21;
        this.carModels = carModels;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
	public int getPersonId() {return personId;}
	public void setPersonId(int personId) {this.personId = personId;}
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public String getSport() {return sport;}
	public void setSport(String sport) {this.sport = sport;}
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}
	public Country getCountry() {return country;}
	public void setCountry(Country country) {this.country = country;}
	public boolean isOver21() {return over21;}
	public void setOver21(boolean over21) {this.over21 = over21;}
	public String[] getCarModels() {return carModels;}
	public void setCarModels(String[] carModels) {this.carModels = carModels;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
