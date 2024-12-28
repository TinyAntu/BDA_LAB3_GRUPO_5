package com.grupo5.Lab3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Document //Documento de Mongo Db
@Data

public class Cliente {

    @Id
    private ObjectId id_cliente;

    @Field("nombre")  // Asegura que se guarde correctamente
    private String nombre;

    @Field("email")   // Asegura que se guarde correctamente
    private String email;

    @Field("telefono") // Asegura que se guarde correctamente
    private String telefono;

    @Field("password") // Asegura que se guarde correctamente
    private String password;
}
