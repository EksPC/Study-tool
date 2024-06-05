package main.java.model;

import java.sql.Date;
import java.time.LocalDate;
import javax.xml.bind.annotation.*;

/**This class represents the model of a task.*/
@XmlAccessorType(XmlAccessType.FIELD)
public class Task{
	
	private String expiration;
	private String name;
	private String listName;
	private boolean completed;
	
	/**Complete constructor.
	 * @param name
	 * @param expiration Date in string format
	 * @param completed flag for completion*/
	public Task(String name,String expiration/*, boolean completed*/) {
		this.expiration = expiration;
		this.name = name;

//		this.completed = completed;
	}
	
	public Task(String name) {
		this.name = name;
		this.expiration = Date.valueOf(LocalDate.MIN).toString();
	}

	public String getListName() {
		return listName;
	}
	
	/**Dummy constructor.*/
	public Task() {
	}
	
	public String getName() {
		return name;
	}
	
	public String getExpiration() { 
		return expiration;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	

	
	
	
	
}
