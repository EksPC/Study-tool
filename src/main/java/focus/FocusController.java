package main.java.focus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.java.MainController;
import main.java.tools.SessionController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


/**This class is responsible for handling events of the focus section, or 
 * the application's left side, for example starting a session or modifying its settings.*/
public class FocusController {

	private ClockView clock;
	private NotesView guide;
	private SessionController session;
	private MainController controller;
	
	private String[] paths = {"/fxml/focusClock.fxml",
							  "/fxml/focusGuide.fxml"}; 	
	
	public FocusController(MainController controller) {
		
		this.controller = controller;
		
		this.clock = new ClockView();
		this.guide = new NotesView(this);
		this.session = new SessionController();
		
	}
	
	
	/**The following functions are getter methods used by mainController to bind Loaders to controllers.*/
	public StackPane getUserInterface() {
		
		FXMLLoader clockLoader = new FXMLLoader(getClass().getResource(paths[0])); //clock
		FXMLLoader notesLoader = new FXMLLoader(getClass().getResource(paths[1])); //guide
		HBox tmp = buildContainer();

		clockLoader.setController(clock);
		notesLoader.setController(guide);

		try {
			tmp.getChildren().add(clockLoader.load());
			tmp.getChildren().add(notesLoader.load());
		} catch(IOException e) {
			e.printStackTrace();
		}
		return new StackPane(tmp);
	}
	
	
	/**This method creates the focus section UI with a specific style and returns it.*/
	private HBox buildContainer() {
		
		HBox focusSection = new HBox();
		focusSection.setPrefHeight(880);
		focusSection.setPrefWidth(1470);
		focusSection.setAlignment(Pos.CENTER);
		focusSection.setSpacing(250);
//		focusSection.setPadding(new Insets(0,0,0,20));
		
		return focusSection;
	}

	
	
	
	
	
	
	
	
	
}
