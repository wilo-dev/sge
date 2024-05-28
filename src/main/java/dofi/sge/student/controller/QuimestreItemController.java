package dofi.sge.student.controller;

import dofi.sge.student.entity.request.QuimestreItemRequest;
import dofi.sge.student.entity.request.QuimestreRequest;
import dofi.sge.student.entity.response.QuimestreItemResponse;
import dofi.sge.student.entity.response.QuimestreResponse;
import dofi.sge.student.service.QuimestreItemService;
import dofi.sge.student.service.QuimestreService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item_quimestre")
public class QuimestreItemController {
    @Autowired
    private QuimestreItemService quimestreItemService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<QuimestreItemResponse>>> getAllItemQuimestres() {
        OutputEntity<List<QuimestreItemResponse>> out = new OutputEntity<>();
        try {
            out = quimestreItemService.getAllItemQuimestres();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<QuimestreItemResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createItemQuimestre(@RequestBody QuimestreItemRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = quimestreItemService.createItemQuimestre(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
