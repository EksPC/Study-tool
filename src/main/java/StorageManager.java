package main.java;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;


import main.java.model.Task;
import main.java.model.TaskList;
import main.java.model.TasksStorage;

//Reads and writes storage
public class StorageManager {
	
	private static final String taskListsFilePath = "resources/xml/taskLists.sar";
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	private static String dataFilePath = "resources/xml/data.xml";
	private static String settingsFilePath = "resources/xml/settings.xml";
	
	private static TasksStorage storage = new TasksStorage();

	
	public StorageManager() {
		
	}
	
	
	
	
	
	public static void initialise() {
		readTasks();
	}
	
	
	public static void addTaskList(String listName) {
		
		storage.getTodoLists().put(listName, new TaskList(listName));
		saveTasks();
	}
	
	public static boolean doesListExist(String newListName) {
		return storage.getTodoLists().containsKey(newListName);
	}
	
	/**This method returns an array list containing the task expiring on a certain date.
	 * If no task expires on the specified date, an empty ArrayList is returned.
	 * @param date The date*/
 	public static ArrayList<Task> getTaskByDate(Date date) {
		if(storage.getCalendarDates().containsKey(date)) {
			return storage.getCalendarDates().get(date);
		} else {
			return new ArrayList<Task>();
			
		}
	}
	
	
	/**This method adds a Task on a specific date via calendar.
	 * @param task The task to add
	 * @param date The date for which to add the task
	 * */
	public static void addTaskOnDate(Task task, String date) {
		
		if(!storage.getCalendarDates().containsKey(date)) {
			ArrayList<Task> tmp = new ArrayList<Task>();
			tmp.add(task);
			storage.getCalendarDates().put(date, tmp);
			
		} else {
			storage.getCalendarDates().get(date).add(task);
		}
		
		storage.getTodoLists().get(task.getListName()).getTasks().add(task);
		saveTasks();
	}
	
	
	public static TaskList getTaskListByName(String name) {
		return storage.getTodoLists().get(name);
	}
	
	
	
	public static ArrayList<TaskList> getTaskLists() {
		
		Set<String> keys = storage.getTodoLists().keySet();
		ArrayList<TaskList> tmp = new ArrayList<TaskList>();
		for(String key : keys) {
			tmp.add(storage.getTodoLists().get(key));
		}
		return tmp;
	}
	
	
	public static void addTaskToList(String listName, Task task) {
		
		//Updating the storage
		storage.getTodoLists().get(listName).getTasks().add(task);
		saveTasks();
		System.out.println("Adding " + task.getName() + " to " + listName);
	}
	
	
	public static boolean isStorageEmpty() {
		System.out.println("STORAGE EMPTY CHECK: ");
		System.out.println("\tcond 0 - null : " + Objects.isNull(storage));
		System.out.println("\tcond 1 - empty: " + storage.getTodoLists().isEmpty());
		return Objects.isNull(storage) || storage.getTodoLists().isEmpty();
	}
	
	
	
//	public Session readSession() {
//		try {
//			JAXBContext context = JAXBContext.newInstance(Session.class);
//			return (Session) context.createUnmarshaller().unmarshal(new FileReader(settingsFilePath));
//		} catch(IOException e) {
//			System.out.println("SETTINGS UNMARSHAL ERROR - I/O");
//			e.printStackTrace();
//		} catch(JAXBException je) {
//			System.out.println("SETTINGS UNMARSHAL ERROR - JAXB");
//			je.printStackTrace();
//		}
//		return null;
//	}
//
//	public void writeSession (Session session) {
//		try {
//			File settingsFile = new File(settingsFilePath);
//		
//			JAXBContext context = JAXBContext.newInstance(Session.class);
//		    Marshaller mar = context.createMarshaller();
//		    mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//		    mar.marshal(session, settingsFile);
//		    
//		} catch(PropertyException pe) {
//			System.out.println("MARSHAL ERROR - Property");
//			pe.printStackTrace();
//		} catch(JAXBException je) {
//			System.out.println("MARSHAL ERROR - JAXB");
//			je.printStackTrace();
//		}
//		
//	}

	public static void deleteList(TaskList list) {
		System.out.println("Deleting list: " + list);
		for(Task task: storage.getTodoLists().get(list.getId()).getTasks()) {
			if(!task.getExpiration().isBlank()) {
				storage.getCalendarDates().get(task.getExpiration()).remove(task);
			}
			
		}
		storage.getTodoLists().remove(list.getId());
		saveTasks();
	}	
	
	
	public static void deleteTask(Task task) {
		
		System.out.println("Deleting task: " + task.getName() + "\t List: " + task.getListName());
		storage.getCalendarDates().get(task.getExpiration()).remove(task);
		storage.getTodoLists().get(task.getListName()).getTasks().remove(task);
		saveTasks();
	}
	
	public static TasksStorage readTasks() {
		
		try {
			storage = new TasksStorage();
			JAXBContext context = JAXBContext.newInstance(TasksStorage.class);
			storage = (TasksStorage) context.createUnmarshaller().unmarshal(new FileReader(dataFilePath));
			
			for(String id:storage.getTodoLists().keySet()) {
				System.out.println(id);
			}
			
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

	/**This method saves the informations contained in the Storage object in the 
	 * storage file (XML).
	 * @param storage list of TaskLists to save*/
	public static void saveTasks (){
			
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
	
	public static void close() {
		try {
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CLOSED");

		
	}
	
	
	
} 