package Laboratorio3BdaGrupo5.BackendLab3.controller;

import Laboratorio3BdaGrupo5.BackendLab3.models.Report;
import Laboratorio3BdaGrupo5.BackendLab3.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping
    ResponseEntity<List<Report>> Report(){
        try {
            System.out.println("1");
            return ResponseEntity.ok(reportService.getReport());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
