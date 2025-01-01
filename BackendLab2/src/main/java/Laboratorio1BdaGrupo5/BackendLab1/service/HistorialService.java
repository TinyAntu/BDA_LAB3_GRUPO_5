package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.DetalleOrden;
import Laboratorio1BdaGrupo5.BackendLab1.models.Historial;
import Laboratorio1BdaGrupo5.BackendLab1.models.Orden;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.DetalleOrdenRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.HistorialRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.OrdenRepository;
import Laboratorio1BdaGrupo5.BackendLab1.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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

        // Agregar la orden al historial
        historial.getOrdenes().add(orden);

        // Agregar los productos al historial
        historial.getProductos().addAll(productos);

        //Actulizar el registro dentro de mongoDB
        historialRepository.save(historial);

        return historial;

    }

}
