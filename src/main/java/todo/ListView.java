package main.java.todo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import main.java.tools.Task;
import main.java.tools.TaskList;
import main.java.tools.TodoList;

public class ListView implements Initializable{

	@FXML private Label listName;
	@FXML private VBox listBox;
	@FXML private Button newTaskButton;
	@FXML private TextField nameField;
	@FXML private DatePicker dateField;
	
	private String mainPromptText = "type in a task";
	private String noNamePromptText = "please type in a task";
	
	private TaskList list;
	private TodoController controller;
	
	public ListView(TodoController controller) {
		this.controller = controller;
		if(StorageManager.isStorageEmpty()) {
			this.list = new TaskList();
		}
		else {
			this.list = StorageManager.getTaskLists().get(0);
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		displayTaskList(list);
		
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
		
		if(newTaskName.equals("")) {
			nameField.setPromptText(noNamePromptText);
			return;
		} 
		
		System.out.println("Adding new Task: " + newTaskName);
		listBox.getChildren().add(buildTask(new Task(newTaskName)));
		StorageManager.addTaskToList(list.getId(),new Task(newTaskName));
		
		
		nameField.setPromptText(mainPromptText);
		
//		Date date = new Date();
//		if(dateField.get) {
//			
//		}
	}
	
	public void displayTaskList(TaskList list) {
		this.list = list;
		listName.setText(list.getId());
		clearTasks();
		
		if(list.isEmpty()) {
			return;
		}
		
		for(Task task:list.getTasks()) {
			listBox.getChildren().add(buildTask(task));
		}
	}
	
	
	private void clearTasks() {
		while(!listBox.getChildren().isEmpty()) {
			listBox.getChildren().removeLast();
		}
		
	}
	
	
	
}
