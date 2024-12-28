package com.grupo5.Lab3.Model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document

public class DetalleOrden {

    @Id
    private Integer idDetalle;


    private Integer idOrden;


    private Integer idProducto;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    public DetalleOrden(Integer idOrden, Integer idProducto){
        this.idOrden = idOrden;
        this.idProducto = idProducto;
    }
}
