package com.example.testCrud1.service;

import com.example.testCrud1.entities.Student;
import com.example.testCrud1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student setUserActivationStatus(Long id, boolean isWorking){
        Optional<Student> student = studentRepository.findById(id); //cerca uno studente nel repository utilizzando l'ID fornito
        if(!student.isPresent()) return null;
        student.get().setWorking(isWorking);
        return studentRepository.save(student.get());
    }
}

