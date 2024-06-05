package main.java.model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
public class TaskList{

	private String id;
	private ArrayList<Task> tasks;
	
	public TaskList() {
		System.out.println("TaskList constructor");
		tasks = new ArrayList<Task>();
	}
	
	public TaskList(String id) {
		this.id = id;
		this.tasks = new ArrayList<Task>();
	}
	
	public TaskList(ArrayList<Task> tasks, String id) {
		this.id = id;
		this.tasks = tasks;
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
	
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	
}
