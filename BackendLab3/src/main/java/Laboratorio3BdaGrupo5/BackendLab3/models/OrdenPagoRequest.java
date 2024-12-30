package Laboratorio3BdaGrupo5.BackendLab3.models;

import lombok.Data;

import java.util.List;

@Data
public class OrdenPagoRequest {
    private List<DetalleOrden> detalles;
    private Orden orden;
}

