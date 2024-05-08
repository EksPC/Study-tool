package main.java.focus;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class NotesView implements Initializable{
	
	private FocusController controller;
	
	@FXML 
	private TextArea inputField;
	@FXML 
	private Button clearButton;
	
	@FXML 
	private void clearInputField(final ActionEvent event) {
		inputField.clear();
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	public NotesView(FocusController controller) {
		this.controller = controller;
	}
}
