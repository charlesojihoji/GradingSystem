package edu.uoengland.GradingSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uoengland.GradingSystem.dto.GradesDTO;
import edu.uoengland.GradingSystem.entity.Grade;
import edu.uoengland.GradingSystem.repository.GradesRepository;

@Service
public class GradesServiceImpl implements GradesService{

	@Autowired
	private GradesRepository gradesRepository;
	
	public List<Grade> getAllGrades(){
		
		return gradesRepository.findAll();
	}

	@Override
	public Optional<Grade> getGrade(UUID gradeId) {

		Optional<Grade> theGrade = gradesRepository.findById(gradeId);
		
		return theGrade;
	}

	@Override
	public void createAGrade(GradesDTO gradesDTO) {
		
		Grade theGrade = new Grade(gradesDTO.getStudentsFullName(),
				gradesDTO.getCourse(), gradesDTO.getGrade(), gradesDTO.getGradeId());
		gradesRepository.save(theGrade);
	}

	@Override
	public String deleteGrade(UUID gradeId) {

		gradesRepository.deleteById(gradeId);
		
		return "The Grade has been successfully deleted.";
	}

	@Override
	public String updateGrade(Grade newGrade) {

		gradesRepository.save(newGrade);
		
		return "The Grade has been successfully updated.";
	}

	@Override
	public List<String> getAllGradesForACourse(String course) {

		return gradesRepository.getAllGradesForACourse(course);
	}
	
	
}
