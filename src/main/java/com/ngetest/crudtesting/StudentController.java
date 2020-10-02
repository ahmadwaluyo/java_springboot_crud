package com.ngetest.crudtesting;

import com.ngetest.crudtesting.db.Student;
import com.ngetest.crudtesting.db.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StudentController {

    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) { this.studentRepository = studentRepository; }

    @GetMapping
    public ResponseEntity<String> Hello() { return new ResponseEntity<>("Selamat datang di latihan RESTFULL API spring-boot", HttpStatus.OK);}

    @GetMapping("students")
    public ResponseEntity<?> getStudents() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("students/insert")
    public ResponseEntity<?> postStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("students/{id}")
    public ResponseEntity<?> putStudentById(@RequestBody Student student, @PathVariable Long id) {
        student.setId(id);
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
