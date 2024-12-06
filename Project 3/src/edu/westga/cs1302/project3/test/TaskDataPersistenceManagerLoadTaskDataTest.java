package edu.westga.cs1302.project3.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskDataPersistenceManager;

class TaskDataPersistenceManagerLoadTaskDataTest {

	 @Test
	    void testLoadTaskData_ValidFile() throws IOException {
	        String fileName = "validTasks.txt";
	        
	        // Use saveTaskData to create the file with valid task data
	        ArrayList<Task> tasksToWrite = new ArrayList<>();
	        tasksToWrite.add(new Task("Task 1", "Description 1"));
	        tasksToWrite.add(new Task("Task 2", "Description 2"));
	        TaskDataPersistenceManager.saveTaskData(tasksToWrite, fileName);

	        // Call loadTaskData to load tasks from the file
	        ArrayList<Task> loadedTasks = TaskDataPersistenceManager.loadTaskData(fileName);

	        // Verify the loaded tasks
	        assertEquals(2, loadedTasks.size());
	        assertEquals("Task 1", loadedTasks.get(0).getName());
	        assertEquals("Description 1", loadedTasks.get(0).getDescription());
	        assertEquals("Task 2", loadedTasks.get(1).getName());
	        assertEquals("Description 2", loadedTasks.get(1).getDescription());
	    }
	 
	 @Test
	    void testLoadTaskData_InvalidTaskData() throws IOException {
	        String fileName = "invalidTaskData.txt";
	        
	        // Use saveTaskData to write invalid data to the file
	        // This can be malformed data that causes errors when loading
	        ArrayList<Task> tasksToWrite = new ArrayList<>();
	        tasksToWrite.add(new Task("Task 1", "")); // Missing description
	        tasksToWrite.add(new Task("", "Description 2")); // Missing name
	        TaskDataPersistenceManager.saveTaskData(tasksToWrite, fileName);

	        // Now test loadTaskData on the malformed data
	        assertThrows(IOException.class, () -> {
	        	TaskDataPersistenceManager.loadTaskData(fileName);
	        });
	    }

	 @Test
	    void testLoadTaskData_EmptyLines() throws IOException {
	        String fileName = "fileWithEmptyLines.txt";
	        
	        // Use saveTaskData to create a file with empty lines
	        ArrayList<Task> tasksToWrite = new ArrayList<>();
	        tasksToWrite.add(new Task("Task 1", "Description 1"));
	        tasksToWrite.add(new Task("Task 2", "Description 2"));
	        // Writing empty lines directly
	        TaskDataPersistenceManager.saveTaskData(tasksToWrite, fileName);

	        // Now call loadTaskData to read from the file
	        ArrayList<Task> loadedTasks = TaskDataPersistenceManager.loadTaskData(fileName);

	        // Verify the number of tasks loaded
	        assertEquals(2, loadedTasks.size(), "Should load 2 tasks.");
	        assertEquals("Task 1", loadedTasks.get(0).getName(), "First task name should be 'Task 1'.");
	        assertEquals("Description 1", loadedTasks.get(0).getDescription(), "First task description should be 'Description 1'.");
	    }
}
