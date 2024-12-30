package Laboratorio3BdaGrupo5.BackendLab3.repository;

import Laboratorio3BdaGrupo5.BackendLab3.models.DetalleOrden;

import java.util.List;

public interface DetalleOrdenRepository {
    // Create
    void createDetalleOrden(DetalleOrden detalleOrden, Integer idCliente);

    // Read
    DetalleOrden getDetalleOrden(Integer idDetalleOrden);

    List<DetalleOrden> getDetalleOrdenByOrdenId(Integer idOrden);

    List<DetalleOrden> getAllDetalleOrden();

    // Update
    void updateDetalleOrden(DetalleOrden detalleOrden);

    // Delete
    void deleteDetalleOrden(Integer idDetalleOrden);


}
