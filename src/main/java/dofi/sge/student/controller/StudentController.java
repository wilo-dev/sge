package dofi.sge.student.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dofi.sge.student.entity.request.StudentRequest;
import dofi.sge.student.entity.response.StudentResponse;
import dofi.sge.student.service.StudentService;
import dofi.sge.util.entity.OutputEntity;
import dofi.sge.util.enums.MessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<OutputEntity<List<StudentResponse>>> getStatusStudent(@RequestParam boolean status) {
        OutputEntity<List<StudentResponse>> out = null;
        try {
            out = studentService.getStatusStudent(status);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<List<StudentResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

//    @GetMapping
//    public ResponseEntity<OutputEntity<List<StudentResponse>>> getCourseStudent(@RequestParam String course) {
//        OutputEntity<List<StudentResponse>> out = null;
//        try {
//            out = studentService.getCourseStudent(course);
//            return new ResponseEntity<>(out, out.getCode());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            out = new OutputEntity<List<StudentResponse>>().error();
//            return new ResponseEntity<>(out, out.getCode());
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<StudentResponse>>> getAllStudents() {
        OutputEntity<List<StudentResponse>> out = null;
        try {
            out = studentService.getAllStudents();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<List<StudentResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputEntity<StudentResponse>> getStudentById(@PathVariable Long id) {
        OutputEntity<StudentResponse> out = null;
        try {
            out = studentService.getStudentById(id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<StudentResponse>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PostMapping
    public ResponseEntity<OutputEntity<String>> createStudent(@RequestBody StudentRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = studentService.createStudent(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> deleteStudent(@PathVariable Long id) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = studentService.deleteStudent(id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputEntity<String>> updateStudent(@RequestBody StudentRequest data, @PathVariable Long id) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = studentService.updateStudent(data, id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    @PutMapping("/enabled/{id}")
    public ResponseEntity<OutputEntity<String>> updateStudentStatus(@RequestBody StudentRequest data, @PathVariable Long id) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = studentService.updateStudentEnabled(data, id);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

    //    @GetMapping
//    public ResponseEntity<OutputEntity<List<StudentResponse>>> getAllEnableStudent() {
//        OutputEntity<List<StudentResponse>> out = null;
//        try {
//            out = studentService.getAllEnableStudent();
//            return new ResponseEntity<>(out, out.getCode());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            out = new OutputEntity<List<StudentResponse>>().error();
//            return new ResponseEntity<>(out, out.getCode());
//        }
//    }

//    @GetMapping("/disabled")
//    public ResponseEntity<OutputEntity<List<StudentResponse>>> getAllDisabledStudent() {
//        OutputEntity<List<StudentResponse>> out = null;
//        try {
//            out = studentService.getAllDisabledStudent();
//            return new ResponseEntity<>(out, out.getCode());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            out = new OutputEntity<List<StudentResponse>>().error();
//            return new ResponseEntity<>(out, out.getCode());
//        }
//    }

//    @PostMapping
//    public ResponseEntity<OutputEntity<String>> createStudent(@RequestBody String jsonData) {
//        OutputEntity<String> out = new OutputEntity<>();
//
//        try {
//            // Intenta convertir el JSON en objeto StudentRequest
//            StudentRequest data = new ObjectMapper().readValue(jsonData, StudentRequest.class);
//            out = studentService.createStudent(data);
//            return new ResponseEntity<>(out, out.getCode());
//        } catch (Exception e) {
//            if (e.getCause() instanceof JsonParseException || e.getCause() instanceof JsonMappingException) {
////                 Manejo de error de sintaxis JSON incorrecta
//                out.error(MessageEnum.SYNTAX_JSON_INVALID.getCode(),
//                        new ArrayList<>(Arrays.asList(MessageEnum.SYNTAX_JSON_INVALID.getMensaje())),
//                        null);
//                return new ResponseEntity<>(out, out.getCode());
//            } else {
//                // Manejo de otros errores
//                out.error(MessageEnum.SYNTAX_JSON_INVALID.getCode(),
//                        new ArrayList<>(Arrays.asList(MessageEnum.SYNTAX_JSON_INVALID.getMensaje())),
//                        null);
//                return new ResponseEntity<>(out, out.getCode());
//            }
////        }
//    }

}




