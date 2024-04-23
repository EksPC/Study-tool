package main.java;
import javafx.application.*;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle.Control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Main extends Application{

	private static final double WIDTH = 200;
    private static final double HEIGHT = 200;
    private static final double RADIUS = 80;
    private static final double START_ANGLE = 90;
    private static final double LENGTH = 360;
    
    private static int angle = 0;
    Arc arc = new Arc();
    private MainController controller = new MainController();
    
    
    @Override
    public void start(Stage stage) {
    	
    	Scene scene = new Scene(controller.getApplicationView());
    	stage.setScene(scene);
    	stage.setTitle("FOCUS TOOL");
    	stage.show();
	  }
    	
    

    public static void main(String[] args) {
    	
    	launch(args);
    	
    }
}
	

