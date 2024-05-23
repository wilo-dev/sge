package dofi.sge.student.controller;

import dofi.sge.student.entity.request.ParcialRequest;
import dofi.sge.student.entity.response.ParcialResponse;
import dofi.sge.student.service.ParcialesService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/parciales")
public class ParcialesController {
    @Autowired
    private ParcialesService parcialesService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<ParcialResponse>>> getAllParciales() {
        OutputEntity<List<ParcialResponse>> out = new OutputEntity<>();
        try {
            out = parcialesService.getAllParciales();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<ParcialResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createParciales(@RequestBody ParcialRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = parcialesService.createParciales(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
