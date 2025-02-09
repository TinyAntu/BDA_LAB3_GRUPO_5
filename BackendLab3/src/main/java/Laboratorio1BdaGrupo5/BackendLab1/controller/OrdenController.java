package Laboratorio1BdaGrupo5.BackendLab1.controller;

import Laboratorio1BdaGrupo5.BackendLab1.models.DetalleOrden;
import Laboratorio1BdaGrupo5.BackendLab1.models.Orden;
import Laboratorio1BdaGrupo5.BackendLab1.models.OrdenPagoRequest;
import Laboratorio1BdaGrupo5.BackendLab1.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<List<Orden>> getAllOrdens(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        try {
            List<Orden> ordens = ordenService.getAllOrdenes(limit, offset);
            return ResponseEntity.ok(ordens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}/{limit}/{offset}")
    public  ResponseEntity<List<Orden>> getAllOrdensById(
            @PathVariable int id,
            @PathVariable int limit,
            @PathVariable int offset){
        try {
            List<Orden> ordenes = ordenService.getAllOrdenesById(limit, offset, id);
            return ResponseEntity.ok(ordenes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Integer id) {
        try {
            Orden orden = ordenService.getOrdenById(id);
            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrden(@RequestBody Orden orden) {
        try {
            ordenService.createOrden(orden);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Orden creada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear la orden");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrden(@PathVariable Integer id, @RequestBody Orden orden) {
        try {
            orden.setId_orden(id);
            ordenService.updateOrden(orden);
            return ResponseEntity.ok("Orden actualizada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la orden");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrden(@PathVariable Integer id) {
        try {
            ordenService.deleteOrden(id);
            return ResponseEntity.ok("Orden eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar la orden");
        }
    }

    @PostMapping("/pagar")
    public ResponseEntity<Integer> pagar(@RequestBody OrdenPagoRequest request) {
        System.out.println("ID del cliente: " + request.getOrden().getId_cliente());
        try {
            Integer idOrden = ordenService.pagar(request.getDetalles(), request.getOrden());
            return ResponseEntity.ok(idOrden);
        } catch (Exception e) {
            System.err.println("Error al procesar el pago: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(-1);
        }
    }


    @GetMapping("/filtrarOrdenesPorAlmacen/{almacenId}")
    public ResponseEntity<List<Orden>> getOrdenesByRadius(@PathVariable Integer almacenId) {
        try {
            List<Orden> ordenes = ordenService.getOrdenesByRadius(almacenId);
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/fuerade100km")
    public ResponseEntity<List<Orden>> getFueraDe100km(){
        try{
            List<Orden> ordenes = ordenService.getFueraDe100km("Almacén Principal");
            return ResponseEntity.ok(ordenes);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
