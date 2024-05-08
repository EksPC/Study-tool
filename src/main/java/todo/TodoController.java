package main.java.todo;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import main.java.MainController;
import main.java.StorageManager;
import main.java.tools.TaskList;
import main.java.tools.TasksStorage;

public class TodoController {
	
	private ListView listView;
	private ListContainerView containerView;
	private MainController controller;
	private String[] paths = {"/fxml/todoList.fxml",
							  "/fxml/listContainer.fxml"};
	
	
	
	public TodoController(MainController controller) {
		this.controller = controller;
		this.listView = new ListView(this);
		this.containerView = new ListContainerView(this);
	}
	
	
	public HBox getUserInterface() {
		FXMLLoader listLoader = new FXMLLoader(getClass().getResource(paths[0]));
		FXMLLoader containerLoader = new FXMLLoader(getClass().getResource(paths[1]));
		listLoader.setController(listView);
		containerLoader.setController(containerView);
		
		HBox tmpBox = buildContainer();	
		
		try {
			tmpBox.getChildren().add(containerLoader.load());
			tmpBox.getChildren().add(listLoader.load());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return tmpBox;
	}
	
	
	private HBox buildContainer() {
		
		HBox todoSection = new HBox();
		todoSection.setPrefWidth(1540);
		todoSection.setPrefHeight(800);
		todoSection.setAlignment(Pos.CENTER);
		todoSection.setSpacing(30);
		todoSection.setPadding(new Insets(0,0,0,0));
		
		return todoSection;
	}
	
	
	public void displayList(TaskList list) {
		listView.displayTaskList(list);
	}
	
//	
//	private void addTaskList() {
//		storage.getLists().add(new TaskList());
//		manager.writeTasks(storage);
//	}
//	
}

