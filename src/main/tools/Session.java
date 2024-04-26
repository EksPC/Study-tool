package main.java.tools;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Session {
	/**Pomodoro Length - minutes*/
	private int PomodoroLength;
	
	/**Break Length - minutes*/
	private int BreakLength;
	
	private int sessionNumber;
	
	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public int getPomodoroLength() {
		return PomodoroLength;
	}

	public void setPomodoroLength(int pomodoroLength) {
		PomodoroLength = pomodoroLength;
	}

	public int getBreakLength() {
		return BreakLength;
	}

	public void setBreakLength(int breakLength) {
		BreakLength = breakLength;
	}

	
	
}
