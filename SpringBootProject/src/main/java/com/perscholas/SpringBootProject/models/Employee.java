package com.perscholas.SpringBootProject.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emp_id;
	private String emp_firstName;
	private String emp_lastName;
	private Date start_date;
	private Date end_date;
	private double salary;
	private int manager_Id;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "dept_id", referencedColumnName = "id")
//    private Department department;
    
    public Employee() {
    	
    }
    
	public Employee(String emp_firstName, String emp_lastName, Date start_date, Date end_date, double salary, int manager_Id) {
		// TODO Auto-generated constructor stub
		this.emp_firstName = emp_firstName;
		this.emp_lastName = emp_lastName;
		this.start_date = start_date;
		this.end_date = end_date;
		this.salary = salary;
//		this.department = department;
		this.manager_Id = manager_Id;
	}
    

	public Employee(int emp_id, String emp_firstName, String emp_lastName, Date start_date, Date end_date, double salary, int manager_Id) {
		// TODO Auto-generated constructor stub
		this.emp_id = emp_id;
		this.emp_firstName = emp_firstName;
		this.emp_lastName = emp_lastName;
		this.start_date = start_date;
		this.end_date = end_date;
		this.salary = salary;
	//	this.department = department;
		this.manager_Id = manager_Id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_firstName() {
		return emp_firstName;
	}

	public void setEmp_firstName(String emp_firstName) {
		this.emp_firstName = emp_firstName;
	}

	public String getEmp_lastName() {
		return emp_lastName;
	}

	public void setEmp_lastName(String emp_lastName) {
		this.emp_lastName = emp_lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
//
//	public Department getDept_Id() {
//		return department;
//	}
//
//	public void setDept_Id(Department department) {
//		this.department = department;
//	}

	public int getManager_Id() {
		return manager_Id;
	}

	public void setManager_Id(int manager_Id) {
		this.manager_Id = manager_Id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

}
