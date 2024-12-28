package com.grupo5.Lab3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class Producto {


    @Id
    private Integer idProducto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    private String estado;

    private Integer idCategoria;

}