package dofi.sge.student.controller;

import dofi.sge.student.entity.request.CourseRequest;
import dofi.sge.student.entity.response.CourseResponse;
import dofi.sge.student.service.CourseService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<CourseResponse>>> getAllCourse() {
        OutputEntity<List<CourseResponse>> out = null;
        try {
            out = courseService.getAllCourse();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<CourseResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createCourse(@RequestBody CourseRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = courseService.createCourse(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
