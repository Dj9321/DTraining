package com.main.classes;

public class Vehicle {
	public double price;
	protected String type = "4W";
	int maxSpeed = 200;

	Vehicle(String type, int maxSpeed) {
		this.type = type;
		this.maxSpeed = maxSpeed;
	}

	Vehicle() {
	}

	public void test() {
		System.out.println("Vehicle");
	}
}
