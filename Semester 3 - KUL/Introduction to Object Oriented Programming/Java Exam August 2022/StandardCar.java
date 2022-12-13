
public class StandardCar implements SCInterface {
	// Class for Standard Cars
	
	String brand;
	int year;
	float distance;
	double euroNorm;
	double fiscHP;
	
	StandardCar(String brand,int year, float distance,double euroNorm, double fiscHP){
		
		// Constructor for Standard Car
		
		this.brand=brand;
		this.year=year;
		this.distance=distance;
		this.euroNorm=euroNorm;
		this.fiscHP=fiscHP;
		
	}

	@Override
	public double calculateRegistrationTax(double index) {
		// Method for calculating registration tax
		
		if (euroNorm==0) {
			return 1140*index;
		}
		else if (euroNorm==1){
			return index*510;
		}
		else if (euroNorm==2) {
			return index*150;
		}
		else if (euroNorm==3) {
			return index*95;
		}
		else {
			return index*20;
		}
	}

	@Override
	public double calculateCirculationTax(double index) {
		// Method for calculating circulation tax
		
		double ccTax=index*fiscHP*50;
		return ccTax;
	}
	
	@Override
	  public String toString() {
	    return brand +" from "+ year;
	  }

}
