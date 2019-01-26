package com.beans;


public class Teacher {
	private int tid;
	private String tuserName;
	private String password;
	private String course;
	private Ts ts;
	
	public int getTid() {
		return tid;
	}
	public Ts getTs() {
		return ts;
	}
	public void setTs(Ts ts) {
		this.ts = ts;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTuserName() {
		return tuserName;
	}
	public void setTuserName(String tuserName) {
		this.tuserName = tuserName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	
}
