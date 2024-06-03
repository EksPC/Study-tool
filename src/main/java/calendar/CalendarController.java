package main.java.calendar;

import java.io.IOException;

import com.sun.tools.javac.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalendarController {

	private String[] paths = {"/fxml/calendarView.fxml"};
	private CalendarView calendarView;

	
	public CalendarController() {
		this.calendarView = new CalendarView();
		
	}
	
	public BorderPane getUserInterface() {
		FXMLLoader calendarLoader = new FXMLLoader(getClass().getResource(paths[0]));
		calendarLoader.setController(calendarView);
		
		GridPane calendar = new GridPane();
		BorderPane view = new BorderPane();
		
		try {
//			calendar = calendarLoader.load();
			view = calendarLoader.load();
		} catch (IOException e) {
			System.out.println("CALENDAR LOADING ERROR");
			e.printStackTrace();
		}
		
		calendarView.displayContent();
//		return setCalendar(calendar);
		return view;
	}
	
	

	
	
	
	
	
}
