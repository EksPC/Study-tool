package main.java.focus;

import java.net.URL;

import java.util.ResourceBundle;

import javax.print.DocFlavor.STRING;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import main.java.model.SessionController;



/**
 * This class handles the focus-clock functionalities.
 * */
public class ClockView implements Initializable{
	
	private FocusController controller;
	private boolean flag; //true for start, false for pause
	private SessionController session;
	private double increment; //calculate from session	
	private double progress;
	private int remainingTime;
	private int currentState;
	private Timeline currentTimeline;
	
	@FXML private HBox timeField;
	@FXML private ProgressIndicator progressIndicator;
	@FXML private Circle cover; //Cover to activate when session completed
	@FXML private Label timeDescriptor;
	
	@FXML private ImageView restartButton;
	@FXML private ImageView startButton;
	@FXML private ImageView pauseAndContinueButton;
	
	@FXML 
	private void buttonClicked(){
		//trigger button
		if(flag) {
			pauseAndContinueButton.setImage(new Image("/pics/play.png"));
			currentTimeline.pause();
			flag = false;
		} else {
			pauseAndContinueButton.setImage(new Image("/pics/pause.png"));
			currentTimeline.play();
			flag = true;
		}
		
	}

	
	@FXML
	private void pauseSession() {
		
		System.out.println("pause btn");
		currentTimeline.pause();
		restartButton.setVisible(true);
	}
	
	
	
	@FXML
	private void startSession() {
		System.out.println("Start ");
		flag = true;
		startButton.setVisible(false);
		restartButton.setVisible(true);
		pauseAndContinueButton.setVisible(true);
		startClock();
	}
	
	
	
	@FXML
	public void restartSession() {

		currentTimeline.stop();
		session.reset();
		currentState = 0;
		setVars();
		formatTimeField();
		
		startButton.setVisible(true);
		restartButton.setVisible(false);
		pauseAndContinueButton.setVisible(false);
	}	
	
	
	

	
	
	

	
	/**Constructor*/
	public ClockView() { 
		this.session = new SessionController();	//TODO session parameter
		currentState = 0;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		increment = session.getIncrement(true); 
		pauseAndContinueButton.setVisible(false);
		restartButton.setVisible(false);
		flag = false;

		setVars();
		formatTimeField();
	}
	
	
	/**This method starts a focus clock whose value can be {@code pomodoro length or break length,
	 * both from Session class}.
	 * 
	 * @param focus sets the clock value: true is pomodoro, false is break
	 * */
	public void startClock() {
		
		if(currentState == 0) {
			currentState = 2;
			setVars();
		}
		
		currentTimeline = new Timeline(new KeyFrame(Duration.minutes(1), event -> {
			progress+=increment;
			System.out.println("minutesTimeline - "+remainingTime);
			progressIndicator.setProgress(progress);
			remainingTime--;
			formatTimeField();
		}));

		
		
		currentTimeline.setCycleCount(remainingTime);
		currentTimeline.setOnFinished(event -> {
			//When finished, the last
			remainingTime = 10; //TODO change to 60 (a minute)
			//If the session finished
			if(session.getRemainingPomodori() == 0) {
				currentState = 0;
				setVars();
				return;
			}
			
			currentState = (currentState == 1)?2:1;
			setVars();
			
			
		});
		
		currentTimeline.play();
	}
	
	
	/**This function handles the end of a time slice (both a pomodoro or a break).*/
	private void handleTimeSliceEnd() {
		/*1. Session finished if remainingPomodoro == 0:
		 * 	- session reset
		 *  - view reset
		 *  
		 *2. Pomodoro finished (break starts) if focus = true
		 *	- view changes -> setVars*/
	}

	
	
	
	
	/**This method sets the correct value to the clock variables, based
	 * on the input value.
	 * @param focus True if the clock is in focus mode, false if it is in break mode*/
//	private void setVars(boolean focus) {
//		
//		if(focus) {
//			timeDescriptor.setText("focus");
//			remainingTime = session.getPomodoroLength();
//		} else {
//			remainingTime = session.getBreakLength();
//			timeDescriptor.setText("break");
//		}
//		
//		progress = 0;
//		progressIndicator.setProgress(progress);
//		increment = session.getIncrement(focus);
//		formatTimeField();
//		cover.setVisible(false);
//	}
	
	/**
	 * @param code: 0 for inactive session, 1 for session break, 2 for session pomodoro*/
	private void setVars() {
		//session finished
		switch(currentState) {
			//inactive session
		case(0):
			session.reset();
			progress = 0;
			progressIndicator.setProgress(progress);
			cover.setVisible(true);
			timeDescriptor.setText("ready");
			remainingTime = session.getPomodoroLength();
			break;
			
			//session break
		case(1):
			progress = 0;
			progressIndicator.setProgress(progress);
			cover.setVisible(false);
			timeDescriptor.setText("break");
			remainingTime = session.getBreakLength();
			
			break;
			
			//session pomodoro
		case(2):
			progress = 0;
			progressIndicator.setProgress(progress);
			cover.setVisible(false);
			timeDescriptor.setText("focus");
			remainingTime = session.getPomodoroLength();
			break;
		}
		
	}
	
	/**This method handles the last minute of clock by showing the timer formatted in seconds.*/

	
	
	
	/**Sets timeField values based on remainingTime.*/
	void formatTimeField() {
		int hours = remainingTime/60;
		int minutes = remainingTime%60;
		
		System.out.println("FORMAT - Hours: "+hours+"\nMinutes: "+minutes);
		
		if(hours >= 1) {
			//modify hours and minutes
			((Label)timeField.getChildren().get(0)).setText(String.valueOf(hours));
			((Label)timeField.getChildren().get(1)).setText("h");
			//else if the hours fields are still visible
		} else if (!((Label)timeField.getChildren().get(0)).getText().equals("")){
			//Hide hours value and "h"
			((Label)timeField.getChildren().get(0)).setText("");
			((Label)timeField.getChildren().get(0)).setPrefWidth(0);
			((Label)timeField.getChildren().get(1)).setText("");
			((Label)timeField.getChildren().get(1)).setPrefWidth(0);
			timeField.setAlignment(Pos.TOP_CENTER);
		}
		((Label)timeField.getChildren().get(3)).setText("min");
		((Label)timeField.getChildren().get(2)).setText(String.valueOf(minutes));
	}

	
	

	
	
//	/**This method formats properly the timeField variable (HBox), by
//	 * modifying {@code timeField properties} based on {@code remainingTime}
//	 * value. 
//	 * */	
}
