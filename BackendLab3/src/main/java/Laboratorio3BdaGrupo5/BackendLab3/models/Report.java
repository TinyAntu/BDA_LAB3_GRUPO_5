package Laboratorio3BdaGrupo5.BackendLab3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private Integer usuario;
    private Integer inserts;
    private Integer updates;
    private Integer deletes;
    private Integer total;
}
