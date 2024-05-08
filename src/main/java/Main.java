package main.java;
import javafx.application.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

	private static final double WIDTH = 200;
    private static final double HEIGHT = 200;
    private static final double RADIUS = 80;
    private static final double START_ANGLE = 90;
    private static final double LENGTH = 360;
    
    private static int angle = 0;
    private static MainController controller = new MainController();
    
    
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
	

