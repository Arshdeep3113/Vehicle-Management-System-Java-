package vehicles;
//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Vehicle class creates an object with holds these variables: make of the vehicle, model and year.
 * The plate number is not initialized since it is unique to each vehicle type. 
 */
public class Vehicle {
	protected String plateNumber;
	protected String make; 
	protected String model;
	protected int yearOfProduction;

	//default constructor
	public Vehicle () {
		this.make = "No make yet";
		this.model = "No model yet"; 
		this.yearOfProduction = 0;
		this.plateNumber = "No plate yet";
	}
	
	//parametrized constructor
	public Vehicle (String make, String model, int yearOfProduction) {
		this.make = make; 
		this.model = model;
		this.yearOfProduction = yearOfProduction;
	}
	
	//copy contructor
	public Vehicle (Vehicle otherVehicules) {
		this(otherVehicules.make, otherVehicules.model, otherVehicules.yearOfProduction);
	}
	
	//getters
	public String getMake () {
		return this.make;
	}
	
	public String getModel () {
		return this.model;
	}
	
	public String getPlateNumber () {
		return this.plateNumber;
	}
	
	public int getYearOfProduction () {
		return this.yearOfProduction;
	}
	
	public void setMake (String make) {
		this.make = make;
	}
	
	//setters
	public void setModel (String model) {
		this.model = model; 
	}
	
	public void setYearOfProduction (int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	
	public String displayYearMakeModel () {
		return this.yearOfProduction + " " + this.make + " " + this.model;
	}
	
	//toString
	@Override
	public String toString() {
		return "Make: " + this.make + "\nModel: " + this.model + "\nYear of production: " 
	+ this.yearOfProduction;
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
		
		Vehicle otherVehicules = (Vehicle) otherObject;
		
		return (this.make.equals(otherVehicules.make)) && 
				(this.model.equals(otherVehicules.model)) && 
				(this.yearOfProduction == otherVehicules.yearOfProduction);
	}
}
