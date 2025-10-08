package com.test.others;

public class VehicleA extends Car {

	static class Truck {

		int getTruckMileage() {
			return 9;
		}

		static int getTyresForTrailerTruck() {
			return 16;
		}
	}

//	final int ina;

	public void methodA() {
		final int a;
		a = 10;
		st.equals("s");
		protectedMethod();
	}

	public static void main(String[] args) {
		Truck t = new Truck();
		System.out.println(Truck.getTyresForTrailerTruck());
		System.out.println(t.getTruckMileage());
		Car c = new Car();
		System.out.println(c.protectedMethod());
	}

}
