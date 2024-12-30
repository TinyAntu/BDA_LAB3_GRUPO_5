package Laboratorio3BdaGrupo5.BackendLab3.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceHistory {
    BigDecimal precio;
    Date fecha;
}
