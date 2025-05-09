package vehicles;
import java.util.Scanner;
import clientRelated.*;

//--------------------------------------------------------------
//Assignment 1
//Written by: Arshdeep Singh (40286514)
//--------------------------------------------------------------

/*
 * My VehicleManager takes care of all vehicle operations. The user can add vehicles of 4 different
 * types (Gasoline car, electric car, diesel truck, electric truck), edit any vehicle in the inventory, 
 * delete a vehicle, or display all vehicles by category. 
 */

public class VehicleManager {
	private Vehicle [] vehicles;
	private int index;
	static Scanner kb = new Scanner (System.in);
	
	public VehicleManager (int max) {
		this.vehicles = new Vehicle [max];
		this.index = 0;
	}
	
	//gets the vehicle array
	public Vehicle [] getVehicleArray () {
		Vehicle [] newVehicles = new Vehicle [index];
		 for (int i = 0; i < this.index; i++) {
			 newVehicles[i] = new Vehicle (this.vehicles[i]);
		 }
		 return newVehicles;
	}
	
	//gets the array of all the diesel trucks in the vehicle array
	public DieselTruck [] getDiselTrucks () {
		int dieselTruckCount = 0;
		for (Vehicle inventory : vehicles) {
			if (inventory instanceof DieselTruck) {
				dieselTruckCount++;
			}
		}
		
		DieselTruck [] DieselTruckInInventory = new DieselTruck [dieselTruckCount];
		int index = 0;
		for (Vehicle inventory : vehicles) {
			if (inventory instanceof DieselTruck) {
				DieselTruckInInventory[index] = (DieselTruck) inventory;
				index++;
			}
		}
		return DieselTruckInInventory;
	}
	
	//gets the array of all the electric trucks in the vehicle array
	public ElectricTruck [] getElectricTrucks () {
		int electricTruckCount = 0;
		for (Vehicle inventory : vehicles) {
			if (inventory instanceof ElectricTruck) {
				electricTruckCount++;
			}
		}
		
		ElectricTruck [] ElectricTruckInInventory = new ElectricTruck [electricTruckCount];
		int index = 0;
		for (Vehicle inventory : vehicles) {
			if (inventory instanceof ElectricTruck) {
				ElectricTruckInInventory[index] = (ElectricTruck) inventory;
				index++;
			}
		}
		return ElectricTruckInInventory;
	}
	
	//displays basic info about a vehicle
	public void displayAllVehicleBasicInfo () {
		for (int i = 0; i < index; i++) {
			System.out.println(i+1 + ". " + vehicles[i].getYearOfProduction() + " " + vehicles[i].getMake() 
					+ " " + vehicles[i].getModel() + ", " + vehicles[i].getPlateNumber());
		}
	}
	
	//adds a vehicle 
	public void addVehicle () {
		int numOfVehiclesToAdd;
		System.out.print("How many vehicules would you like to add: ");
		numOfVehiclesToAdd = kb.nextInt();
		kb.nextLine();
		
		int retry;
		//checks the user input
		if (numOfVehiclesToAdd > this.vehicles.length) {
			do {
				System.out.print("The number you have entered exceeds the inventory."
						+ "\nPlease try again. Choice: ");
				retry = kb.nextInt();
			} while (retry > this.vehicles.length);
		}
		
		else {
			for (int i = 0; i < numOfVehiclesToAdd; i++) {
				int carType;
				System.out.print("What kind of vehicle will you be adding:\n"
						+ "\t1. Gasoline car\n"
						+ "\t2. Electric car\n"
						+ "\t3. Disel truck\n"
						+ "\t4. Electric truck\n"
						+ "Choice > ");
				
				do {
					carType = kb.nextInt();
					kb.nextLine();
				if (carType < 0 || carType > 4) {
					System.out.print("Invalid option. Please try again. Choice > ");
					}
				} while (carType < 0 || carType > 4);
				
				System.out.print("Make: ");
				String make = kb.nextLine();
				System.out.print("Model: ");
				String model = kb.nextLine();
				System.out.print("Year of production: ");
				int yearOfProduction = kb.nextInt();
				switch (carType) {
				case 1:
					System.out.print("Enter the number of passengers: ");
					int numOfPassengers = kb.nextInt();
					vehicles[index] = new GasolineCar (make, model, yearOfProduction, numOfPassengers);
					
					break;
				case 2: 
					System.out.print("Enter the number of passengers: ");
					numOfPassengers = kb.nextInt();
					System.out.print("Enter the range of the battery: ");
					int range = kb.nextInt();
					vehicles[index] = new ElectricCar (make, model, yearOfProduction, numOfPassengers, range);
					
					break;
				case 3:
					System.out.print("Enter the maximum weight capacity: ");
					double weightCapacity = kb.nextDouble();
					System.out.print("Enter the fuel tank capacity: ");
					int fuelTank = kb.nextInt();
					vehicles[index] = new DieselTruck (make, model, yearOfProduction, weightCapacity, fuelTank);
					
					break;
				case 4:
					System.out.print("Enter the maximum weight capacity: ");
					weightCapacity = kb.nextDouble();
					System.out.print("Enter the range of the battery: ");
					range = kb.nextInt();
					vehicles[index] = new ElectricTruck (make, model, yearOfProduction, weightCapacity, range);
					
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
				
				System.out.println(vehicles[index].displayYearMakeModel() + " has been succesfully added to the inventory.");
				index++;
			}			
		}
		
	}
	
	//deletes a vehicle
	public void deleteVehicle(LeaseManager leaseManager) {
		//checks if there any vehicle to delete
	    if (index == 0) {
	        System.out.println("No vehicle has been added to delete.");
	        return;
	    }

	    displayAllVehicleBasicInfo();
	    System.out.print("Please enter the plate number of the vehicle you would like to remove. Choice > ");
	    String plateNumber = kb.next();
	    boolean flag = false;
	    
	    //this top half checks if the vehicle is being leased, then it will not be delete
	    Lease[] leases = leaseManager.getLeases();
	    for (int i = 0; i < index; i++) {
	        if (vehicles[i].getPlateNumber().equals(plateNumber)) {
	            if (leases != null) {
	                for (Lease lease : leases) {
	                    if (lease != null && (lease.getVehicle().getMake().equals(vehicles[i].getMake()) &&
	                    	lease.getVehicle().getModel().equals(vehicles[i].getModel()) &&
	                    	lease.getVehicle().getYearOfProduction() == vehicles[i].getYearOfProduction())) {
	                        System.out.println("Cannot delete vehicle since it is being leased.");
	                        return;
	                    }
	                }
	            }
	            //this part deletes the vehicles 
	            for (int j = i; j < index - 1; j++) {
	            	vehicles[j] = vehicles[j+1];
	            }
	            vehicles[index - 1] = null;
                index--;
                flag = true;
                System.out.println("Car has been successfully removed from the inventory list.");
	        }
	    }
	    //checks the users input if the plate number is in the inventory
	    if (!flag) {
	    	System.out.println("The plate number " + plateNumber + " does not match.");
	    }
	}
	
	//edits vehicle
	public void editVehicle () {
		displayAllVehicleBasicInfo();
		System.out.print("Please enter the plate number of the vehicle you would like to edit. Choice >  ");
		String plateNumber = kb.next();
		boolean flag = false;
		for (int i = 0; i < index; i++) {
			if (vehicles[i].getPlateNumber().equals(plateNumber)) {
				int edit = 0;
				//checks if vehicle is a gasoline car
				if (vehicles[i] instanceof GasolineCar) {
					System.out.print("Which element would you like to edit:\n"
							+ "\t1. Make\n"
							+ "\t2. Model\n"
							+ "\t3. Year of production\n"
							+ "\t4. Number of passengers\n"
							+ "Choice > ");
					edit = kb.nextInt();
					kb.nextLine();
				}
				//checks if vehicle is a electric car
				else if (vehicles[i] instanceof ElectricCar) {
					System.out.print("Which element would you like to edit:\n"
							+ "\t1. Make\n"
							+ "\t2. Model\n"
							+ "\t3. Year of production\n"
							+ "\t4. Number of passengers\n"
							+ "\t5. Range of battery\n"
							+ "Choice > ");
					edit = kb.nextInt();
					kb.nextLine();	
				}
				//checks if vehicle is a diesel truck
				else if (vehicles[i] instanceof DieselTruck) {
					System.out.print("Which element would you like to edit:\n"
							+ "\t1. Make\n"
							+ "\t2. Model\n"
							+ "\t3. Year of production\n"
							+ "\t4. Weight capacity\n"
							+ "\t5. Fuel tank capacity\n"
							+ "Choice > ");
					edit = kb.nextInt();
					kb.nextLine();
				}
				//checks if vehicle is a electric truck
				else if (vehicles[i] instanceof ElectricTruck) {
					System.out.print("Which element would you like to edit:\n"
							+ "\t1. Make\n"
							+ "\t2. Model\n"
							+ "\t3. Year of production\n"
							+ "\t4. Weight capacity\n"
							+ "\t5. Range of battery\n"
							+ "Choice > ");
					edit = kb.nextInt();
					kb.nextLine();
				}
				
				switch (edit) {
				case 1:
					System.out.print("Enter the new make: ");
					String newMake = kb.nextLine();
					vehicles[i].setMake(newMake);
					System.out.println(vehicles[i].toString());
					System.out.println("Make has been succesfully updated.");
					break;
				case 2: 
					System.out.print("Enter the new model: ");
					String newModel = kb.nextLine();
					vehicles[i].setModel(newModel);
					System.out.println(vehicles[i].toString());
					System.out.println("Model has been succesfully updated.");
					break;
				case 3:
					System.out.print("Enter the new year of production: ");
					int newYear = kb.nextInt();
					vehicles[i].setYearOfProduction(newYear);
					System.out.println(vehicles[i].toString());
					System.out.println("The year of production has been succesfully updated.");
					break;
				case 4: 
					if (vehicles[i] instanceof Car car) {
						System.out.print("Enter the new number of passenger: ");
						int newNumOfPassengers = kb.nextInt();
						car.setNumberOfPassengers(newNumOfPassengers);
						System.out.println(vehicles[i].toString());
						System.out.println("The number of passengers has been succesfully updated.");
						break;
					}
					
					else if (vehicles[i] instanceof Truck truck) {
						System.out.print("Enter the new weight capacity: ");
						double newWeightCapacity = kb.nextDouble();
						truck.setWeightCapacity(newWeightCapacity);
						System.out.println(vehicles[i].toString());
						System.out.println("The weight capacity has been succesfully updated.");
						break;
					}
				case 5:
					if (vehicles[i] instanceof ElectricCar ec) {
						System.out.print("Enter the new range of the battery: ");
						int newRange = kb.nextInt();
						ec.setRange(newRange);
						System.out.println(vehicles[i].toString());
						System.out.println("The range of the battery has been succesfully updated.");
						break;
					}
					else if (vehicles[i] instanceof ElectricTruck et) {
						System.out.print("Enter the new range of the battery: ");
						int newRange = kb.nextInt();
						et.setRange(newRange);
						System.out.println(vehicles[i].toString());
						System.out.println("The range of the battery has been succesfully updated.");
						break;
					}
					else if  (vehicles[i] instanceof DieselTruck dt) {
						System.out.print("Enter the new fuel tank capacity: ");
						int newFuelTank = kb.nextInt();
						dt.setFuelTank(newFuelTank);
						System.out.println(vehicles[i].toString());
						System.out.println("The fuel tank capacity has been succesfully updated.");
						break;
					}
				default: 
					System.out.println("Invalid input. Please try again.");
				}
				flag = true;

			}
		}
		//checks if the plate number matches any vehicle in inventory
		if (!flag) {
			System.out.println("The plate number " + plateNumber + " does not match any car in our inventory.");
		}
	}
	
	//displays all vehicles in each category
	public void displayVehiclesByCategory () {
		//displays all gasoline car vehicle
		System.out.println("List of gas cars:");
		for (int i = 0; i < this.index; i++) {
			if (vehicles[i] instanceof GasolineCar) {
				System.out.println(vehicles[i] + "\n");
			}
		}
		
		//displays all electric car vehicles
		System.out.println("List of electric cars:");
		for (int i = 0; i < this.index; i++) {
			if (vehicles[i] instanceof ElectricCar) {
				System.out.println(vehicles[i] + "\n");
			}		
		}
		
		//displays all diesel truck vehicles
		System.out.println("List of diesel trucks:");
		for (int i = 0; i < this.index; i++) {
			if (vehicles[i] instanceof DieselTruck) {
				System.out.println(vehicles[i] + "\n");
			}
		}
		
		//displays all electric vehicles
		System.out.println("List of electric trucks:");
		for (int i = 0; i < this.index; i++) {
			if (vehicles[i] instanceof ElectricTruck) {
				System.out.println(vehicles[i] + "\n");
			}
		}
	}
	
}
