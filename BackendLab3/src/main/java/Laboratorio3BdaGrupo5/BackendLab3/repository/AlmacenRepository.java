package Laboratorio3BdaGrupo5.BackendLab3.repository;

import Laboratorio3BdaGrupo5.BackendLab3.models.Almacen;

import java.util.List;

public interface AlmacenRepository {
    List<Almacen> getAll();

    void createAlmacen(Almacen almacen);

    void updateAlmacen(Almacen almacen);

    void deleteAlmacen(Integer id);

    Almacen getAlmacenById(Integer id);

    Almacen findAlmacenMasCercano(Integer idCliente);
}
