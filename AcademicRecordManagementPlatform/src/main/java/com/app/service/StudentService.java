package com.app.service;




import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.app.model.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student createStudent(Student student) {

        if (studentRepository.existsByRegistrationNumber(
                student.getRegistrationNumber())) {
            throw new RuntimeException(
                    "Registration number already exists");
        }

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return studentRepository.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // READ BY ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with ID: " + id));
    }

    // UPDATE
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setRegistrationNumber(student.getRegistrationNumber());
        existingStudent.setFullName(student.getFullName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setSemester(student.getSemester());
        existingStudent.setMarks(student.getMarks());

        return studentRepository.save(existingStudent);
    }

    // DELETE
    public String deleteStudent(Long id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);

        return "Student deleted successfully";
    }

	 
	}
