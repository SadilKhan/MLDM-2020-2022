
public interface ECInterface {
	// Interface for Electric Cars
	
	double calculateRegistrationTax(double index); 
	double calculateCirculationTax(double index);
	
	void chargeBattery(double time);
	double checkBatteryCapacity();
	

}
