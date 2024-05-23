package dofi.sge.student.controller;

import dofi.sge.student.entity.request.QuimestreRequest;
import dofi.sge.student.entity.response.QuimestreResponse;
import dofi.sge.student.service.QuimestreService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quimestre")
public class QuimestreController {
    @Autowired
    private QuimestreService quimestreService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<QuimestreResponse>>> getAllQuimestres() {
        OutputEntity<List<QuimestreResponse>> out = new OutputEntity<>();
        try {
            out = quimestreService.getAllQuimestres();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<QuimestreResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createQuimestre(@RequestBody QuimestreRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = quimestreService.createQuimestre(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
