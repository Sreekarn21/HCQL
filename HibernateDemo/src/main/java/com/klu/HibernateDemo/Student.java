package com.klu.HibernateDemo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String gender;
    private String department;
    private String program;
    private Date dateOfBirth;
    private String contactNumber;
    private double cgpa;
    private int backlogs;
    private boolean graduationStatus;

    @Version
    private int version;

    public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String gender, String department, String program, Date dateOfBirth,
			String contactNumber, double cgpa, int backlogs, boolean graduationStatus, int version) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.department = department;
		this.program = program;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.cgpa = cgpa;
		this.backlogs = backlogs;
		this.graduationStatus = graduationStatus;
		this.version = version;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public int getBacklogs() {
		return backlogs;
	}

	public void setBacklogs(int backlogs) {
		this.backlogs = backlogs;
	}

	public boolean isGraduationStatus() {
		return graduationStatus;
	}

	public void setGraduationStatus(boolean graduationStatus) {
		this.graduationStatus = graduationStatus;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", department=" + department
				+ ", program=" + program + ", dateOfBirth=" + dateOfBirth + ", contactNumber=" + contactNumber
				+ ", cgpa=" + cgpa + ", backlogs=" + backlogs + ", graduationStatus=" + graduationStatus + ", version="
				+ version + "]";
	}
    
    
}
