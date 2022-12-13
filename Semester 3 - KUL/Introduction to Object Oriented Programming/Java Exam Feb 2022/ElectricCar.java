
public class ElectricCar implements ECInterface {
	
	// Class for Electric Cars
	
	String brand;
	int year;
	float distance;
	double curCharge;
	
	ElectricCar(String brand,int year, float distance, double curCharge){
		// Constructor for Electric Car
		
		this.brand=brand;
		this.year=year;
		this.distance=distance;
		this.curCharge=curCharge;
	}
	
	@Override
	public double calculateRegistrationTax(double index) {
		// Method for calculating registration tax. EC cars have no registration tax

		return 0;
	}

	@Override
	public double calculateCirculationTax(double index) {
		// Method for calculating circulation tax. EC cars have no circulation tax

		return 0;
	}

	@Override
	public void chargeBattery(double time) {
		// Increases the battery charge by time * 0.07.

		this.curCharge+=time*0.07;
	}

	@Override
	public double checkBatteryCapacity() {
		// Returns the current battery capacity
		
		return curCharge;
	}
	
	@Override
	  public String toString() {
	    return brand +" from "+ year;
	  }

}
