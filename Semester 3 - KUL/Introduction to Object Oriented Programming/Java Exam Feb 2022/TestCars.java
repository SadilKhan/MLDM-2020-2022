public class TestCars implements CarBrand {

	public static void main(String[] args) {
		
		//current value of the tax calculation index
		double currentIndex = 1.12;
		
		CarCollection collection = new CarCollection();
		
		//create 3 electric car objects
		//order of the constructor arguments: car brand, construction year, distance driven, current battery charge (between 0 and 100)
		ElectricCar electricCarOne = new ElectricCar(CarBrand.TESLA, 2021, 0, 85.0);
		ElectricCar electricCarTwo = new ElectricCar(CarBrand.TESLA, 2019, 75000, 65.0);
		ElectricCar electricCarThree = new ElectricCar(CarBrand.SUZUKI, 2021, 0, 100.0);
		
		System.out.println(electricCarOne);
		System.out.println(electricCarTwo);
		System.out.println(electricCarThree);
		
		//add the 3 electric cars to the car collection
		collection.addCar(electricCarOne);
		collection.addCar(electricCarTwo);
		collection.addCar(electricCarThree);
		
		System.out.println("\nNumber of cars in collection: " + collection.getTotalNumberOfCars());
		System.out.println("  " + collection.getNumberOfElectricCars() + " electric cars and " + collection.getNumberOfStandardCars() + " standard cars");
		System.out.println();
		
		//create 5 standard car objects
		//order of the constructor arguments: car brand, construction year, distance driven, euro norm, fiscal horse power
		StandardCar standardCarOne = new StandardCar(CarBrand.BMW, 2016, 162000, 6, 10);
		StandardCar standardCarTwo = new StandardCar(CarBrand.MERCEDES, 2021, 0, 6, 7);
		StandardCar standardCarThree = new StandardCar(CarBrand.CITROEN, 2012, 198000, 5, 9);
		StandardCar standardCarFour = new StandardCar(CarBrand.AUDI, 2009, 251000, 4, 12);
		StandardCar standardCarFive = new StandardCar(CarBrand.VOLKSWAGEN, 2005, 287000, 3, 12);
		
		System.out.println(standardCarOne);
		System.out.println(standardCarTwo);
		System.out.println(standardCarThree);
		System.out.println(standardCarFour);
		System.out.println(standardCarFive);
		
		//add the 5 standard cars to the car collection
		collection.addCar(standardCarOne);
		collection.addCar(standardCarTwo);
		collection.addCar(standardCarThree);
		collection.addCar(standardCarFour);
		collection.addCar(standardCarFive);
		
		System.out.println("\nNumber of cars in collection: " + collection.getTotalNumberOfCars());
		System.out.println("  " + collection.getNumberOfElectricCars() + " electric cars and " + collection.getNumberOfStandardCars() + " standard cars");
		
		System.out.println("\nCalculate total taxes on car collection:");
		double registrationTax = 0.0;
		for (ElectricCar car : collection.getElectricCars()) {
			registrationTax += car.calculateRegistrationTax(currentIndex);
		}
		System.out.println("  Total registration tax for electric cars: " + registrationTax);
		registrationTax = 0.0;
		for (StandardCar car : collection.getStandardCars()) {
			registrationTax += car.calculateRegistrationTax(currentIndex);
		}
		System.out.println("  Total registration tax for standard cars: " + registrationTax);
		
		double circulationTax = 0.0;
		for (ElectricCar car : collection.getElectricCars()) {
			circulationTax += car.calculateCirculationTax(currentIndex);
		}
		System.out.println("  Total circulation tax for electric cars: " + circulationTax);
		circulationTax = 0.0;
		for (StandardCar car : collection.getStandardCars()) {
			circulationTax += car.calculateCirculationTax(currentIndex);
		}
		System.out.println("  Total circulation tax for standard cars: " + circulationTax);
		
	}

}
