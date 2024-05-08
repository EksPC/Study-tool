package main.java.todo;


import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Struct;
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
import main.java.tools.TaskList;
import main.java.tools.TasksStorage;
public class ListContainerView implements Initializable {
	
	private class FieldMessages {
		public final static String noIdPromptText = "please insert a name";
		public final static String standardPromptText = "type the list name";
		public final static String nameTakenText = "name already taken";
	}
	
	private TodoController controller;
	private ArrayList<TaskList> tasks;
	/**Needed for binding ListName to position in the */
	private HBox displayedListBox;
	private TaskList activeTaskList;
	private HBox pointedBox;
	
	@FXML private VBox listContainer;
	@FXML private Button newListButton;
	@FXML private TextField listNameField;
	
	public ListContainerView(TodoController controller) {
		
		this.tasks = StorageManager.getTaskLists();
		this.controller = controller;
		this.listContainer = new VBox();
		this.displayedListBox = new HBox();
		this.pointedBox = new HBox();
	
	}
	
	public void setPointedBox(HBox pointedBox) {
		this.pointedBox = pointedBox;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		for(int i = 0; i<tasks.size(); i++) {
			
			TaskList list = tasks.get(i);
			addTaskListView(list);	
		}
		
	}

	
	/**This method creates the view of a task list.*/
	private HBox addTaskListView(TaskList list) {
		
		ListBox newTaskBox = new ListBox(list.getId(), this);
		HBox listBox = newTaskBox.getListBox();
		
		
		listBox.setOnMouseClicked(event -> {
			displayedListBox.setStyle("-fx-background-color: rgb(134, 129, 121);");
			listBox.setStyle("-fx-background-color: rgb(51, 49, 46);");
			displayedListBox = listBox;
			activeTaskList = list;
			
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

		if(StorageManager.contains(newListName)) {
			listNameField.setPromptText(FieldMessages.nameTakenText);
			return;
		} else if(newListName.equals("")) {
			listNameField.setPromptText(FieldMessages.noIdPromptText);
		}
		
		addTaskListView(new TaskList(newListName));
		StorageManager.addTaskList(newListName);
		listNameField.setPromptText(FieldMessages.standardPromptText);
	}
	
	private boolean containsTaskList(String newName) {
		for(TaskList list: tasks) {
			if(list.getId() == newName) {
				return true;
			}
		}
		return false;
	}
	
	
	/**This method removes the HBox representing the list 
	 * at the index passed as parameter.*/
	public void removeList(int index) {
		listContainer.getChildren().remove(index);
		
		/*TODO
		 * REQUEST AN AUTHORIZATION*/
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
