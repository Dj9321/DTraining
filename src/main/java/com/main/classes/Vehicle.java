package com.main.classes;

public class Vehicle implements Transport {
	public double price;
	protected String type = "4W";
	int maxSpeed = 200;
	int y;

	Vehicle(String type, int maxSpeed) {
		this.type = type;
		this.maxSpeed = maxSpeed;
	}

	public Vehicle() {
	}

	Vehicle(int y) {
		this.y = y;
	}

	public void test() {
		System.out.println("Vehicle");
	}

	@Override
	public void transport() {
		System.out.println("Transporting in Vehicle ... ");
	}
}
