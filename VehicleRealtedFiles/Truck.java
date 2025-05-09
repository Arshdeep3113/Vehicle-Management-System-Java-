package vehicles;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Truck class creates a generic truck object extending Vehicle class with an extra parameter:
 * weight capacity and assigns its unique plate number.
 */

public class Truck extends Vehicle {
	protected double weightCapacity;
	
	//default constructor
	public Truck () {
		this.weightCapacity = 0;
	}
	
	//parametrized constructor
	public Truck (String make, String model, int yearOfProduction, double weightCapacity) {
		super(make, model, yearOfProduction);
		this.weightCapacity = weightCapacity; 
	}
	
	//copy constructor
	public Truck (Truck otherTruck) {
		super (otherTruck);
		this.weightCapacity = otherTruck.weightCapacity;
	}
	
	//getters
	public double getWeightCapacity () {
		return this.weightCapacity;
	}
	
	//setters
	public void setWeightCapacity (double weightCapacity) {
		this.weightCapacity = weightCapacity;
	}
	
	//toString
	@Override
	public String toString () {
		return super.toString() + "\nWeight capacity: " + this.weightCapacity;
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
		
		Truck otherTruck = (Truck) otherObject;
		final double DIFFERENCE = 0.00001;
		
		return super.equals(otherTruck) 
				&& (Math.abs(this.weightCapacity - otherTruck.weightCapacity) < DIFFERENCE);
	}

}
