package main.java.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**This class represents the model of a task.*/
//@XmlAccessorType(XmlAccessType.FIELD)
public class Task implements Serializable{
	
	private String expiration = "";
	private String name;
	private boolean completed;
	private String listName;
	
	/**Complete constructor.
	 * @param name
	 * @param expiration Date in string format
	 * @param completed flag for completion*/
	public Task(String name/*,String expiration, boolean completed*/) {
//		this.expiration = expiration;
		this.name = name;

//		this.completed = completed;
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
