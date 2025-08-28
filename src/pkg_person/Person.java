package pkg_person;

import java.io.Serializable;
import java.util.regex.Pattern;

abstract public class Person implements Serializable {
     protected String name;
     protected String emailaId ; 
     protected String phoneNumber;
     protected String address;
     protected String dob;
     
	public String getName() {
		return name;
	}
	public void setName(String name) {
		boolean isValidName = Pattern.matches("[a-zA-Z]+", name);
		if(isValidName)
		    this.name = name;
		else
			this.name = "default name";
	}
	public String getEmailaId() {
		return emailaId;
	}
	public void setEmailaId(String emailaId) {
		this.emailaId = emailaId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		boolean isValidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		if(isValidDob)
		   this.dob = dob;
		else
			this.dob = "01-06-2005";
	}
	
	public Person(String name, String emailaId, String phoneNumber, String address, String dob) {
		super();
		this.setName(name);
		this.emailaId = emailaId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setDob(dob);
	}
	public Person() {
		super();
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", emailaId=" + emailaId + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", dob=" + dob + "]";
	}
     
     
}
