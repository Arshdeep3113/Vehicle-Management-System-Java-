package driver;
import vehicles.*;
import clientRelated.*;
import java.util.Scanner;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * This is my driver class which allows the user to pick between which scenario they would like
 * to use. The first option is to simulate a menu base interface for Royal Rentals which allows 
 * the user to do multiple operations. The user can add, delete, edit and display vehicles and 
 * clients. As well as lease vehicles, return them and display them with additional operations
 * such as displaying the diesel truck with the greatest capacity and copies the electric trucks.
 * The second option is a hard coded scenario which gives the user an idea at what the code does.  
 */

public class DriverClass {
	static final int INVENTORY = 25;
	static final int CLIENTEL = 25;
	
	
	static Scanner kb = new Scanner(System.in);

	public static void main (String[] args) {
		int scenarioChoice;
		ClientManager clientel = new ClientManager (CLIENTEL);
		VehicleManager inventory = new VehicleManager (INVENTORY);
		LeaseManager leases = new LeaseManager (clientel, inventory, INVENTORY);
		
		do {
			displayScenario();
			scenarioChoice = kb.nextInt();
			kb.nextLine();
			
			switch (scenarioChoice) {
			case 1:
				MenuScenario(clientel, inventory, leases);
				break;
			case 2:
				PredefinedScenario();
				break;
			case 3:
				System.out.println("Thank you using Royal Rentals system!");
				System.exit(0);
			default:
				System.out.println("Invalid option. Please try again.\n");
			}
		} while (scenarioChoice !=1 || scenarioChoice != 2);
		
		
	}
	
	//displays scenario menu
	public static void displayScenario () {
		System.out.print("\t         Welcome to Royal Rentals!\n"
				+ "|---------------------------------------------------------|\n"
				+ "| 1. Menu-driven interface to interact with the program.  |\n"
				+ "| 2. Predifined/hard-coded scenario for testing.          |\n"
				+ "| 3. Exit                                                 |\n"
				+ "|---------------------------------------------------------|\n"
				+ "Please enter your choice > ");
	}
	
	//displays main menu 
	public static void displayMainMenu () {
		System.out.print("\n\t\tMain menu\n"
				+ "---------------------------------------\n"
				+ "\t1. Vehicle Management   \n"
				+ "\t2. Client Management    \n"
				+ "\t3. Leasing Operations   \n"
				+ "\t4. Additional Operations\n"
				+ "\t5. Exit                 \n"
				+ "---------------------------------------\n"
				+ "Choice > ");
	}
	
	//displays client operation options
	public static void displayClientMenu () {
		System.out.print("\n\t\tMain menu\n"
				+ "---------------------------------------\n"
				+ "\t1. Add a client\n"
				+ "\t2. Edit a client\n"
				+ "\t3. Remove a client\n"
				+ "\t4. Exit\n"
				+ "---------------------------------------\n"
				+ "Choice > ");
	}
	
	//displays vehicle operations options
	public static void displayVehicleMenu () {
		System.out.print("---------------------------------------\n"
				+ "Pick between options: \n"
				+ "\t1. Add vehicle\n"
				+ "\t2. Delete vehicle\n"
				+ "\t3. Edit vehicle\n"
				+ "\t4. List all vehicles by category\n"
				+ "\t5. Exit\n"
				+ "---------------------------------------\n"
				+ "Choice > ");
	}
	
	//displays lease operation options
	public static void displayLeaseMenu () {
		System.out.print("---------------------------------------\n"
				+ "Pick between options:\n"
				+ "\t1. Lease a vehicle to a client\n"
				+ "\t2. Return a vehicle from a client\n"
				+ "\t3. Show all vehicles leased by a client\n"
				+ "\t4. Show all leased vehicles by all clients\n"
				+ "\t5. Exit\n"
				+ "---------------------------------------\n"
				+ "Choice > ");
	}
	
	//display aditional operation options
	public static void displayAdditionalOperationsMenu () {
		System.out.print("-------------------------------------------------\n"
				+ "Pick between options:\n"
				+ "\t1. Display the truck with the largest capacity\n"
				+ "\t2. Create a copy of the electric trucks\n"
				+ "\t3. Exit\n"
				+ "-------------------------------------------------\n"
				+ "Choice > ");
	}
	
	//does the client operations picked by the user
	public static void clientManagement (ClientManager clientel, LeaseManager leases) {
		int choice;
		do {
			displayClientMenu();
			choice = kb.nextInt();
			kb.nextLine();
			
			switch (choice) {
			case 1:
				clientel.addClient();
				break;
			case 2: 
				clientel.editClient();
				break;
			case 3: 
				clientel.deleteClient(leases);
				break;
			case 4:
				System.out.println("Back to main menu...");
				break;
			default: 
				System.out.println("Invalid input. Please try again.");
			}
			
		} while ( choice != 4);
		
	}
	
	//does the vehicle operations picked by the user
	public static void vehicleManagement (VehicleManager inventory, LeaseManager leases) {
		int choice;
		do {
			displayVehicleMenu();
			choice = kb.nextInt();
			kb.nextLine();
			
			switch (choice) {
			case 1:
				inventory.addVehicle();
				break;
			case 2:
				inventory.deleteVehicle(leases);
				break;
			case 3: 
				inventory.editVehicle();
				break;
			case 4:
				inventory.displayVehiclesByCategory();
				break;
			case 5:
				System.out.println("Back to main menu...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
			
		} while (choice != 5);
		
		
	}
	
	//does the lease operations picked by the user
	public static void leaseManagement (LeaseManager leases) {
		int choice;
		do {
			displayLeaseMenu();
			choice = kb.nextInt();
			kb.nextLine();
			
			switch (choice) {
			case 1:
				leases.leaseVehicleToClient();
				break;
			case 2: 
				leases.returnVehicleFromClient();
				break;
			case 3:
				leases.displayVehiclesLeasedByAClient();
				break;
			case 4:
				leases.displayAllVehiclesLeased();
				break;
			case 5:
				System.out.println("Back to main menu...");
				break;
			default:
				System.out.println("Invalid input please try again.");			
			}	
		} while (choice != 5);
		
		
	}
	
	//getLargestTruck method
	public static DieselTruck getLargestTruck (DieselTruck [] dieselTrucks) {
		//checks if there is any diesel trucks
		if (dieselTrucks == null) {
			System.out.println("There is no diesel truck in our inventory.");
		}
		
		double maxWeightCapacity = 0;
		DieselTruck dieselTruckWithBiggestWeightCapacity = null;
		
		for (int i = 0; i < dieselTrucks.length; i++) {
			//finds the truck with the biggest weight capacity
			if (dieselTrucks[i] != null && dieselTrucks[i].getWeightCapacity() > maxWeightCapacity) {
				maxWeightCapacity = dieselTrucks[i].getWeightCapacity();
				dieselTruckWithBiggestWeightCapacity = dieselTrucks[i];
			}
		}
		
		if (dieselTrucks != null) {
			System.out.println("The truck with the largest weight capacity is " + 
					dieselTruckWithBiggestWeightCapacity.displayYearMakeModel() +
					" with a weight capacity of " + 
					dieselTruckWithBiggestWeightCapacity.getWeightCapacity() + " kg.");
		}
		return dieselTruckWithBiggestWeightCapacity;
	}
	
	//copyVehicle method
	public static ElectricTruck [] copyVehicle (ElectricTruck [] electricTrucks) {
		//checks if there is any electric trucks
		if (electricTrucks == null) {
			return null;
		}
		
		//makes a deep copy of the electric truck array
		ElectricTruck [] copyOfElectricTruck = new ElectricTruck [electricTrucks.length];
		for (int i = 0; i < electricTrucks.length; i++) {
			if (electricTrucks[i] != null) {
				copyOfElectricTruck[i] = new ElectricTruck (electricTrucks[i]);
			}
		}
		System.out.println("All the electric trucks have succesfully been copied.");
		return copyOfElectricTruck;
	}
	
	//does the additional operations picked by the user
	public static void additionalOperations (DieselTruck [] dieselTrucks, ElectricTruck [] electricTrucks) {
		int choice;
		do {
			displayAdditionalOperationsMenu();
			choice = kb.nextInt();
			kb.nextLine();
			
			switch (choice) {
			case 1:
				getLargestTruck(dieselTrucks);
				break;
			case 2:
				copyVehicle (electricTrucks);
				break;
			case 3:
				System.out.println("Back to main menu...");
				break;
			default: 
				System.out.println("Invalid input please try again.");
			}
		} while (choice !=3);
		
		
		
	}
	
	//does the menu scenario operations 
	public static void MenuScenario (ClientManager clientel, VehicleManager inventory, LeaseManager leases) {
		int menuChoice;
		do {
			displayMainMenu();
			menuChoice = kb.nextInt();
			kb.nextLine();
			
			switch (menuChoice) {
			case 1:
				vehicleManagement(inventory, leases);
				break;
			case 2: 
				clientManagement(clientel, leases);				
				break;
			case 3: 
				leaseManagement(leases);
				break;
			case 4:
				additionalOperations(inventory.getDiselTrucks(), inventory.getElectricTrucks());
				break;
			case 5:
				System.out.println("Back to scenario menu...");
				break;
			default: 
				System.out.print("Invalid option. Please try again.");
			}	
		} while (menuChoice !=5);
		
	}
	
	//does the predefined scenario
	public static void PredefinedScenario () {
		//3 clients
		Client c1 = new Client ("Barry Allen" , "BA240697");
		Client c2 = new Client ("Bruce Wayne", "BW311294");
		Client c3 = new Client ("Clark Kent", "CK210189");
		
		final int FLEETSIZE = 5;
		GasolineCar [] gasolineCarFleet = new GasolineCar [FLEETSIZE];
		ElectricCar [] electricCarFleet = new ElectricCar [FLEETSIZE];
		DieselTruck [] dieselTruckFleet = new DieselTruck [FLEETSIZE];
		ElectricTruck [] electricTruckFleet = new ElectricTruck [FLEETSIZE];
		Vehicle [] totalFleet = new Vehicle [INVENTORY];
		
		//3 gasoline cars objects and displays them
		GasolineCar gc1 = new GasolineCar ("Honda", "Civic", 2001, 5);
		GasolineCar gc2 = new GasolineCar ("Acura", "CSX", 2009, 5);
		GasolineCar gc3 = new GasolineCar ("Honda", "Accord", 2012, 5);
		gasolineCarFleet[0] = gc1;
		gasolineCarFleet[1] = gc2;
		gasolineCarFleet[2] = gc3;
		
		System.out.println("Gasoline cars: ");
		for (int i = 0; i < FLEETSIZE; i++) {
			if (gasolineCarFleet[i] != null) {
				System.out.println(gasolineCarFleet[i] + "\n");
			}
		}
		
		//3 electric cars objects and displays them
		ElectricCar ec1 = new ElectricCar ("Tesla", "Model 3", 2024, 5, 550);
		ElectricCar ec2 = new ElectricCar (ec1);
		ElectricCar ec3 = new ElectricCar ("Mustang", "Mach E", 2020, 5, 450);
		electricCarFleet[0] = ec1;
		electricCarFleet[1] = ec2;
		electricCarFleet[2] = ec3;
		System.out.println("Electric cars:");
		for (int i = 0; i < FLEETSIZE; i++) {
			if (electricCarFleet[i] != null) {
				System.out.println(electricCarFleet[i] + "\n");
			}
		}
		
		//3 diesel trucks objects and displays them
		DieselTruck dt1 = new DieselTruck ("Ford", "F-150", 2018, 780, 63);
		DieselTruck dt2 = new DieselTruck ("Ram", "3500", 2015, 630, 82);
		DieselTruck dt3 = new DieselTruck ("Chevrolet", "Colorado", 2024, 850, 78);
		dieselTruckFleet[0] = dt1;
		dieselTruckFleet[1] = dt2;
		dieselTruckFleet[2] = dt3;
		System.out.println("Diesel trucks:");
		for (int i = 0; i < FLEETSIZE; i++) {
			if (dieselTruckFleet[i] != null) {
				System.out.println(dieselTruckFleet[i] + "\n");
			}
		}
		
		//3 electric trucks objects and displays them
		ElectricTruck et1 = new ElectricTruck ("Tesla", "Cybertruck", 2025, 1050, 920);
		ElectricTruck et2 = new ElectricTruck ("Ford", "F-150 Lightning", 2023, 650, 420);
		ElectricTruck et3 = new ElectricTruck ("GMC", "Hummer EV", 2025, 780, 575);
		electricTruckFleet[0] = et1;
		electricTruckFleet[1] = et2;
		electricTruckFleet[2] = et3;
		System.out.println("Electric trucks:");
		for (int i = 0; i < FLEETSIZE; i++) {
			if (electricTruckFleet[i] != null) {
				System.out.println(electricTruckFleet[i] + "\n");
			}
		}
		
		//equals method 1: compare two different classes of objects 
		System.out.println("Is " + gc1.displayYearMakeModel() + " the same vehicle as " + ec1.displayYearMakeModel());
		System.out.println(gc1.equals(ec3) + "\n");
		//equals method 2: compares two object of the same  class but different attributes
		System.out.println("Is " + et1.displayYearMakeModel() + " the same vehicle as " + et2.displayYearMakeModel());
		System.out.println(et1.equals(et2) + "\n");
		//equals method 3: compares two objects of the same class and identical attributes
		System.out.println("Is " + ec1.displayYearMakeModel() + " the same vehicle as " + ec2.displayYearMakeModel());
		System.out.println(ec1.equals(ec2) + "\n");
		
		//adds all vehicles made into vehicle array
		totalFleet[0] = gc1;
		totalFleet[1] = gc2;
		totalFleet[2] = gc3;
		totalFleet[3] = ec1;
		totalFleet[4] = ec2;
		totalFleet[5] = ec3;
		totalFleet[6] = dt1;
		totalFleet[7] = dt2;
		totalFleet[8] = dt3;
		totalFleet[9] = et1;
		totalFleet[10] = et2;
		totalFleet[11] = et3;	
		
		//get largest truck method
		getLargestTruck(dieselTruckFleet);
		//copy vehicles method
		copyVehicle(electricTruckFleet);	
		System.out.println(" ");
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
