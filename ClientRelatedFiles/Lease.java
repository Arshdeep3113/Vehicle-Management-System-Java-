package clientRelated;
import vehicles.*;
//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * Lease class creates an object which stores two object variables, client and vehicle
 */


public class Lease {
	private Client client;
	private Vehicle vehicle;
	
	//parametrized constructor
	public Lease (Client client, Vehicle vehicle) {
		this.client = new Client (client);
		this.vehicle = new Vehicle (vehicle);
	}
	
	//copy constructor
	public Lease (Lease other) {
		this (other.client, other.vehicle);
	}
	
	//getters
	public Client getClient () {
		return new Client (this.client);
	}
	
	public Vehicle getVehicle () {
		return new Vehicle (this.vehicle);
	}
	
	//setters
	public void setClient (Client client) {
		this.client = new Client (client);
	}
	
	public void setVehicle (Vehicle vehicle) {
		this.vehicle = new Vehicle (vehicle);
	}
	
	//toString
	@Override
	public String toString () {
		return this.client.getName() + " has leased " + this.vehicle.displayYearMakeModel();
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
		
		Lease otherLease = (Lease) otherObject;
		
		return this.client.equals(otherLease.getClient()) && this.vehicle.equals(otherLease.getVehicle());
	}

}
