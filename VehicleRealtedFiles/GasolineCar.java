package vehicles;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Creates a object of gasoline type extending Car class. It also assigns the gasoline car its unique
 * plate number.
 */

public class GasolineCar extends Car {
	private static int nextPlate = 1001;
	
	//default constructor
	public GasolineCar () {
		this.plateNumber = "GC" + nextPlate++;
	}
	
	//parametrized constructor
	public GasolineCar (String make, String model, int yearOfProduction, int numOfPassengers) {
		super(make, model, yearOfProduction, numOfPassengers);
		this.plateNumber = "GC" + nextPlate++;

	}
	
	//copy constructor
	public GasolineCar (GasolineCar other) {
		super(other);
		this.plateNumber = "GC" + nextPlate++;
	}
	
	@Override
	public String toString () {
		 return super.toString() + "\nPlate number: " + this.plateNumber;
	}
	
	@Override
	public boolean equals (Object otherObject) {
		if (otherObject == null) {
			return false;
		}
		
		if (this.getClass() != otherObject.getClass()) {
			return false;
		}
		
		GasolineCar otherGasolineCars = (GasolineCar) otherObject;
		return (super.equals(otherGasolineCars));
		
		
	}
	
	
	
	
	
	
	

}
