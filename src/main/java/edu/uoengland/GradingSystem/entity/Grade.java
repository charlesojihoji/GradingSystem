package edu.uoengland.GradingSystem.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="grades")
public class Grade {

	@Column(name="studentsFullName", nullable=false)
	private String studentsFullName;
	
	@Column(name="courseName", nullable=false)
	private String courseName;
	
	@Column(name="grade")
	private String grade;
	
	@Id
	@Column(name="gradeId", unique=true)
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID gradeId;

	public Grade() {
		super();
	}

	public Grade(String studentsFullName, String courseName, String grade, UUID gradeId) {
		super();
		this.studentsFullName = studentsFullName;
		this.courseName = courseName;
		this.grade = grade;
		this.gradeId = gradeId;
	}

	public String getStudentsFullName() {
		return studentsFullName;
	}

	public void setStudentsFullName(String studentsFullName) {
		this.studentsFullName = studentsFullName;
	}


	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public UUID getGradeId() {
		return gradeId;
	}

	public void setGradeId(UUID gradeId) {
		this.gradeId = gradeId;
	}

}
