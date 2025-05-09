package vehicles;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Creates a object of diesel truck type extending truck. It has another parameter: fuel tank and
 * assigns its own plate number. 
 */

public class DieselTruck extends Truck {
	private int fuelTank;
	private static int nextPlate = 1001;
	
	// default constructor
	public DieselTruck () {
		this.fuelTank = 0;
		this.plateNumber = "DT" + nextPlate++;
		
	}
	
	// parametrized constructor
	public DieselTruck (String make, String model, int yearOfProduction, double weightCapacity, int fuelTank) {
		super (make, model, yearOfProduction, weightCapacity);
		this.fuelTank = fuelTank;
		this.plateNumber = "DT" + nextPlate++;

	}
	
	//copy constructor
	public DieselTruck (DieselTruck otherDieselTruck) {
		super(otherDieselTruck);
		this.fuelTank = otherDieselTruck.fuelTank;
		this.plateNumber = "DT" + nextPlate++;

	}
	
	//getters
	public int getfuelTank () {
		return this.fuelTank;
	}
	
	//setters
	public void setFuelTank (int fuelTank) {
		this.fuelTank = fuelTank;
	}
	
	//toString
	@Override
	public String toString () {
		return super.toString() + "\nFuel tank capacity: " + this.fuelTank 
				+ "L\nPlate Number: " + this.plateNumber;
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
		
		DieselTruck otherDieselTruck = (DieselTruck) otherObject;
		
		return super.equals(otherDieselTruck) 
				&& (this.fuelTank == otherDieselTruck.fuelTank);
	}
	
}
