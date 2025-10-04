package edu.uoengland.GradingSystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.uoengland.GradingSystem.entity.Grade;

@Repository
public interface GradesRepository extends JpaRepository<Grade, UUID> {
	
	@Query("SELECT g.grade FROM Grade g WHERE g.courseName = :courseName")
	public List<String> getAllGradesForACourse(String courseName);

	public List<Grade> findByCourseName(String courseName);

	public List<Grade> findBystudentsFullName(String studentsFullName);
}
