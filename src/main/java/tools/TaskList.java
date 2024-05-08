package main.java.tools;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;



//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskList {


	private String id;
	
	private ArrayList<Task> task;
	
	public TaskList() {
		System.out.println("TaskList constructor");
		task = new ArrayList<Task>();
	}
	
	public TaskList(String id) {
		this.id = id;
		this.task = new ArrayList<Task>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Task> getTasks() {
		return task;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.task = tasks;
	}
	
	public boolean isEmpty() {
		return task.isEmpty();
	}

	
}
