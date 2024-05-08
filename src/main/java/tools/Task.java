package main.java.tools;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**This class represents the model of a task.*/
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
	
	private String expiration;
	private String name;
	private boolean completed;
	
	/**Complete constructor.
	 * @param name
	 * @param expiration Date in string format
	 * @param completed flag for completion*/
	public Task(String name/*,String expiration, boolean completed*/) {
//		this.expiration = expiration;
		this.name = name;
//		this.completed = completed;
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
