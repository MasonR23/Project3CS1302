package edu.westga.cs1302.project3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskDataPersistenceManager;

class TaskDataPersistenceManagerTestWriter {

	 @Test
	    void testSaveTaskData_NullTasks() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            TaskDataPersistenceManager.saveTaskData(null, "tasks.txt");
	        });
	       
	    }
	 
	  @Test
	    void testSaveTaskData_EmptyTasks() throws IOException {
	        ArrayList<Task> tasks = new ArrayList<>();
	        String fileName = "emptyTasks.txt";
	        
	        // Call method to save tasks (no tasks should be written)
	        TaskDataPersistenceManager.saveTaskData(tasks, fileName);
	        
	        File file = new File(fileName);
	        assertTrue(file.exists());
	        assertEquals(0, file.length());
	    }
	  
	   @Test
	    void testSaveTaskData_WithNullTasksInList() throws IOException {
	        ArrayList<Task> tasks = new ArrayList<>();
	        tasks.add(null); // Add a null task
	        tasks.add(new Task("Task 1", "Description 1"));
	        
	        String fileName = "tasksWithNull.txt";
	        TaskDataPersistenceManager.saveTaskData(tasks, fileName);
	        
	        File file = new File(fileName);
	        assertTrue(file.exists());
	        
	        // Verify that only the non-null task is written to the file
	        String content = new String(Files.readAllBytes(file.toPath()));
	        assertTrue(content.contains("Task 1,Description 1"));
	        assertFalse(content.contains("null"));
	    }
	   
	   @Test
	    void testSaveTaskData_ValidTasks() throws IOException {
	        ArrayList<Task> tasks = new ArrayList<>();
	        tasks.add(new Task("Task 1", "Description 1"));
	        tasks.add(new Task("Task 2", "Description 2"));
	        
	        String fileName = "validTasks.txt";
	        TaskDataPersistenceManager.saveTaskData(tasks, fileName);
	        
	        File file = new File(fileName);
	        assertTrue(file.exists());
	        
	        // Verify that the tasks are correctly written
	        String content = new String(Files.readAllBytes(file.toPath()));
	        assertTrue(content.contains("Task 1,Description 1"));
	        assertTrue(content.contains("Task 2,Description 2"));
	    }
	   
	   @Test
	    void testSaveTaskData_FileWriteError() {
	        ArrayList<Task> tasks = new ArrayList<>();
	        tasks.add(new Task("Task 1", "Description 1"));
	        
	        // Use an invalid file path to trigger an IOException
	        String invalidFileName = "/invalid/path/to/file.txt";
	        
	        assertThrows(IOException.class, () -> {
	        	TaskDataPersistenceManager.saveTaskData(tasks, invalidFileName);
	        });
	    }
	   
	   @Test
	    void testSaveTaskData_CreateNewFile() throws IOException {
	        ArrayList<Task> tasks = new ArrayList<>();
	        tasks.add(new Task("Task 1", "Description 1"));
	        
	        String fileName = "newTasksFile.txt";
	        
	        // Ensure file does not exist before the test
	        File file = new File(fileName);
	        if (file.exists()) {
	            file.delete();
	        }
	        
	        // Call method to save tasks (this should create the file)
	        TaskDataPersistenceManager.saveTaskData(tasks, fileName);
	        
	        assertTrue(file.exists());
	        
	        // Verify that the task data is written to the file
	        String content = new String(Files.readAllBytes(file.toPath()));
	        assertTrue(content.contains("Task 1,Description 1"));
	    }
}
