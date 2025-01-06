package Laboratorio1BdaGrupo5.BackendLab1.controller;

import Laboratorio1BdaGrupo5.BackendLab1.models.PriceHistory;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/search/{id_cliente}")
    public ResponseEntity<Map<String, Object>> getAllProductos(
            @PathVariable Integer id_cliente,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "") String search) {
        try {
            List<Producto> productos = productoService.getAllProductos(id_cliente, limit, offset, search);
            long totalCount = productoService.getTotalCount(); // Obtén el total de productos
            if (!search.isEmpty()) {
                totalCount = productos.size();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("products", productos);
            response.put("totalCount", totalCount); // Total de productos para calcular páginas
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/{id_producto}/{id_cliente}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id_producto, @PathVariable Integer id_cliente) {
        try {
            Producto producto = productoService.getProductoById(id_producto, id_cliente);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createProducto(@RequestBody Producto producto) {
        try {
            productoService.createProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Producto creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el producto");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        try {
            producto.setIdProducto(id); // Asegurarse de que el ID sea consistente
            productoService.updateProducto(producto);
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar el producto");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Integer id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar el producto");
        }
    }

    @GetMapping("/getName/{id}")
    public ResponseEntity<String> getProductoName(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productoService.getProductoName(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al obtener el nombre del producto");
        }
    }

    @GetMapping("/getVariablePriceProduct")
    public ResponseEntity<Producto> getMostVariablePriceProduct() {
        try {
            Producto product = productoService.getMostVariablePriceProduct();
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping("/getPriceHistory/{id}")
    public ResponseEntity<List<PriceHistory>> getPriceHistory(@PathVariable Integer id) {
        try {
            List<PriceHistory> prices = productoService.getPriceHistory(id);
            if (prices == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(prices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping("/getByCategoria/{categoria}/{id_cliente}")
    public ResponseEntity<List<Producto>> getByCategoria(@PathVariable String categoria, @PathVariable Integer id_cliente){
        try {
            List<Producto> p = productoService.getProductosPorCategoria(id_cliente, categoria);
            if (p == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
