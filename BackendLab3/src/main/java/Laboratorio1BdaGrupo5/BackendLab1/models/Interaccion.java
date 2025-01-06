package Laboratorio1BdaGrupo5.BackendLab1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interaccion {
    private String tipo; // Ejemplo: "Click", "Compra", "Consulta"
    private String descripcion; // Detalles de la interacción
    private Date fecha; // Fecha de la interacción

    public Interaccion(String tipo, String descripcion) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        Date fecha = Date.from(fechaHoraActual.atZone(ZoneId.systemDefault()).toInstant());
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
}
