package com.example.testCrud1;


import com.example.testCrud1.entities.Student;
import com.example.testCrud1.repositories.StudentRepository;
import com.example.testCrud1.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    void checkStudentWorking() throws Exception {
        Student student = new Student();
        student.setWorking(true);
        student.setName("Marta");
        student.setSurname("Caponnetto");

        Student studentFromDB = studentRepository.save(student);
        assertThat(studentFromDB).isNotNull();
        assertThat(studentFromDB.getId()).isNotNull();

        Student studentFromService = studentService.setUserActivationStatus(studentFromDB.getId(), true);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

    }

    @Test
    void findById() {
        Student student = new Student();
        student.setWorking(true);
        student.setName("Marta");
        student.setSurname("Caponnetto");

        Student studentFromDB = studentRepository.save(student);
        assertThat(studentFromDB).isNotNull();

        Student studentFromFind = studentRepository.findById(studentFromDB.getId()).orElse(null);
        assertThat(studentFromFind).isNotNull();
        assertThat(studentFromFind.getId()).isEqualTo(studentFromDB.getId());
        assertThat(studentFromFind.isWorking()).isEqualTo(studentFromDB.isWorking());
    }
}
