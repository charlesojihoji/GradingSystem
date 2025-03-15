package edu.uoengland.GradingSystem.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
}
