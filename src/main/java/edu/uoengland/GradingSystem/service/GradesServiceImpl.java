package edu.uoengland.GradingSystem.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uoengland.GradingSystem.dto.GradesDTO;
import edu.uoengland.GradingSystem.entity.Grade;
import edu.uoengland.GradingSystem.repository.GradesRepository;

@Service
public class GradesServiceImpl implements GradesService {

	@Autowired
	private GradesRepository gradesRepository;

	public List<Grade> getAllGrades() {

		return gradesRepository.findAll();
	}

	@Override
	public Optional<Grade> getGrade(UUID gradeId) {

		Optional<Grade> theGrade = gradesRepository.findById(gradeId);

		return theGrade;
	}

	@Override
	public void createAGrade(GradesDTO gradesDTO) {

		Grade theGrade = new Grade(gradesDTO.getStudentsFullName(), gradesDTO.getCourse(), gradesDTO.getGrade(),
				gradesDTO.getGradeId());
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

	@Override
	public String facultyUpdatesAStudentsGrade(GradesDTO gradesDTO, UUID gradeId, String studentName) {

		Optional<Grade> oldGrade = gradesRepository.findById(gradeId);

		Grade newGrade = oldGrade.get();

		newGrade.setGrade(gradesDTO.getGrade());
		newGrade.setStudentsFullName(studentName);
		newGrade.setCourseName(gradesDTO.getCourse());

		gradesRepository.save(newGrade);

		return "The faculty member has successfully set the student's new grade.";
	}

	/*
	 * The Pseudocode or algorithm Go through the Grades' table in the database
	 * Select all the rows with the said-course Sort the grades associated with the
	 * said-course Return the student name(s) with the same best grade
	 */
	@Override
	public List<String> getBestStudentsForACourse(String courseName) {

		List<Grade> theListOfGrades = gradesRepository.findByCourseName(courseName);

		List<String> gradeOrder = Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-",
				"E+", "E", "E-", "F");

		Comparator<Grade> gradeComparator = Comparator.comparingInt(g -> gradeOrder.indexOf(g.getGrade()));

		Map<String, List<Grade>> byCourse = theListOfGrades.stream()
				.collect(Collectors.groupingBy(Grade::getCourseName));

		List<String> topStudents = new ArrayList<>();

		for (Map.Entry<String, List<Grade>> entry : byCourse.entrySet()) {

			List<Grade> courseGrades = entry.getValue();

			Grade top = Collections.min(courseGrades, gradeComparator);

			int topIndex = gradeOrder.indexOf(top.getGrade());

			topStudents = courseGrades.stream().filter(g -> gradeOrder.indexOf(g.getGrade()) == topIndex)
					.map(Grade::getStudentsFullName).collect(Collectors.toList());
		}

		return topStudents;
	}

	/*
	 * The Pseudocode or algorithm Go through the Grades' table in the database
	 * Select all the rows with the said-course Sort the grades associated with the
	 * said-course Return the student name(s) with the sameworest grade
	 */
	@Override
	public List<String> getWorstStudentsForACourse(String courseName) {

		List<Grade> theListOfGrades = gradesRepository.findByCourseName(courseName);

		List<String> gradeOrder = Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-",
				"E+", "E", "E-", "F");

		Comparator<Grade> gradeComparator = Comparator.comparingInt(g -> gradeOrder.indexOf(g.getGrade()));

		Map<String, List<Grade>> byCourse = theListOfGrades.stream()
				.collect(Collectors.groupingBy(Grade::getCourseName));

		List<String> bottomStudents = new ArrayList<>();

		for (Map.Entry<String, List<Grade>> entry : byCourse.entrySet()) {

			List<Grade> courseGrades = entry.getValue();

			Grade bottom = Collections.max(courseGrades, gradeComparator);

			int bottomIndex = gradeOrder.indexOf(bottom.getGrade());

			bottomStudents = courseGrades.stream().filter(g -> gradeOrder.indexOf(g.getGrade()) == bottomIndex)
					.map(Grade::getStudentsFullName).collect(Collectors.toList());
		}

		return bottomStudents;
	}

	/*
	 * The Pseudocode or algorithm Go through the Grades' table in the database
	 * Select all the rows with the said-student Get the numeric average of all of
	 * the students' grades Return the student's equivalent grade
	 */
	@Override
	public String getTheAverageGradeOfAStudent(String studentsFullName) {

		List<Grade> theListOfGrades = gradesRepository.findBystudentsFullName(studentsFullName);

		int numberOfStudentsCourses = theListOfGrades.size();
		double studentGradeCounter = 0.0;
		double finalStudentGradeDouble = 0.0;

		for (Grade studentGrade : theListOfGrades) {

			switch (studentGrade.getGrade()) {

			case "A+":
				studentGradeCounter += 16;
				break;
			case "A":
				studentGradeCounter += 15;
				break;
			case "A-":
				studentGradeCounter += 14;
				break;
			case "B+":
				studentGradeCounter += 13;
				break;
			case "B":
				studentGradeCounter += 12;
				break;
			case "B-":
				studentGradeCounter += 11;
				break;
			case "C+":
				studentGradeCounter += 10;
				break;
			case "C":
				studentGradeCounter += 9;
				break;
			case "C-":
				studentGradeCounter += 8;
				break;
			case "D+":
				studentGradeCounter += 7;
				break;
			case "D":
				studentGradeCounter += 6;
				break;
			case "D-":
				studentGradeCounter += 5;
				break;
			case "E+":
				studentGradeCounter += 4;
				break;
			case "E":
				studentGradeCounter += 3;
				break;
			case "E-":
				studentGradeCounter += 2;
				break;
			case "F":
				studentGradeCounter += 1;
				break;
			}

		}
		
		System.out.println("The studentGradeCounter is " + studentGradeCounter);
		System.out.println("The numberOfStudentsCourses is " + numberOfStudentsCourses);

		finalStudentGradeDouble = studentGradeCounter / numberOfStudentsCourses;

		System.out.println("The finalStudentGradeDouble is " + finalStudentGradeDouble);

		if ((15.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 16.0)) {

			return "A+";
		} else if ((14.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 15.4)) {

			return "A";
		} else if ((13.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 14.4)) {

			return "A-";
		} else if ((12.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 13.4)) {

			return "B+";
		} else if ((11.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 12.4)) {

			return "B";
		} else if ((10.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 11.4)) {

			return "B-";
		} else if ((9.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 10.4)) {

			return "C+";
		} else if ((8.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 9.4)) {

			return "C";
		} else if ((7.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 8.4)) {

			return "C-";
		} else if ((6.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 7.4)) {

			return "D+";
		} else if ((5.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 6.4)) {

			return "D";
		} else if ((4.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 5.4)) {

			return "D-";
		} else if ((3.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 4.4)) {

			return "E+";
		} else if ((2.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 3.4)) {

			return "E";
		} else if ((1.4 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 2.4)) {

			return "E-";
		} else if ((0.0 < finalStudentGradeDouble) && (finalStudentGradeDouble <= 1.4)) {

			return "F";
		}

		return "End of method";
	}
}
