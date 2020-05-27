package org.angrybee.meet.utils.data;

import java.io.Serializable;

public class User implements Serializable {

	
	private String id;
	
	private String displayname;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	public User(String id,String displayname, String password, String firstname, String lastname, String email) {
		this.id = id;
		this.displayname = displayname;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplyname(String displayname) {
		this.displayname = displayname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
