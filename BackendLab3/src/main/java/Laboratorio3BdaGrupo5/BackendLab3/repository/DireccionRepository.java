package Laboratorio3BdaGrupo5.BackendLab3.repository;

import Laboratorio3BdaGrupo5.BackendLab3.models.Direccion;

public interface DireccionRepository {

    Direccion getDireccionById(Integer id);

    Direccion createDireccion(Double latitud, Double longitud);

    void updateDireccion(Integer id, Double latitud, Double longitud);

    void deleteDireccion(Integer id);
}
