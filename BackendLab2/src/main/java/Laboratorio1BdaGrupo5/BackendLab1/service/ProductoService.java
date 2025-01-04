package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.*;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepositoryImp productoRepository;

    @Autowired CategoriaService categoriaService;
    @Autowired
    HistorialService historialService;


    public List<Producto> getAllProductos(Integer id_cliente ,int limit, int offset, String search) {
        try {
            if (search.isEmpty()) {
                return productoRepository.getProductos(limit, offset);
            } else {
                Interaccion interaccion = new Interaccion("Busqueda", "Contenido: " + search);
                historialService.addInteraccion(id_cliente, interaccion);
                return productoRepository.getProductosSearch(limit, offset, search);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de productos", e);
        }
    }

    public Producto getProductoById(Integer idProducto, Integer id_cliente) {
        try {
            Producto producto = productoRepository.getProductoById(idProducto);
            if(id_cliente != null){ // La id = null es para consultas del sistema
                Interaccion interaccion = new Interaccion("Visita del producto", "Producto:" + producto.toString());
                historialService.addInteraccion(id_cliente, interaccion);
            }
            if (producto != null) {
                return producto;
            } else {
                throw new RuntimeException("Producto no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto", e);
        }
    }

    public List<Producto> getProductosPorCategoria(Integer idCategoria) {
        try {
            return productoRepository.getProductosPorCategoria(idCategoria);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de productos por categoría", e);
        }
    }

    public List<Producto> getProductosPorCategoria(Integer id_cliente, String nombre){
        try {
            Interaccion interaccion = new Interaccion("Filtro por categoría", "Categoría: " + nombre);
            historialService.addInteraccion(id_cliente, interaccion);
            return productoRepository.getProductosPorCategoria(nombre);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener productos por categoria", e);
        }
    }

    public void createProducto(Producto producto) {
        try {
            productoRepository.createProducto(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el producto", e);
        }
    }

    public void updateProducto(Producto producto) {
        try {
            getProductoById(producto.getIdProducto(), null);
            productoRepository.updateProducto(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    public void deleteProducto(Integer idProducto) {
        try {
            getProductoById(idProducto, null);
            productoRepository.deleteProducto(idProducto);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }

    public long getTotalCount() {
        try {
            return productoRepository.getTotalCount();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el total de productos", e);
        }
    }

    public String getProductoName(Integer id) {
        try {
            return productoRepository.getProductoName(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el nombre del producto", e);
        }
    }

    public Producto getMostVariablePriceProduct() {
        try {
            Producto p = productoRepository.getMostVariablePriceProduct();
            if (p == null) {
                return productoRepository.getFirstProducto();
            }
            return productoRepository.getMostVariablePriceProduct();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el producto con precio más variable", e);
        }
    }

    public List<PriceHistory> getPriceHistory(Integer productId) {
        try {
            return productoRepository.getPriceHistory(productId);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el historial del producto", e);
        }
    }
}
