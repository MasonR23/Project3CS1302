package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**Class that allows for the saving/loading of a list of task from or two a file. 
 * 
 * @author mricha23
 * @version fall 24
 * 
 */
public class TaskDataPersistenceManager {

	/**Allows for the writing of a collection of Task to a specified file. 
	 * 
	 * @param tasks = the ArrayList of Task that need to be saved
	 * @param fileName = the desired name of the file that the task should be saved too
	 * @throws IOException
	 */
	public static void saveTaskData(ArrayList<Task> tasks, String fileName) throws IOException {
		if (tasks == null) {
			throw new IllegalArgumentException("Must provide an ArrayList of Tasks");
		}
		try (FileWriter writer = new FileWriter(fileName)) {
			for (Task currTask : tasks) {
				if (currTask != null) {
					writer.write(currTask.getName() + "," + currTask.getDescription() + System.lineSeparator());
				}
			}
		}
	}
	
	/**Allows for the reading and loading of a file passed into the method by the user.
	 * 
	 * @param fileName file to be loaded in by the user
	 * @return a array list full of Task that is stored in the passed in file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Task> loadTaskData(String fileName) throws FileNotFoundException, IOException {
		ArrayList<Task> tasks = new ArrayList<Task>();
		File inputFile = new File(fileName);
		try (Scanner reader = new Scanner(inputFile)) {
			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");
				try {
					String name = parts[0];
					String description = parts[1];
					Task nextTask = new Task(name, description);
					tasks.add(nextTask);
				} catch (NumberFormatException numError) {
					throw new IOException(
							"Unable to read description" + lineNumber + " : " + strippedLine);
				} catch (IllegalArgumentException studentDataError) {
					throw new IOException(
							"Unable to create Task, something wrong wither either Name or Description" + lineNumber + " : " + strippedLine);
				} catch (IndexOutOfBoundsException studentDataError) {
					throw new IOException(
							"Missing either name and/or description on line " + lineNumber + " : " + strippedLine);
				}
			}
		}
		return tasks;
	}
}
