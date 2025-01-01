package Laboratorio1BdaGrupo5.BackendLab1.controller;

import Laboratorio1BdaGrupo5.BackendLab1.models.Historial;
import Laboratorio1BdaGrupo5.BackendLab1.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/History")
public class HistorialController {
    @Autowired
    HistorialService historialService;


    @PostMapping("/initiate/{id_Cliente}")
    public ResponseEntity<Historial> initiateHistroy(@PathVariable Integer id_Cliente) {
        Historial historial = historialService.InitiateHistorial(id_Cliente);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/history/{id_Cliente}")
    public ResponseEntity<Historial> getHistorial(@PathVariable Integer id_Cliente) {
        Historial historial = historialService.getHistorialByUsuario(id_Cliente);
        return ResponseEntity.ok(historial);
    }

    @PostMapping("/aggregate/{id_Cliente}/{id_Orden}")
    public ResponseEntity<Historial> aggregateHistorial(@PathVariable Integer id_Cliente, @PathVariable Integer id_Orden) {
        Historial historial  = historialService.aggregateOrden_Producto(id_Cliente, id_Orden);
        return ResponseEntity.ok(historial);
    }
}
