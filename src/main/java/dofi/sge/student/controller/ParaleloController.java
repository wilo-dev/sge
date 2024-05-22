package dofi.sge.student.controller;

import dofi.sge.student.entity.request.CourseRequest;
import dofi.sge.student.entity.request.ParaleloRequest;
import dofi.sge.student.entity.response.CourseResponse;
import dofi.sge.student.entity.response.ParaleloResponse;
import dofi.sge.student.service.CourseService;
import dofi.sge.student.service.ParaleloService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/paralelo")
public class ParaleloController {
    @Autowired
    private ParaleloService paraleloService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<ParaleloResponse>>> getAllParalelo() {
        OutputEntity<List<ParaleloResponse>> out = null;
        try {
            out = paraleloService.getAllParalelo();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<ParaleloResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createParalelo(@RequestBody ParaleloRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = paraleloService.createParalelo(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
