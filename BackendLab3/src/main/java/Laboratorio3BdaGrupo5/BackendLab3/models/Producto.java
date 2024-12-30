package Laboratorio3BdaGrupo5.BackendLab3.models;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"producto\"")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"id_producto\"")
    private Integer idProducto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    private String estado;

    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Integer idCategoria;

}