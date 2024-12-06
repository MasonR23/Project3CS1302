package edu.westga.cs1302.project3.model;

import java.util.ArrayList;

/**Wrapper class for the class "Task" 
 * 
 * this class aims to allow for the collection of task. 
 * 
 * @author mricha23
 * @version Fall 2024
 * 
 */
public class TaskList {
	private ArrayList<Task> toDoList;
	
	/**basic constructor
	 * 
	 */
	public TaskList() {
		this.toDoList = new ArrayList<Task>();
	}
	
	/**This is a method that allows the storage of a task. 
	 * 
	 * @param task = the desired task object
	 * @return true if the task is successfully added to the, false if the task could not be added to 
	 * the current list. 
	 */
	public boolean addTask(Task task) {
		this.toDoList.add(task);
		
		for (Task currTask : this.toDoList) {
			if (currTask == task) {
				return true;
			}
		}
		return false;
	}
}
