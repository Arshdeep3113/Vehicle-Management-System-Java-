package clientRelated;
//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Client class creates a object with two variables of type String: name and driver license. 
 */

public class Client {
	protected String name;
	protected String driverLicense;
	
	//default constructor
	public Client () {
		this.name = "NO NAME YET";
		this.driverLicense = "NAN";
	}
	
	//parametrized constructor
	public Client (String name, String driverLicense) {
		this.name = name;
		this.driverLicense = driverLicense;
		
	}
	
	//copy constructor
	public Client(Client otherClient) {
		this (otherClient.name, otherClient.driverLicense);
	}
	
	//getters
	public String getDriverLicense () {
		return this.driverLicense;
	}

	public String getName () {
		return this.name;
	}
	
	//setters
	public void setName (String name) {
		this.name = name;
	}
	
	public void setDriverLicense (String driverLicense) {
		this.driverLicense = driverLicense;
	}
	
	//toString
	@Override
	public String toString () {
		return this.name + ", " + this.driverLicense;
	}
	
	//equals
	@Override 
	public boolean equals (Object otherObject) {
		if (otherObject == null) {
			return false;
		}
		
		if (this.getClass() != otherObject.getClass()) {
			return false;
		}
		
		Client otherClient = (Client) otherObject;
		return this.name.equalsIgnoreCase(otherClient.name) 
				&& this.driverLicense.equalsIgnoreCase(otherClient.driverLicense);
	}

}
