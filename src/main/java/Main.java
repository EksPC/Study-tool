package main.java;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import javafx.application.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.calendar.CalendarController;
import main.java.model.TasksStorage;


public class Main extends Application{

    private static MainController controller = new MainController();
    private static CalendarController calendar = new CalendarController();
    
    
    @Override
    public void start(Stage stage) {
    	
    	Scene scene = new Scene(controller.getApplicationView());
//    	Scene scene = new Scene(calendar.getUserInterface());
    	
    	stage.setScene(scene);
    	stage.setTitle("FOCUS TOOL");
    	stage.show();	
	  }
    	
    

    public static void main(String[] args) {
    	launch(args);


    }
    
}
	

