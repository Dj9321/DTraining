package com.main.classes;

public class Vehicle {
	public double price;
	protected String type = "4W";
	int maxSpeed = 200;
	int y;

	Vehicle(String type, int maxSpeed) {
		this.type = type;
		this.maxSpeed = maxSpeed;
	}

	Vehicle() {
	}

	Vehicle(int y){
		this.y = y;
	}

	public void test() {
		System.out.println("Vehicle");
	}
}
