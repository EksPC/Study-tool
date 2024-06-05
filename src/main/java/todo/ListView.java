package main.java.todo;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.print.attribute.standard.DateTimeAtCompleted;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.java.MainController;
import main.java.StorageManager;
import main.java.model.Task;
import main.java.model.TaskList;
import main.java.model.TodoList;

public class ListView implements Initializable{

	@FXML private Label listName;
	@FXML private VBox listBox;
	@FXML private Button newTaskButton;
	@FXML private TextField nameField;
	@FXML private DatePicker dateField;
	
	private String mainPromptText = "type in a task";
	private String noNamePromptText = "please type in a task";
	private String invalidDateText = "please insert a valid date";

	
	private TodoController controller;
	private TaskList currentList;
	
	public ListView(TodoController controller) {
		this.controller = controller;
//		if(StorageManager.isStorageEmpty()) {
//			this.currentList = new TaskList("");
//			return;
//		}
		this.currentList = StorageManager.getTodayTaskList();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayTodayList();
	}
	
	
	public TaskList getCurrentList() {
		return currentList;
	}
	
	private HBox buildTask(Task task) {
		
		HBox box = new HBox();
		
		CheckBox check = new CheckBox(task.getName());
//		Label expire = new Label(task.getExpiration().toString());
		
		box.setMaxSize(80, 00);
		box.setPadding(new Insets(0,0,0,20));
//		box.getChildren().addAll(check, expire);
		box.setStyle("-fx-border: solid 2px black;");
		box.getStyleClass().add("task");
		box.getChildren().add(check);
		
		return box;
	}
	
	@FXML
	private void addTask() {
		//TODO add expiring time and check flag
		
		String newTaskName = nameField.getText();
		nameField.clear();
		
		LocalDate localDate;
		dateField.getEditor().clear();
		
		localDate = dateField.getValue();
		if(localDate==null) {
			localDate = LocalDate.MIN;
		}
		
		if(newTaskName.equals("")) {
			nameField.setPromptText(noNamePromptText);
			return;
		} 
		
		Task newTask = new Task(newTaskName,Date.valueOf(localDate).toString());
		
		System.out.println("Adding new Task: " + newTaskName + " to "+currentList.getId());
		
		listBox.getChildren().add(buildTask(newTask));
		StorageManager.addTaskToList(currentList.getId(),newTask);
		
		
		nameField.setPromptText(mainPromptText);
	}
	
	public void displayTaskList(TaskList list) {
		
		System.out.println("ListView.displayTaskList(): list displayed = " + list.getId());
		
		this.currentList = list;
		listName.setText(list.getId());
		
		nameField.setVisible(true);
		dateField.setVisible(true);
		newTaskButton.setVisible(true);
				
		listName.setText(list.getId());
		clearTasks();
		
		if(list.isEmpty()) {
			return;
		}
		
		for(Task task:list.getTasks()) {
			listBox.getChildren().add(buildTask(task));
		}
	}

	public void clearTasks() {
		while(!listBox.getChildren().isEmpty()) {
			listBox.getChildren().removeLast();
		} 
	}
	
	public void displayTodayList() {
		displayTaskList(StorageManager.getTodayTaskList());
		nameField.setVisible(false);
		dateField.setVisible(false);
		newTaskButton.setVisible(false);
	}
	
	
	
	
	
	
	
	
	
}
