package main.java;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.UnmarshalException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.tools.Session;
import main.tools.Task;

/**This class is responsible for saving FXML-file-paths, associate them to loaders and,
 * once the MainController bound loaders and controllers, this class assembles all the components
 * to build the user interface.*/
public class Loader {
	
	//XML session
	private final String sessionFilePath = "sessionSettings.xml";
	private final String tasksFilePath = "tasks.xml";
	
	private File sessionFile;
	private File tasksFile;
	
	public Loader() {
		sessionFile = new File(sessionFilePath);
		tasksFile = new File(tasksFilePath);
		
	}
	//XML tasks
	
	public Session getSessionSettings() {
		try {
			JAXBContext jcSession = JAXBContext.newInstance(Session.class);
			Unmarshaller unmarshaller = jcSession.createUnmarshaller();
			Session session = (Session)unmarshaller.unmarshal(sessionFile);
			System.out.println(session.getPomodoroLength());
			return session;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[SESSION] DATA UNMARSHALLING ERROR");
			e.printStackTrace();
			return null;
		}
	}
	
//	//returns an array list containing 
//	public ArrayList<Task> getTasks(){
//		try {
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//		
	
	public void storeData(Session session) {
		//settings change
		try {
			JAXBContext jcSession = JAXBContext.newInstance(Session.class);
			Marshaller marshaller = jcSession.createMarshaller();
			marshaller.marshal(session, sessionFile);
			
		} catch (JAXBException e) {
			
			System.out.println("[SESSION] DATA MARSHALLING ERROR");
			e.printStackTrace();
		}
		//tasks add or deletion
	}
	
	public void storeData(Task task) {
		try {
			JAXBContext jcTask = JAXBContext.newInstance(Task.class);
			Marshaller marshaller = jcTask.createMarshaller();
			marshaller.marshal(task, tasksFile);
			System.out.println("Storing: " + task.getName());
		} catch (JAXBException e) {
			
			System.out.println("[TASK] DATA MARSHALLING ERROR");
			e.printStackTrace();
		}
	}
}
