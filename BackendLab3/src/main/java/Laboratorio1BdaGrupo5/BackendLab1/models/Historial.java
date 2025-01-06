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
    private List<Orden> ordenes;
    private List<Interaccion> interacciones;

    public Historial(Integer idUsuario) {
        this.idUsuario = idUsuario;
        this.productos = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.interacciones = new ArrayList<>();
    }
}