package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.*;
import Laboratorio1BdaGrupo5.BackendLab1.repository.DetalleOrdenRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.HistorialRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.OrdenRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;


import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialService {
    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    public Historial InitiateHistorial(Integer id_Cliente){
        // Verificar si ya existe un historial con el id_Cliente
        Query query = new Query();
        query.addCriteria(Criteria.where("idUsuario").is(id_Cliente));

        Historial existingHistorial = mongoTemplate.findOne(query, Historial.class);

        // Si ya existe, simplemente lo retornamos
        if (existingHistorial != null) {
            return existingHistorial;
        }

        // Si no existe, creamos uno nuevo

        Historial historial = new Historial(id_Cliente);

        // Guardar en MongoDB
        mongoTemplate.save(historial);

        return historial;
    }

    public Historial getHistorialByUsuario(Integer idUsuario) {
        return historialRepository.findByIdUsuario(idUsuario);
    }

    public Historial aggregateOrden_Producto(Integer idUsuario, Integer id_Orden) {
        //Para conseguir todo necesitamos la orden
        Orden orden = ordenRepository.getOrdenById(id_Orden);

        //Sabiendo la orden podemos acceder a todos los detalle orden
        List<DetalleOrden> detallesOrden = detalleOrdenRepository.getDetalleOrdenByOrdenId(id_Orden);
        List<Producto> productos = new ArrayList<>();

        //Recorremos las detalles orden conseguidas
        for(DetalleOrden detalle : detallesOrden){
            //Producto perteneciente a ese detalle orden
            Producto found = productoRepository.getProductoById(detalle.getIdProducto());
            if (found != null) {
                productos.add(found);
            }
        }

        Historial historial = getHistorialByUsuario(idUsuario);

        boolean ordenYaAgregada = false;
        for (Orden o : historial.getOrdenes()) {
            if (o.getId_orden() != null && o.getId_orden().intValue() == id_Orden) {
                ordenYaAgregada = true;
                break;
            }
        }

        // Agregar la orden al historial
        if (!ordenYaAgregada) {
            // Agregar la orden al historial
            historial.getOrdenes().add(orden);
        }

        // Agregar los productos al historial
        // Verificar y agregar solo los productos que no estén ya en el historial
        for (Producto producto : productos) {
            boolean productoYaAgregado = false;
            for (Producto p : historial.getProductos()) {
                if (p.getIdProducto() != null && p.getIdProducto().intValue() == producto.getIdProducto()) {
                    productoYaAgregado = true;
                    break;
                }
            }

            if (!productoYaAgregado) {
                historial.getProductos().add(producto);
            }
        }

        //Actulizar el registro dentro de mongoDB
        historialRepository.save(historial);

        return historial;

    }

    public Historial addInteraccion(Integer id_cliente, Interaccion interaccion){
        if(getHistorialByUsuario(id_cliente) == null){
            InitiateHistorial(id_cliente);
        }
        try{
            Historial historial = getHistorialByUsuario(id_cliente);
            historial.getInteracciones().add(interaccion);
            return historialRepository.save(historial);
        } catch (Exception e){
            throw new RuntimeException("Error al agregar la interaccion");
        }

    }

}
