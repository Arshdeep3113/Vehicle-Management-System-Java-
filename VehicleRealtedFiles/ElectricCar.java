package vehicles;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Creates an object of type electric car extending car. It has another parameter: range 
 * and assigns its unique plate number.
 */

public class ElectricCar extends Car {
	private int range;
	private static int nextPlate = 1001;
	
	//default constructor
	public ElectricCar () {
		this.range = 0;
		this.plateNumber = "EC" + nextPlate++;
	}
	
	// parametrized constructor
	public ElectricCar (String make, String model, int yearOfProduction, int numOfPassengers, int range) {
		super (make, model, yearOfProduction, numOfPassengers);
		this.range = range;
		this.plateNumber = "EC" + nextPlate++;
	}
	
	//copy constructor
	public ElectricCar (ElectricCar other) {
		super (other); 
		this.range = other.range;
		this.plateNumber = "EC" + nextPlate++;
	}
	
	//getters
	public int getRange () {
		return this.range;
	}
	
	//setters
	public void setRange (int range) {
		this.range = range;
	}
	
	//toString
	@Override
	public String toString () {
		return super.toString()  + "\nRange: " + this.range 
				+ "\nPlate Number: " + this.plateNumber;
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
		
		ElectricCar otherElectricCars = (ElectricCar) otherObject;
		
		return super.equals(otherElectricCars) && (this.range == otherElectricCars.range); 
	}
	
	
	
	

}
