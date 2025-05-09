package clientRelated;
import vehicles.*;
import java.util.Scanner;
//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * My LeaseManager takes care of all the leasing operations. The lease vehicles allows a client to
 * lease a vehicle but also checks to see if a vehicle is currently being leased. Return vehicles
 * allows the user to return a vehicle back into their inventory. Finally, the user can either check
 * how many vehicles a client has leased, and see all the vehicles being leased by all clients.
 */


public class LeaseManager {
	private ClientManager clients;
	private VehicleManager vehicles;
	private Lease [] leases;
	private int numOfLeases; 
	static Scanner kb = new Scanner (System.in);
	
	public LeaseManager (ClientManager clients, VehicleManager vehicles, int max) {
		this.clients = clients;
		this.vehicles = vehicles;
		this.leases = new Lease [max]; 
		this.numOfLeases = 0;
	}
	
	//gets the array of leases
	public Lease [] getLeases () {
		Lease [] newLeases = new Lease [numOfLeases];
		for (int i = 0; i < this.numOfLeases; i++) {
			newLeases [i] = new Lease (this.leases[i]);
		}
		return newLeases;
	}
	
	//checks if the client is currently leasing
	public boolean isClientLeasing (Client client) {
		for (int i = 0; i < this.numOfLeases; i++) {
			if (this.leases[i].getClient().equals(client)) {
				return true;
			}
		}
		return false;
	}
	
	//displays all the leases
	public void displayLeaseHistoryInOrder () {
		for (int i = 0; i < this.numOfLeases; i++) {
			System.out.println((i+1) + ". " + leases[i].getClient().getName() + ", " 
					+ this.leases[i].getVehicle().displayYearMakeModel());
		}
	}
	
	//leases a vehicle to a client
	public void leaseVehicleToClient () {
		if (this.clients.getClientArray().length == 0 || this.vehicles.getVehicleArray().length == 0) {
			System.out.println("Client or vehicle is missing.");
			return;
		}
		
		this.clients.displayAllClients();
		int clientIndex;
		System.out.print("Enter the customer index. Choice > ");
		clientIndex = kb.nextInt();
		kb.nextLine();
		
		
		//checks client index input
		while (clientIndex > this.clients.getClientArray().length) {
			System.out.print("Invalid option, please try again > ");
			clientIndex = kb.nextInt();
			kb.nextLine();
		}
		Client clientLeasing = this.clients.getClientArray()[clientIndex-1];

		this.vehicles.displayAllVehicleBasicInfo();
		int vehicleIndex;
		System.out.print("Enter the vehicle index the client will be leasing. Choice > ");
		vehicleIndex = kb.nextInt();
		kb.nextLine();

		//checks vehicle index input
		while (vehicleIndex < 0 || vehicleIndex > this.vehicles.getVehicleArray().length) {
				System.out.print("Invalid option, please try again > ");
				vehicleIndex = kb.nextInt();
				kb.nextLine();
		}
			
		Vehicle vehicleLeased = this.vehicles.getVehicleArray()[vehicleIndex-1];
		//checks if the vehicle picked is currently being leased or not
		for (int i = 0; i < this.numOfLeases; i++) {
			if (this.leases[i].getVehicle().equals(vehicleLeased)) {
				System.out.println("This vehicle is already been leased to a client.");
				return;
			}
		}

		this.leases[numOfLeases] = new Lease (clientLeasing,vehicleLeased);
		System.out.println(this.leases[numOfLeases]);
		numOfLeases++;
	}
	
	//returns the vehicles and removes the lease 
	public void returnVehicleFromClient () {
		//checks if there is at least one client or vehicle
		if (this.clients.getClientArray().length == 0 || this.vehicles.getVehicleArray().length == 0) {
			System.out.println("No client or vehicle has yet been added.");
			return;
		}
		
		//checks if a vehicle has been leased
		if (this.numOfLeases == 0) {
			System.out.println("No vehicle has yet been leased to return.");
			return;
		}
		
		displayLeaseHistoryInOrder();
		boolean flag = false;
		System.out.print("Which lease are you returning: ");
		int leasedReturned = kb.nextInt();
		kb.nextLine();
		
		//successfully returns the vehicle from client
		for (int i = 0; i < this.numOfLeases; i++) {
			if ((leasedReturned-1) == i) {
				for (int j = i; j < this.numOfLeases-1; j++) {
					this.leases[j] = this.leases[j+1];
				}
				this.leases[this.numOfLeases-1] = null;
				this.numOfLeases--;
				System.out.println("The vehicle has succesfully been returned to Royal Rental's inventory.");
				flag = true;
				break;
			}
		}
		//displays if the input is invalid
		if (!flag) {
			System.out.println("No match found.");
		}
	}
	
	//displays all vehicles leased by a certain client
	public void displayVehiclesLeasedByAClient () {
		//checks if there is at least one client or vehicle
		if (this.clients.getClientArray().length == 0 || this.vehicles.getVehicleArray().length == 0) {
			System.out.println("Client or vehicle is missing.");
			return;
		}
		
		//checks if there is at least one lease 
		if (this.numOfLeases == 0) {
			System.out.println("No vehicles has been leased.");
			return;
		}
		
		
		this.clients.displayAllClients();
		int clientToDisplayLeasedVehicles;
		System.out.print("Enter the client index would you like to see their vehicles leased > ");
		clientToDisplayLeasedVehicles = kb.nextInt();
		//checks user input
		while (clientToDisplayLeasedVehicles < 0 || clientToDisplayLeasedVehicles > this.numOfLeases) {
			System.out.print("Sorry, the number you put is invalid, please try again. Choice > ");
			clientToDisplayLeasedVehicles = kb.nextInt();
		}
		

		//displays all vehicles leased by the client
		Client clientToDisplay = this.clients.getClientArray()[clientToDisplayLeasedVehicles-1];
		String name = clientToDisplay.getName();
		
		System.out.println("Vehicles leased by " + name + ":");
		for (int i = 0; i < this.numOfLeases; i++) {
			if (this.leases[i].getClient().getName().equalsIgnoreCase(name)) {
				System.out.println(this.leases[i].getVehicle().displayYearMakeModel());
			}
		}
	}
	
	//displays all the vehicles being leased by all clients
	public void displayAllVehiclesLeased () {
		//checks if there is at least one client or vehicle
		if (this.clients.getClientArray().length == 0 || this.vehicles.getVehicleArray().length == 0) {
			System.out.println("Client or vehicle is missing.");
			return;
		}
		
		//checks if there is at least one lease
		if (this.numOfLeases == 0) {
		    System.out.println("No vehicle has yet been leased.");
		    return;
		}
		
		//this loops checks if a client name is already been displayed
		for (int i = 0; i < this.numOfLeases; i++) {
		    String displayedClient = this.leases[i].getClient().getName();
		    boolean alreadyDisplayed = false;

		    for (int j = 0; j < i; j++) {
		        if (this.leases[j].getClient().getName().equals(displayedClient)) {
		            alreadyDisplayed = true;
		            break;
		        }
		    }

		    if (alreadyDisplayed) {
		        continue;
		    }

		    System.out.println("\nClient: " + displayedClient);
		    //displays all vehicles
		    for (int k = 0; k < this.numOfLeases; k++) {
		        if (this.leases[k].getClient().getName().equals(displayedClient)) {
		            System.out.println((k + 1) + ". " + this.leases[k].getVehicle().displayYearMakeModel());
		        }
		    }
		}
	}
}
