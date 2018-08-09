package com.bms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	@NotNull
	@Email(message="Enter Unique Email Id Ex: abc@xyz.pqr")
	@Column(name = "username")
	private String username;
	
	@NotNull(message="Mobile No. cannot be Blank")
	@Column(name = "mobileno")
	private String mobileno;
	
	@NotNull(message="Password cannot be Blank")
	@Column(name = "password")
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", mobileno=" + mobileno + ", password=" + password
				+ "]";
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	 
	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	 
}
