package dofi.sge.student.controller;

import dofi.sge.student.entity.request.ParcialItemRequest;
import dofi.sge.student.entity.request.ParcialRequest;
import dofi.sge.student.entity.response.ParcialItemResponse;
import dofi.sge.student.entity.response.ParcialResponse;
import dofi.sge.student.service.ParcialeItemService;
import dofi.sge.student.service.ParcialeService;
import dofi.sge.util.entity.OutputEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item_parciales")
public class ParcialesItemController {
    @Autowired
    private ParcialeItemService itemService;

    @GetMapping("/all")
    public ResponseEntity<OutputEntity<List<ParcialItemResponse>>> getAllItemParciales() {
        OutputEntity<List<ParcialItemResponse>> out = new OutputEntity<>();
        try {
            out = itemService.getAllItemParciales();
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<List<ParcialItemResponse>>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }


    @PostMapping
    public ResponseEntity<OutputEntity<String>> createItemParciales(@RequestBody ParcialItemRequest data) {
        OutputEntity<String> out = new OutputEntity<>();
        try {
            out = itemService.createItemParciales(data);
            return new ResponseEntity<>(out, out.getCode());
        } catch (Exception e) {
            out = new OutputEntity<String>().error();
            return new ResponseEntity<>(out, out.getCode());
        }
    }

}
