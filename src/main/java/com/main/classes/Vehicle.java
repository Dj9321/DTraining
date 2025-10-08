package com.main.classes;

public class Vehicle {
	public double price;
	String type = "4W";
	int maxSpeed = 200;

	Vehicle(String type, int maxSpeed) {
		this.type = type;
		this.maxSpeed = maxSpeed;
	}

	Vehicle() {
	}
}
