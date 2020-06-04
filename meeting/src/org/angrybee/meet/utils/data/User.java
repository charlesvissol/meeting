package org.angrybee.meet.utils.data;

import java.io.Serializable;

public class User implements Serializable {

	
	private String id;
	
	private String displayname;
	
	private String ipAddress;
	
	private String[] networkInterfaces;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	public User(String id,String displayname, String ipAddress, String firstname, String lastname, String email) {
		this.id = id;
		this.displayname = displayname;
		this.ipAddress = ipAddress;
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
	
	public String[] getNetworkInterfaces() {
		return networkInterfaces;
	}

	public void setNetworkInterfaces(String[] networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIpAddress() {
		return this.ipAddress;
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
