package main.java;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class AppController implements Initializable{
 
	@FXML private Pane page;
	@FXML private ImageView settingsButton;
	@FXML private ImageView focusButton;
	@FXML private ImageView todoButton;
	@FXML private ImageView calendarButton;
	
	private MainController controller;
	
	public AppController(MainController controller) {
		this.controller = controller;
	}
	
	
	private void changePage(int code) {
		controller.changePage(code);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		settingsButton.setOnMouseClicked(event -> {
			changePage(0);
		});
		
		todoButton.setOnMouseClicked(event -> {
			changePage(2);
		});
		
		focusButton.setOnMouseClicked(event -> {
			changePage(1);
		});
		
		calendarButton.setOnMouseClicked(event -> {
			changePage(3);
		});
	}
	
	public void setCenter(Pane center) {
		page = center;
	}
}
