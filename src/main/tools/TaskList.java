package main.java.tools;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import main.java.tools.Task;

//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskList {

	@XmlAttribute
	private String id;
	
	private ArrayList<Task> tasks;
	
	public TaskList() {
		tasks = new ArrayList<Task>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	

	
}
