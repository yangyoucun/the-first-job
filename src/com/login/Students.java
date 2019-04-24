package com.login;
 
public class Students {
	private Integer id;
	private String name;
	private String sex;
	private String birthday;
	private String classes;
	private String academy;
	private float math;
	private float chniese;
	
	public Students() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	
	public float getMath() {
		return math;
	}
 
	public void setMath(float math) {
		this.math = math;
	}
 
	public float getChniese() {
		return chniese;
	}
 
	public void setChniese(float chniese) {
		this.chniese = chniese;
	}
 
	@Override
	public String toString() {
		
		return id+"  "+name+"  "+sex;
	}
	
	
}
