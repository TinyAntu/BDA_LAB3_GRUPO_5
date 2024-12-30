package Laboratorio3BdaGrupo5.BackendLab3.repository;

import Laboratorio3BdaGrupo5.BackendLab3.models.Categoria;

import java.util.List;

public interface CategoriaRepository {
    Categoria getCategoriaById(Integer idCategoria);

    void createCategoria(Categoria categoria);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(Integer id_categoria);

    List<Categoria> getAllCategorias();

    List<Categoria> searchCategoria(String name);
}
