import java.util.ArrayList;

public class CarCollection {
	
	public ArrayList<ElectricCar> ecArray= new ArrayList<ElectricCar>(); // List of Electric Cars
	public ArrayList<StandardCar> scArray= new ArrayList<StandardCar>(); // List of Standard Cars
	
	int numTotalCar;
	int numTotalEcCar;
	int numTotalScCar;

	
	public void addCar(ElectricCar ec) {
		// Adds an EC Car
		
		numTotalCar+=1;
		numTotalEcCar+=1;
		ecArray.add(ec);

		
	}
	
	public void addCar(StandardCar sc) {
		// Adds an SC Car
		
		scArray.add(sc);
		numTotalCar+=1;
		numTotalScCar+=1;


		
	}
	
	public int getTotalNumberOfCars() {
		
		return numTotalCar;
		
	}
	public int getNumberOfElectricCars() {
			
			return numTotalEcCar;
			
	}
	
	public int getNumberOfStandardCars() {
		
		return numTotalScCar;
		
	}
	
	public ArrayList<ElectricCar> getElectricCars() {
	
		return ecArray;
		
		
	}
	
	public ArrayList<StandardCar> getStandardCars() {
			
		return scArray;
			
			
	}
		
	

}
