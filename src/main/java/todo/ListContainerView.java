package main.java.todo;


import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Struct;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.StorageManager;
import main.java.model.Task;
import main.java.model.TaskList;
import main.java.model.TasksStorage;
public class ListContainerView implements Initializable {
	
	private class FieldMessages {
		public final static String noIdPromptText = "please insert a name";
		public final static String standardPromptText = "type the list name";
		public final static String nameTakenText = "name already taken";
	}
	
	private TodoController controller;
	/**Needed for binding ListName to position in the */
	private HBox displayedListBox;
	private TaskList activeTaskList;
	private HBox pointedBox;
	
	@FXML private HBox todayList;
	@FXML private VBox listContainer;
	@FXML private Button newListButton;
	@FXML private TextField listNameField;
	
	public ListContainerView(TodoController controller) {
		
		this.controller = controller;
		this.listContainer = new VBox();
		this.displayedListBox = new HBox();
		this.pointedBox = new HBox();
	
	}
	
	public void setPointedBox(HBox pointedBox) {
		this.pointedBox = pointedBox;
	}
	
	public void setActiveTaskList(int index) {
		if(StorageManager.isStorageEmpty()) {
			this.activeTaskList = new TaskList();
			return;
		}
		this.activeTaskList = StorageManager.getTaskLists().get(0);
		System.out.println("CONTAINER VIEW: Task list 0 added");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for(int i = 0; i<StorageManager.getTaskLists().size(); i++) {
			
			TaskList list = StorageManager.getTaskLists().get(i);
			displayTaskList(list);	
		}
		
	}

	
	/**This method creates the view of a task list.*/
	private HBox displayTaskList(TaskList list) {
		
		ListBox newTaskBox = new ListBox(list, this);
		HBox listBox = newTaskBox.getListBox();
		
		listBox.setOnMouseClicked(event -> {
			
			displayedListBox.setStyle("-fx-background-color: rgb(134, 129, 121);");
			listBox.setStyle("-fx-background-color: rgb(51, 49, 46);");
			
			displayedListBox = pointedBox;
			activeTaskList = StorageManager.getTaskListByName(list.getId());
			
			//Display tasks
			System.out.println("LIST CONTAINER: displaying " + activeTaskList.getId());
			controller.displayList(activeTaskList);
		});
		
		listContainer.getChildren().add(listBox);
		return listBox;
	}
	
	
	@FXML
	private void addNewList() {
		String newListName = listNameField.getText();
		listNameField.clear();
		//if the same name exists --> notify

		if(StorageManager.doesListExist(newListName)) {
			listNameField.setPromptText(FieldMessages.nameTakenText);
			return;
		} else if(newListName.equals("")) {
			listNameField.setPromptText(FieldMessages.noIdPromptText);
		}
		
		TaskList newList = new TaskList(newListName);
		StorageManager.addTaskList(newListName);
		listNameField.setPromptText(FieldMessages.standardPromptText);
		activeTaskList = newList;
		displayTaskList(newList);
		controller.displayList(activeTaskList);
	}
	
	@FXML
	private void displayTodayTasks() {
		LocalDate date = LocalDate.now();
		
		if(StorageManager.getTaskByDate(Date.valueOf(date)).isEmpty()) {
			return;
		}
		
		TaskList todayList = new TaskList();
	}
	
	
	private void tmp() {
		//Get the list name
		String name = listNameField.getText();
		listNameField.clear();
		
		//Check if the list already exists
		if(StorageManager.doesListExist(name)) {
			listNameField.setPromptText(FieldMessages.nameTakenText);
			return;
		} else if(name.equals("")) {
			listNameField.setPromptText(FieldMessages.noIdPromptText);
		}
		
		//add to storage
		StorageManager.addTaskList(name);
		activeTaskList = StorageManager.getTaskLists().getLast();
		//display
		controller.displayList(activeTaskList);
	}
	
	
	
	private boolean containsTaskList(String newName) {
		for(TaskList list: StorageManager.getTaskLists()) {
			if(list.getId() == newName) {
				return true;
			}
		}
		return false;
	}
	
	
	/**This method removes the HBox representing the list 
	 * at the index passed as parameter.*/
	public int removeList(TaskList list) {
		
		controller.removeTask(list);
		displayLists();
		return 1;
		
	}
	

	private void displayLists() {
		listContainer.getChildren().clear();
		for(TaskList list:StorageManager.getTaskLists()) {
			displayTaskList(list);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
