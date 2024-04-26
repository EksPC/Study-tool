package main.java.tools;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TasksStorage {

	private ArrayList<TaskList> lists;
	
	public TasksStorage() {
		lists = new ArrayList<TaskList>();
	}

	public ArrayList<TaskList> getLists() {
		return lists;
	}

	public void setLists(ArrayList<TaskList> lists) {
		this.lists = lists;
	}
	
	
}
