package com.example.testCrud1.controllers;

import com.example.testCrud1.entities.Student;
import com.example.testCrud1.repositories.StudentRepository;
import com.example.testCrud1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    //nuovo studente
    @PostMapping("/create")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student newStudent = studentRepository.save(student);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);

    }
    //lista di tutti gli studenti
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    //per prendere uno Student specifico passando primary key come path variable

    @GetMapping("/getSingle/{id}")
    public ResponseEntity<Student> getSingle(@PathVariable Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return ResponseEntity.of(optionalStudent);
}
//aggiornare uno Studente
    @PostMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.get().setName(student.getName());
        optionalStudent.get().setSurname(student.getSurname());
        return ResponseEntity.of(optionalStudent);
    }
    //aggiornare il valore isWorking value
    @PutMapping("/{id}/working")
    public ResponseEntity<Student> setStudentActivation(@PathVariable Long id, @RequestParam boolean working){
        studentService.setUserActivationStatus(id,working);
        return ResponseEntity.ok().build();
    }
//cancellare Studente
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
