package main.java.todo;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.java.StorageManager;

public class ListBox {

	private String listId;
	private ListContainerView container;
	
	public ListBox(String listId, ListContainerView container) {
		this.container = container;
		this.listId = listId;
	}

	public HBox getListBox() {
		
		HBox taskBox = new HBox();
		Label taskName = new Label(listId);
		Button deleteButton = new Button("D");
		
		deleteButton.setVisible(false);
	
		deleteButton.setOnMouseClicked(event -> {
			
			int index = StorageManager.deleteList(listId);
			if(index < 0) {
				System.err.println("LIST BOX ~ DELETE BUTTON: INDEX ERROR");
				return;
			}
			container.removeList(index);
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


