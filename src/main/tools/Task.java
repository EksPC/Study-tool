package main.tools;

import java.sql.Date;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlRootElement;

/**This class represents the model of a task.*/
@XmlRootElement 
public class Task {
	
	private LocalDate expiration;
	private String name;
	private boolean completed;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public LocalDate getExpiration() {
		return expiration;
	}
	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	
	
	
	
}
