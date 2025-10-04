package edu.uoengland.GradingSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;

import edu.uoengland.GradingSystem.dto.GradesDTO;
import edu.uoengland.GradingSystem.entity.Grade;

public interface GradesService {

	public List<Grade> getAllGrades();
	public Optional<Grade> getGrade(UUID uuid);
	public void createAGrade(GradesDTO gradesDTO);
	public String deleteGrade(UUID gradeId);
	public String updateGrade(Grade newGrade);
	public List<String> getAllGradesForACourse(String course);
	public String facultyUpdatesAStudentsGrade(GradesDTO gradesDTO, UUID gradeId, String studentName);
	public List<String> getBestStudentsForACourse(String courseName);
	public List<String> getWorstStudentsForACourse(String courseName);
	public String getTheAverageGradeOfAStudent(String studentsFullName);
}
