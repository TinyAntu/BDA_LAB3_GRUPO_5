package Laboratorio1BdaGrupo5.BackendLab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Data


public class Opinion {
    @Id
    private ObjectId id_opinion;
    //Quitar el comentario cuando se haya traspasado el producto a mongo (para tener la ObjetId)
    //@DBRef
    private Integer productId;

    private String comentario;

    private Integer puntuacion;

    private Integer id_usuario;

    private Date fecha;
}
