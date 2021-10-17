package com.model2.mvc.service.domain;

import java.sql.Date;


public class User {
	
	///Field
	private String userId;
	private String userName;
	private String password;
	private String role;
	private String ssn;
	private String phone;
	private String addr;
	private String email;
	private Date regDate;
	
	///Constructor
	public User(){}
	
	///setter method 
	public void setUserId(String userId) {this.userId = userId;}
	public void setUserName(String userName) {this.userName = userName;}
	public void setPassword(String password) {this.password = password;}
	public void setRole(String role) {this.role = role;}
	public void setSsn(String ssn) {this.ssn = ssn;}
	public void setPhone(String phone) {this.phone = phone;}
	public void setAddr(String addr) {this.addr = addr;}
	public void setEmail(String email) {this.email = email;}
	public void setRegDate(Date regDate) {this.regDate = regDate;}
	
	///getter method
	public String getUserId() {return userId;}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	public String getRole() {return role;}
	public String getSsn() {return ssn;}
	public String getPhone() {return phone;}
	public String getAddr() {return addr;}
	public String getEmail() {return email;}
	public Date getRegDate() {return regDate;}
	@Override
	public String toString() {
		return "UserVO : [userId] "+userId+" [userName] "+userName+" [password] "+password+" [role] "+ role
			+" [ssn] "+ssn+" [phone] "+phone+" [email] "+email+" [regDate] "+regDate;
	}
}