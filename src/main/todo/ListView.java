package main.todo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.tools.Task;
import main.tools.TodoList;

public class ListView implements Initializable{

	@FXML private Label listName;
	@FXML private VBox listBox;
	
	private TodoList list;
	
	public ListView(TodoList list) {
		this.list = list;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private void buildList() {
		VBox box = new VBox();
		box.setMaxWidth(400);
		
		for(Task t : list.getTasks()) {
			box.getChildren().add(buildTask(t));
		}
		listBox = box;
	}
	
	
	private HBox buildTask(Task task) {
		
		HBox box = new HBox();
		
		CheckBox check = new CheckBox(task.getName());
		Label expire = new Label(task.getExpiration().toString());
		
		box.setMaxSize(80, 00);
		box.setPadding(new Insets(0,0,0,20));
		box.getChildren().addAll(check, expire);
		box.setStyle("-fx-border: solid 2px black;");
		
		return box;
	}
	
	
}
