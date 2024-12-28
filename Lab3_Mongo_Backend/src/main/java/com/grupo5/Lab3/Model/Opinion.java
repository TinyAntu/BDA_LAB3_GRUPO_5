package com.grupo5.Lab3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data


public class Opinion {
    @Id
    private Integer id_opinion;

    private String comentario;

    private Integer puntuacion;

    private Integer id_usuario;
}
