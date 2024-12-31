package Laboratorio1BdaGrupo5.BackendLab1.controller;

import Laboratorio1BdaGrupo5.BackendLab1.models.Opinion;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.service.OpinionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/review")
public class OpinionController {

    @Autowired
    OpinionService opinionService;

    @PostMapping("/create")
    public ResponseEntity<Opinion> createReview(@RequestBody Opinion review) {
        Opinion newOpinion = opinionService.saveReview(review);
        return ResponseEntity.ok(newOpinion);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Opinion>> getReviewsByProduct(@PathVariable Producto productId) {
        return ResponseEntity.ok(opinionService.getReviewsByProductId(productId));
    }

    @GetMapping("/BydateDes/{productId}")
    public ResponseEntity<List<Opinion>> getReviewsByDate(@PathVariable ObjectId productId ) {
        return ResponseEntity.ok(opinionService.getReviewsByProductWithDate(productId));
    }
}
