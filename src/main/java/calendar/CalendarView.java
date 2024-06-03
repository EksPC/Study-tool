package main.java.calendar;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.java.StorageManager;
import main.java.model.Task;
import main.java.model.TaskList;

public class CalendarView implements Initializable {
	
	@FXML private GridPane centerField;
	@FXML private VBox taskSection;
	@FXML private Button dayButton;
	@FXML private Button weekButton;
	@FXML private Button monthButton;
	
	private final String styleSheets = "/style.css";
	private String today;
	 
	public CalendarView() {
		Calendar current = Calendar.getInstance();
		current.set(Calendar.HOUR_OF_DAY, 0); // same for minutes and seconds
		this.today = current.getTime().toString();
	}
	
	
	/**List of functionalities:
	 * - button clicking --> changing UI month, week, day
	 * - task selection
	 * */


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//stampare le tasks
//		for(TaskList list:StorageManager.getTaskLists()) {
//			for(Task task : list.getTasks()) {
//				dateContainer.get(task.getExpiration()).add(task);
//			}
//		}
		
	}
	
	
	
	public void displayContent() {
		for(TaskList list:StorageManager.getTaskLists()) {
			taskSection.getChildren().add(buildListView(list.getId()));
		}
	}
	
	private CheckBox buildListView(String listName) {
		CheckBox box = new CheckBox(listName);
		
		box.getStylesheets().add(styleSheets);
		box.getStyleClass().add("calendar-list-button");
		
		box.setOnAction(event -> {
			if(box.isSelected()) {
				//display the content on the page
			} else {
				//remove the content from the page
			}
		});
		
		return box;
	}
	
	/*CALENDAR IMPLEMENTATION
	 * BucketList for containing tasks:
	 * -Each bucket represents the day we are looking for
	 * -In the three visualization mode there are only a princple:
	 * 		for each day, print the checked lists
	 * 
	 * */
	
//	public GridPane setCalendar(GridPane grid) {
//		
//		for(int i = 0; i<7; i++) {
//			for(int j = 0; j<5;j++) {
//				grid.add(buildSingleCell(), i, j);
//			}
//		}
//		return grid;
//	}
//	
//	public VBox buildSingleCell(int d) {
//		VBox cell = new VBox();
//		cell.setStyle("-fx-border-color: black;");
//		Label date = new Label(Integer.toString(d));
//		cell.getChildren().add(date);
//		return cell;
//	}

	
	
	
	

}
