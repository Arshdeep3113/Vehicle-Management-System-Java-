package vehicles;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------


/*
 * Creates a object of electric truck type extending truck. It has another parameter: range and
 * assigns its own plate number.
 */
public class ElectricTruck extends Truck {
	private int range;
	private static int nextPlate = 1001;

	//default constructor
	public ElectricTruck () {
		this.range = 0;
		this.plateNumber = "ET" + nextPlate++;
	}
	
	//parametrized constructor
	public ElectricTruck (String make, String model, int yearOfProduction, double weightCapacity, int range) {
		super (make, model, yearOfProduction, weightCapacity);
		this.range = range; 
		this.plateNumber = "ET" + nextPlate++;

	}
	
	//copy constructor
	public ElectricTruck (ElectricTruck otherElectricTruck) {
		super (otherElectricTruck);
		this.range = otherElectricTruck.range;
		this.plateNumber = "ET" + nextPlate++;
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
	return super.toString() + "\nRange:" + this.range + "\nPlate Number: " + this.plateNumber;
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
		
		ElectricTruck otherElectricTruck = (ElectricTruck) otherObject;
		return super.equals(otherElectricTruck) 
				&& (this.range == otherElectricTruck.range); 
		
	}
	
}
