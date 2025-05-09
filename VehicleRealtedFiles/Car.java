package vehicles;
//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * The Car class creates a generic car, extending the Vehicle class. It has a extra parameter,
 * number of passengers.
 */
public class Car extends Vehicle {
	protected int numberOfPassengers;
	
	//default constructor
	public Car () {
		this.numberOfPassengers = 0;
	}
	
	//parametrized constructor
	public Car (String make, String model, int yearOfProduction, int numberOfPassengers) {
		super (make, model, yearOfProduction);
		this.numberOfPassengers = numberOfPassengers;
	}
	//copy constructor
	public Car (Car otherCar) {
		super (otherCar);
		this.numberOfPassengers = otherCar.numberOfPassengers;
	}
	
	//getter
	public int getNumberOfPassengers () {
		return this.numberOfPassengers;
	}
	
	//setter
	public void setNumberOfPassengers (int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}
	
	//toString
	@Override
	public String toString () {
		return super.toString() + "\nNumber of passengers: " + this.numberOfPassengers;
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
		
		Car otherCar = (Car) otherObject;
		
		return super.equals(otherCar) && this.numberOfPassengers == otherCar.numberOfPassengers;
	}

}
