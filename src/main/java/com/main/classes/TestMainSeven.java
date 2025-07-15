package com.main.classes;

import java.util.ArrayList;

public class TestMainSeven {

	public static void main(String[] args) {
		Person p = new Person("Dheeraj", "ID22");
		Person p1 = new Person("Temp1", "IDT2");
		Person p2 = new Person("Temp2", "IDT3");
		Person p3 = new Person("Temp3", "IDT4");
		Person p4 = new Person("Temp4", "IDT5");
		Person p5 = new Person("Temp5", "IDT6");

		ArrayList<Person> a = new ArrayList<Person>();
		a.add(p);
		a.add(p1);
		a.add(p2);
		a.add(p3);
		a.add(p4);
		a.add(p5);

		ClientData c = new ClientData();
		c.setPersonList(a);

		ArrayList b = new ArrayList(1);

//		c.getPersonList().forEach(user -> 
//				b.clear();
//				b.add(user);
//				);
//		

	}
}
