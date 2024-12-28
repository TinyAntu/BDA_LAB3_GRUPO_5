package com.grupo5.Lab3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class Orden {

    @Id
    private Integer id_orden;

    private Date fecha_orden;

    private String estado;

    private Integer id_cliente;

    private Float total;
}
