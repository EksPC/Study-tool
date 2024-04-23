package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import main.calendar.CalendarController;
import main.focus.FocusController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.todo.TodoController;
import main.tools.Task;

/**This class is the brain of the application.
 * 
 * @author Francesco Carboni
 * @version 0.1*/
public class MainController {
	
	private AppController appController;
	private CalendarController calendarController;
	private FocusController focusController;
	private TodoController todoController;
	private Loader loader;
	private int pageNumber;
	private final String applicationPath = "/fxml/applicationView.fxml";
	
	private Main main;
	private BorderPane root;
//	private DBManager manager;
	
	
	public MainController() {
		focusController = new FocusController(this);
		todoController = new TodoController(this);
		calendarController = new CalendarController();
		appController = new AppController(this);
		loader = new Loader();
		pageNumber = 1;
	}
	
	public void storeData() {
		Task nt = new Task();
		nt.setCompleted(true);
		nt.setExpiration(LocalDate.now());
		nt.setName("Culo");
		
		loader.storeData(nt);
	}
	
	/*TODO
	 * Decidi qual e' la strategia con cui creare le classi:
	 * - Ogni volta viene ricreata da zero l'intera interfaccia <--
	 * - Si tiene conto di una variabile per capire se la pagina dev'essere aggiornata o meno?
	 * 
	 * Strategia giusta:
	 * 	Iniziare */
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
