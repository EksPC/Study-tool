package main.java;

import java.io.Serializable;

public class User implements Serializable {

	private String name;
	private String password;
	
	public User(String usr, String psw) {
		this.name = usr;
		this.password = psw;
	}
	
	public void printCredentials() {
		System.out.println(name + " - " + password);
	}
}
