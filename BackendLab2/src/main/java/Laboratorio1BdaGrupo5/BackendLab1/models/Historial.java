package Laboratorio1BdaGrupo5.BackendLab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "History")
@NoArgsConstructor
@AllArgsConstructor

public class Historial {
    @Id
    private ObjectId id_Historial;

    private Integer idUsuario;
    private List<Producto> productos;
    //Orden tiene los estados no tendria sentido guardarlos aparte
    private List<Orden> ordenes;

    public Historial(Integer id_Usuario) {
        this.idUsuario = id_Usuario;
        this.productos = new ArrayList<>(); // Lista vacía de productos
        this.ordenes = new ArrayList<>(); // Lista vacía de órdenes
    }

}

