package Laboratorio3BdaGrupo5.BackendLab3.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacen")
    private Integer id_almacen;

    private String nombre;
    @Column(name = "id_direccion")
    private Integer id_direccion;
    private Integer capacidad;
    private String estado;


}
