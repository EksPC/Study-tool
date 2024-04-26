package main.java.tools;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class TodoList {

	private Color color;
	private String name;
	private ArrayList<Task> tasks;
	
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(int index) {
		tasks.remove(index);
	}
	
	/**This method return the integer value representing the progress precentage.*/
	public int getProgress() {
		int counter = 0;
		for(Task t:tasks) {
			if(t.isCompleted()) {
				counter++;
			}
		}
		
		return (counter/tasks.size()-1)*100;
	}
	
}
