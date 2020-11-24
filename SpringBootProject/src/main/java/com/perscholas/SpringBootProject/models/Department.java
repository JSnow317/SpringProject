package com.perscholas.SpringBootProject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dept_Id;
	private String dept_name;
//    @OneToOne(mappedBy = "department")
//    private Employee employee;

    
    public Department() {
    	
    }
    
	public Department(String dept_name) {
		// TODO Auto-generated constructor stub
		this.dept_name = dept_name;
//		this.employee = employee;
	}
    
	public Department(int dept_Id, String dept_name) {
		// TODO Auto-generated constructor stub
		this.dept_Id = dept_Id;
		this.dept_name = dept_name;
//		this.employee = employee;
	}

	public int getDept_Id() {
		return dept_Id;
	}

	public void setDept_Id(int dept_Id) {
		this.dept_Id = dept_Id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
	
	

}