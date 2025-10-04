package edu.uoengland.GradingSystem.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uoengland.GradingSystem.dto.GradesDTO;
import edu.uoengland.GradingSystem.entity.Grade;
import edu.uoengland.GradingSystem.service.GradesService;

@RequestMapping("/grades")
@RestController
@CrossOrigin("*")
public class GradesController {

	@Autowired
	private GradesService gradesService;
	
	@PostMapping
	public String createAGrade(@RequestBody GradesDTO gradesDTO) {
		
		try {
			gradesService.createAGrade(gradesDTO);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "A Grade has been successfully created.";
	}
	
	@GetMapping
	public List<Grade> getAllGrades(){
		
		List<Grade> gradeResults = null;
		
		try {
			gradeResults = gradesService.getAllGrades();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return gradeResults;
	}
	
	@GetMapping("/{gradeId}")
	public Optional<Grade> getGrade(@PathVariable UUID gradeId) {
		
		Optional<Grade> theResult = null;
		
		try {
			theResult = gradesService.getGrade(gradeId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return theResult;
	}
	
	@DeleteMapping("/{gradeId}")
	public String deleteGrade(@PathVariable UUID gradeId) {
		
		try {
			gradesService.deleteGrade(gradeId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "The Grade has been successfully deleted.";
	}
	
	@PutMapping("/{gradeId}")
	public String updateGrade(@RequestBody GradesDTO gradesDTO, @PathVariable UUID gradeId) {
		
		try {
			Grade newGrade = new Grade(gradesDTO.getStudentsFullName(), gradesDTO.getCourse(), gradesDTO.getGrade(), gradeId);
			gradesService.updateGrade(newGrade);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "The Grade has been successfully updated.";
	}
	
	@GetMapping("/studentsGrades/{course}")
	public List<String> getStudentGradesforCourse(@PathVariable String course) {
		
		try {
			List<String> listOfCourses = gradesService.getAllGradesForACourse(course);
			return listOfCourses;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PutMapping("/updateGrade/{gradeId}/student/{studentName}")
	public String facultyUpdatesAStudentsGrade(@RequestBody GradesDTO gradesDTO, @PathVariable UUID gradeId, @PathVariable String studentName) {
		
		return gradesService.facultyUpdatesAStudentsGrade(gradesDTO, gradeId, studentName);
	}
	
	/*The Pseudocode or algorithm
	Go through the Grades' table in the database
	Select all the rows with the said-course
	Sort the grades associated with the said-course
	Return that student name(s) with the same best grade
	*/
	
	@GetMapping("/bestStudentForACourse/{courseName}")
	public List<String> bestStudentsForACourse(@PathVariable String courseName){
		
		return gradesService.getBestStudentsForACourse(courseName);
	}
	
	/*The Pseudocode or algorithm
	Go through the Grades' table in the database
	Select all the rows with the said-course
	Sort the grades associated with the said-course
	Return the student name(s) with the same worst grade
	*/
	
	@GetMapping("/worstStudentForACourse/{courseName}")
	public List<String> worstStudentsForACourse(@PathVariable String courseName){
		
		return gradesService.getWorstStudentsForACourse(courseName);
	}
	
	/*The Pseudocode or algorithm
	Go through the Grades' table in the database
	Select all the rows with the said-student
	Get the numeric average of all of the students' grades
	Return the student's equivalent grade
	*/
	
	@GetMapping("/averageGradeOfAStudent/{studentsFullName}")
	public String averageGradeOfAStudent(@PathVariable String studentsFullName){
		
		return gradesService.getTheAverageGradeOfAStudent(studentsFullName);
	}
}
