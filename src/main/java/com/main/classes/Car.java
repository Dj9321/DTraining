package com.main.classes;

public class Car extends Vehicle {
	public double price;
	String transmission;
	private String engineType;
	protected String lightsType;
	int x;

	protected Car(String transmission) {
		this.transmission = transmission;
	}

	public Car() {
		super();
//		this(10);// Constructor cannot have more than one explicit constructor call
	}

	@Override
	public String toString() {
		return "Price" + price + " Transmission: " + transmission + " Engine type: " + engineType + " Lights Type: "
				+ lightsType + " X: " + x;
	}

	public Car(int x) {
		this.x = x;
		System.out.println("Car int constrcutor: " + x);
	}

	Car(String type, int maxSpeed, String transamission) {
//		this.type = type;
//		this.maxSpeed = maxSpeed;
		this.transmission = transamission;
		super(type, maxSpeed); // here super may not be in the first line as per latest java features
	}

	public void test() {
		System.out.println("Car");
	}

	public static void main(String[] args) {
		Car c1 = new Car("Auto");
		Car c2 = new Car("4W", 300, "Auto");
		System.out.println(c1.type + " " + c1.maxSpeed + " " + c1.transmission);
		System.out.println(c2.type + " " + c2.maxSpeed + " " + c2.transmission);

	}

	public String getLightsType() {
		return lightsType;
	}

	public void setLightsType(String lightsType) {
		this.lightsType = lightsType;
	}

	protected void getNumberOfTyres() {
		System.out.println("Number of tyres is: ");
	}
}

class D {

}
