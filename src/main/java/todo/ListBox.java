package main.java.todo;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.java.StorageManager;
import main.java.model.TaskList;

public class ListBox {

	private TaskList list;
	private ListContainerView container;
	
	public ListBox(TaskList list, ListContainerView container) {
		this.container = container;
		this.list = list;
	}

	public HBox getListBox() {
		
		HBox taskBox = new HBox();
		Label taskName = new Label(list.getId());
		Button deleteButton = new Button("D");
		
		deleteButton.setVisible(false);
	
		deleteButton.setOnMouseClicked(event -> {
			
			StorageManager.deleteList(list);
			container.removeList(list);
		});
		
		taskBox.getStyleClass().add("list-button");
		
		taskBox.setOnMouseEntered(event -> {
			deleteButton.setVisible(true);
			container.setPointedBox(taskBox);
		});
		
		taskBox.setOnMouseExited(event -> {
			deleteButton.setVisible(false);
			
		});
		
		
		taskBox.getChildren().add(taskName);
		taskBox.getChildren().add(deleteButton);
		
		
		
		return taskBox;
	}

}


