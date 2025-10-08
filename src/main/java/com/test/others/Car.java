package com.test.others;

import com.test.others.VehicleA.Truck;

public class Car {

	String st = "Car 1";

	String protectedMethod() {
		return "Restricted access";
	}

	public static void main(String[] args) {
//		Truck v = new Vehicle.Truck();
//		v.getTruckMileage();
		Truck.getTyresForTrailerTruck();
		Truck tr = new VehicleA.Truck();
		tr.getTruckMileage();

	}

	String s = "Car 1";

	public String getCar(String s) {
		return s;
	}

}
