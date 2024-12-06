package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;

class TaskTestConstructor {

	@Test
	void testWhenNameIsNull() {
		 assertThrows(IllegalArgumentException.class,
		            ()->{ new Task(null, "Something should go here"); });
	}
	
	@Test
	void testWhenDescriptionIsNull() {
		 assertThrows(IllegalArgumentException.class,
		            ()->{ new Task("Something should go here", null ); });
	}

}
