package edu.westga.cs1302.project3.model;

/**This class aims to allow for a place to store Names and Descriptions for a given task.
 * 
 * @author mricha23 
 * @version Fall 24
 * 
 */
public class Task {

	private String name; 
	private String description; 
	
	/**Constructor for the Task class
	 * 
	 * @param name = this is the desired thing that the task should be called
	 * @param description = this explains what the task is in more detail that the name can convey.
	 */
	public Task(String name, String description) {
		if (name == null || description == null) {
			throw new IllegalArgumentException("Name or Description can not be null");
		}
		
		this.name = name;
		this.description = description; 
	}
	
	/**simple getter 
	 * 
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}
	
	/**simple getter
	 * 
	 * @return this.description
	 */
	public String getDescription() {
		return this.description;
	}
}
