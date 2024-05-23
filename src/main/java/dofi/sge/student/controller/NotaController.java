package dofi.sge.student.controller;

import dofi.sge.student.entity.request.CourseRequest;
import dofi.sge.student.entity.request.NotasRequest;
import dofi.sge.student.entity.response.CourseResponse;
import dofi.sge.student.entity.response.NotasResponse;
import dofi.sge.student.service.CourseService;
import dofi.sge.student.service.NotaService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<NotasResponse>>> getNotas() {
        OutputEntity<List<NotasResponse>> out = null;
        try {
            out = notaService.getNotas();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<NotasResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createNota(@RequestBody NotasRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = notaService.createNota(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
