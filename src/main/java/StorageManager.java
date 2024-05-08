package main.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import main.java.tools.TasksStorage;
import main.java.tools.Session;
import main.java.tools.Task;
import main.java.tools.TaskList;

//Reads and writes storage
public class StorageManager {

	private static String dataFilePath = "resources/xml/data.xml";
	private static String settingsFilePath = "resources/xml/settings.xml";
	
	private static TasksStorage storage;

	
	public StorageManager() {
		
	}
	
	
	public static void addTaskList(String listName) {
		
		storage.getLists().add(new TaskList(listName));
		writeTasks(storage);
	}
	
	public static boolean contains(String newListName) {
		for(TaskList it:StorageManager.getTaskLists()) {
			if(it.getId().equals(newListName)) {
				return true;
			}
		} 
		return false;
	}
	
	
	public static ArrayList<TaskList> getTaskLists() {
		if(isStorageEmpty()) {
			return new ArrayList<TaskList>();
		}
		return storage.getLists();
	}
	
	
	
	public static void addTaskToList(String listName, Task task) {
		
		System.out.println("Adding " + task.getName() + " to " + listName);
		//Updating the storage
		for(int i = 0; i<storage.getLists().size(); i++) {
			if(storage.getLists().get(i).getId().equals(listName)) {
				storage.getLists().get(i).getTasks().add(task);
				writeTasks(storage);
				break;
			}
		}
	}
	
	/**This method saves the informations contained in the Storage object in the 
	 * storage file (XML).
	 * @param storage list of TaskLists to save*/
	public static void writeTasks (TasksStorage storage){
		
		try {
			File dataFile = new File(dataFilePath);
			StringWriter sw = new StringWriter();
		
			JAXBContext context = JAXBContext.newInstance(TasksStorage.class);
		    Marshaller mar = context.createMarshaller();
		    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    mar.marshal(storage, dataFile);
		    
		} catch(PropertyException pe) {
			System.out.println("MARSHAL ERROR - Property");
			pe.printStackTrace();
		} catch(JAXBException je) {
			System.out.println("MARSHAL ERROR - JAXB");
			je.printStackTrace();
		}
		
	}
	
	public static boolean isStorageEmpty() {
		System.out.println("STORAGE EMPTY CHECK: ");
		System.out.println("\tcond 0 - null : " + Objects.isNull(storage));
		System.out.println("\tcond 1 - empty: " + storage.getLists().isEmpty());
		return Objects.isNull(storage) || storage.getLists().isEmpty();
	}
	
	public static TasksStorage readTasks() {
		
		try {
			storage = new TasksStorage();
			JAXBContext context = JAXBContext.newInstance(TasksStorage.class);
			storage = (TasksStorage) context.createUnmarshaller().unmarshal(new FileReader(dataFilePath));
			
			return storage;
			
		} catch(IOException e) {
			System.out.println("TASK UNMARSHAL ERROR - I/O");
			e.printStackTrace();
		} catch(JAXBException je) {
			System.out.println("TASK UNMARSHAL ERROR - JAXB");
			je.printStackTrace();
		}
		return null;
	}
	
	
	public Session readSession() {
		try {
			JAXBContext context = JAXBContext.newInstance(Session.class);
			return (Session) context.createUnmarshaller().unmarshal(new FileReader(settingsFilePath));
		} catch(IOException e) {
			System.out.println("SETTINGS UNMARSHAL ERROR - I/O");
			e.printStackTrace();
		} catch(JAXBException je) {
			System.out.println("SETTINGS UNMARSHAL ERROR - JAXB");
			je.printStackTrace();
		}
		return null;
	}

	public void writeSession (Session session) {
		try {
			File settingsFile = new File(settingsFilePath);
		
			JAXBContext context = JAXBContext.newInstance(Session.class);
		    Marshaller mar = context.createMarshaller();
		    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    mar.marshal(session, settingsFile);
		    
		} catch(PropertyException pe) {
			System.out.println("MARSHAL ERROR - Property");
			pe.printStackTrace();
		} catch(JAXBException je) {
			System.out.println("MARSHAL ERROR - JAXB");
			je.printStackTrace();
		}
		
	}


	public static int deleteList(String listName) {
		int counter = 0;
		
		System.out.println("Before starting -> storage 0 = " + storage.getLists().get(0).getId() );
		
		for(TaskList list : storage.getLists()) {
			
			if(list.getId().equals(listName)) {
				storage.getLists().remove(counter);
				writeTasks(storage);
				return counter;
			}
			counter++;
		}
		return -1;
	}
	
	
} 