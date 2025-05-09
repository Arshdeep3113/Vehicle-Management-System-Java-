package clientRelated;
import java.util.Scanner;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * My ClientManager deals with all client operations. The user can add clients, edit a clients info 
 * and remove a client. 
 */

public class ClientManager {
	private Client [] clientel;
	private int clientPos;
	static Scanner kb = new Scanner(System.in);
	
	
	public ClientManager (int max) {
		this.clientel = new Client [max];
		this.clientPos = 0;
	}
	
	//gets the client array
	public Client [] getClientArray () {
		Client [] newClientel = new Client [clientPos];
		for (int i = 0; i < this.clientPos; i++) {
			newClientel[i] = new Client (this.clientel[i]);
		}
		return newClientel;
	}
	
	//displays all clients
	public void displayAllClients () {
		System.out.println("List of clients: ");
		for (int i = 0; i < this.clientPos; i++) {
			System.out.println(i+1 + ". " + this.clientel[i].getName()+ ", " 
					+ this.clientel[i].getDriverLicense()); 
		}
	}
	
	//adds client
	public void addClient () {
		if (this.clientPos < this.clientel.length) {
			System.out.print("Please enter the client name: ");
			String nameToAdd = kb.nextLine();
			System.out.print("Please enter the client's driver license: ");
			String driverLicenseToAdd = kb.nextLine();
			Client client = new Client (nameToAdd, driverLicenseToAdd);
			this.clientel[clientPos] = client;
			this.clientPos++;
			System.out.println(client.getName() + " has been succesfully added.");
		}
		else {
			System.out.println("The maximum amount of clients has been reached.");
		}
	}
	
	//edit client
	public void editClient () {
		System.out.print("Please enter the client's driver license you would like to update: ");
		String driverLicense = kb.nextLine();
		boolean flag = false;
		int edit;
		
		for (int i = 0; i < this.clientPos; i++) {
			if (this.clientel[i].getDriverLicense().equalsIgnoreCase(driverLicense)) {
				do {
					System.out.print("What would you like to edit:\n"
							+ "\t1. Name\n"
							+ "\t2. Driver license\n"
							+ "\t3. Exit\n"
							+ "choice > ");
					edit = kb.nextInt();
					kb.nextLine();
					switch (edit) {
					case 1:
						System.out.print("New name: ");
						String newName = kb.nextLine();
						this.clientel[i].setName(newName);
						System.out.println("Name has succesfully been updated.");
						break;
					case 2: 
						System.out.println("New driver license: ");
						String newDriverLicense = kb.next();
						this.clientel[i].setDriverLicense(newDriverLicense);
						System.out.println("Driver license has succesfully been updated.");
						break;	
					case 3:
						System.out.println("Exiting...");
						break;
					default:
						System.out.println("Invalid input. please try again.");
					}
					flag = true;
				} while (edit !=3);
			}
		}
		//checks if the driver license matches
		if (!flag) {
			System.out.println("No match found.");
		}
	}
	
	public void deleteClient (LeaseManager leaseManager) {
		displayAllClients();
		System.out.print("Please enter the driver license of the client you would like to remove: ");
		String driverLicenseToRemove = kb.nextLine();
		
		
		boolean flag = false;
		for (int i = 0; i < this.clientPos; i++) {
	        if (this.clientel[i].getDriverLicense().equalsIgnoreCase(driverLicenseToRemove)) {
	            if (leaseManager.isClientLeasing(this.clientel[i])) {
	                System.out.println("Cannot remove client because they have active leases.");
	                return;
	            }

	            for (int j = i; j < this.clientPos - 1; j++) {
	                this.clientel[j] = this.clientel[j + 1];
	            }
	            this.clientel[clientPos - 1] = null;
	            clientPos--;
	            System.out.println("Client was successfully removed.");
	            flag = true;
	            break;
	        }
	    }
	    if (!flag) {
	        System.out.println("No match found.");
	    }
	}
}
