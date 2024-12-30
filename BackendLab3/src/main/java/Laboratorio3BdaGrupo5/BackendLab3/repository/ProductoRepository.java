package Laboratorio3BdaGrupo5.BackendLab3.repository;

import Laboratorio3BdaGrupo5.BackendLab3.models.PriceHistory;
import Laboratorio3BdaGrupo5.BackendLab3.models.Producto;

import java.util.List;

public interface ProductoRepository {

    List<Producto> getProductos(int limit, int offset); // Para paginación
    List<Producto> getProductosSearch(int limit, int offset, String search);
    Producto getProductoById(Integer idProducto);
    public List<Producto> getProductosPorCategoria(Integer idCategoria);
    public List<Producto> getProductosPorCategoria(String name);
    void createProducto(Producto producto);
    void updateProducto(Producto producto);
    void deleteProducto(Integer idProducto);
    long getTotalCount();
    String getProductoName(Integer idProducto);
    Producto getMostVariablePriceProduct();
    List<PriceHistory> getPriceHistory(Integer productoId);
    Producto getFirstProducto();
}
