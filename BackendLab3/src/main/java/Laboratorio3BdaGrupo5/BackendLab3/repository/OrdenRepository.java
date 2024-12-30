package Laboratorio3BdaGrupo5.BackendLab3.repository;


import Laboratorio3BdaGrupo5.BackendLab3.models.Orden;

import java.util.List;

public interface OrdenRepository {
    List<Orden> getOrdenes(int limit, int offset);
    Orden getOrdenById(Integer id);
    List<Orden> getOrdenesById(int limit, int offset, int idCliente);
    Orden createOrden(Orden orden);
    void updateOrden(Orden orden);
    void deleteOrden(Integer idOrden);
    List<Orden> filtrarOrdenesEnviadasDentro10km(int id_almacen);

    List<Orden> getFueraDe100km(String nombreAlmacenPrincipal);
}
