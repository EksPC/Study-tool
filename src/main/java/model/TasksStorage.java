package main.java.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TasksStorage{

	private HashMap<String,TaskList> todoLists;
	private HashMap<String,TaskList> calendarDates;
	
	public TasksStorage() {
		todoLists = new HashMap<String, TaskList>();
		calendarDates = new HashMap<String, TaskList>();
		
	}
	
	public void setTodoLists(HashMap<String,TaskList> todoLists) {
		this.todoLists = todoLists;
	}
	
	public void setCalendarDates(HashMap<String,TaskList> calendarDates) {
		this.calendarDates = calendarDates;
	}
	
	public HashMap<String, TaskList> getTodoLists() {
		return todoLists;
	}
	
	
	public HashMap<String,TaskList> getCalendarDates(){
		return calendarDates;
	}
	
	public void addTaskToList(String listName, Task task) {
		
	}

}
