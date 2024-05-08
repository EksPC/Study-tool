package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.java.focus.FocusController;
import main.java.calendar.CalendarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;
import main.java.todo.TodoController;
import main.java.tools.TasksStorage;
import main.java.tools.Session;
import main.java.tools.Task;
import main.java.tools.TaskList;

/**This class is the brain of the application.
 * 
 * @author Francesco Carboni
 * @version 0.1*/
public class MainController {
	
	private AppController appController;
	private CalendarController calendarController;
	private FocusController focusController;
	private TodoController todoController;
	private StorageManager storageManager;
	private int pageNumber;
	private final String applicationPath = "/fxml/applicationView.fxml";
	
	private Main main;
	private BorderPane root;
//	private DBManager manager;
	
	
	public MainController() {
		StorageManager.readTasks();
		focusController = new FocusController(this);
		todoController = new TodoController(this);
		calendarController = new CalendarController();
		appController = new AppController(this);
		pageNumber = 1;
	}	
		
	

	public BorderPane getApplicationView() {
		
		FXMLLoader appLoader = new FXMLLoader(getClass().getResource(applicationPath));
		appLoader.setController(appController);
		root = new BorderPane();
		
		try {
			root = appLoader.load();
		} catch(IOException e) {
			System.out.println("ROOT LOADING ERROR:");
			e.printStackTrace();
		}
		
		root.setCenter(focusController.getUserInterface());
		
		return root;
	}
	
	/**1 - focus
	 * 2 - todo
	 * 3 - calendar
	 * 
	 * 0 - settings*/
	public void changePage(int code) {
		switch(code) {
		case 0:
			//settings
			if(pageNumber != 0) {
				pageNumber = 0;
				System.out.println("Pg: " + pageNumber);
			}
			break;
			
		case 1:
			if(pageNumber != 1) {
				root.setCenter(focusController.getUserInterface());
				pageNumber = 1;
				System.out.println("Pg: " + pageNumber);
			}
			break;
			
		case 2:
			if(pageNumber != 2) {
				root.setCenter(todoController.getUserInterface());
				pageNumber = 2;
				System.out.println("Pg: " + pageNumber);
			}
			break;

		case 3:
			if(pageNumber != 3) {
				pageNumber = 3;
				System.out.println("Pg: " + pageNumber);
//				root.setCenter(CalendarController.getUserInterface());
			}
			break;
			//calendar
		}
	}
	
	
	//Assemblaggio applicazioni
	
	
	
	
	
	
	
	
}
