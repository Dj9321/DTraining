package com.main.classes;

public sealed abstract class PersonAbstractSealed permits PersonJob {

	// for Abstract methods you don't need {}. Also if one method is abstract the
	// class should be abstract.
	public abstract String jobType();

	public abstract String jobDesignation();

}
