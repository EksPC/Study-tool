package main.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.java.model.Task;
import main.java.model.TaskList;
import main.java.model.TasksStorage;


/*TRANSITION:
 *  Storage becomes an hash map with the following structure:
 *  HashMap<String */

public class StorageManagerV2 {

	private static String taskListsFilePath = "xml/taskLists.sar";
	private static TasksStorage storage;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	
	
	public StorageManagerV2() {
		
	}
	
	public static void initialise() {
		FileOutputStream fileOut;
		FileInputStream fileIn;
		try {
			fileOut = new FileOutputStream(taskListsFilePath);
			fileIn = new FileInputStream(taskListsFilePath);
			output = new ObjectOutputStream(fileOut);
			input = new ObjectInputStream(fileIn);
			
			storage = (TasksStorage) input.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void addTaskToList(Task task) {
		
	}
	
	
	private void save() throws IOException {
		output.writeObject(storage);
	}
	
	
	
	
}
