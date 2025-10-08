package com.main.others;

import com.main.classes.Car;
import com.main.classes.Vehicle;

public class MarutiCar extends Car {

//	 24: public class MarutiCar extends Car > You can extend Car class in another
	// package, but if it doesn't have default constructor, then you have to write
	// argument constructor in MarutiCar Class (again super class argument
	// constructor should be protected or public and NOT default)

	MarutiCar(String transmission) {
		super(transmission); // you can't omit this as implicit super() is not available for Car
	}

	MarutiCar() {

	}

	public static void main(String[] args) {
		Car c = new MarutiCar("Automatic Transmission");
		MarutiCar mc = new MarutiCar("Automatic Transmission");
//		mc = new Car("Auto"); // not possible with subclass creating superclass
//		Car c1 = new Car("Automatic Transmission"); // even though the constructor is protected it is not accessible
		// here
		// as object is of type MarutiCar, we can't access variables in Car which are
		// not public
//		System.out.println(c.engineType); 
//		A protected variable in Java is generally accessible in a subclass, but there are specific situations where a subclass 
//		cannot access the protected variable if it is an instance of the superclass rather than itself or its own subclass instance.
//		System.out.println(c.lightsType); // even though it is protected it is not visible as we are using instance of superclass and not subclass
//		c.getNumberOfTyres(); // not possible > same reason as above
		double price2 = mc.price;
		String t1 = mc.type;
		System.out.println(mc.lightsType);

		Vehicle v3 = new MarutiCar();
//		Vehicle v4 = new Car();
		Vehicle v5 = new MarutiCar();
		v3 = (Vehicle) v5; // tricky here > subclass casted to superclass. Here object is of type MarutiCar
							// but reference is of type Vehicle. v3 is also same
		Vehicle v6 = (Car) v5;
		v3.test(); // object is MarutiCar so that class will only be called
		v6.test(); // even though it is casted to super super class the object is still MarutiCar

	}

	public void test() {
		System.out.println("Maruti Car");
	}
}
