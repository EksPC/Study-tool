package main.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.junit.jupiter.api.io.CleanupMode;

@XmlRootElement
public class Session {

	private int pomodoroNum;
	private int completedPomo;
	private String sessionInfo;
	private int pomodoroLength;
	private int breakLength;
	private boolean sessionFlag = false; //this starts false because the session is not started by default
	private int sessionProgress;
	
	/**Basic constructor. Not all the fields are required as arguments because concludedSessions and sessionFlag are
	 * modified through setter methods.*/
	public Session(int pomoNum, String sessionInfo, int pomodoroLength, int breakTime) {
		this.pomodoroNum = pomoNum;
		this.sessionInfo = sessionInfo;
		this.pomodoroLength = pomodoroLength;
		this.breakLength = breakTime;
	}
	
	/**Dummy constructor, default settings.*/
	public Session() {
		this.pomodoroNum = 3;
		this.sessionInfo = "DAJE ROMAAAA!";
		this.pomodoroLength = 45;
		this.breakLength = 15;
	}
	
	
	/**This method returns the number of remaining pomodori of the session.*/
	public int getRemainingPomodori(){
		return (pomodoroNum - completedPomo);
	}
	
	/***
	 * This method is triggered whenever a session is completed or skipped: 
	 * if it is the last one remaining in the session the session ends without 
	 * the last break, else the break period is started.
	 */
	public int completePomodoro() {
		pomodoroNum--;
		return pomodoroNum;
	}
	
	public String getSessionInfo() {
		return sessionInfo;
	}
	
	/**This method modify session times (pomodoro and break), 
	 * it is called when settings are changed.
	 * 
	 * @param pTime pomodoro time
	 * @param bTime break time */
	public void setPomodoro(int pTime, int bTime) {
		
	}
	
	public int getPomodoroLength() {
		return pomodoroLength;
	}
	
	public int getBreakLength() {
		return breakLength;
	}
	
	/**This method allows you to set a flag based on the session state:
	 * when a session is started it's flag becomes true, otherwise is false.*/
	public void setSessionFlag(boolean state) {
		this.sessionFlag = state;
	}
	
	public void reset() {
		completedPomo = 0;
	}
	
	/**This method returns true if the section is active, false otherwise.*/
	public boolean isActive() {
		return sessionFlag;
	}
	
	/**This method returns the increment factor of the {@code ClockController 
	 * progress indicator} and it */
	public double getIncrement(boolean focus) {
		
		return focus?(Double)(1.0/pomodoroLength):(Double)(1.0/breakLength);
	}
	
}

