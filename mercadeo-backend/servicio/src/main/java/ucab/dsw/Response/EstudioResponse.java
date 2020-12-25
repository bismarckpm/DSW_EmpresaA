package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudioResponse {
    private long id;
    private String nombre;
    private String fechaInicio;
    private String fechaFinal;
    private String status;

    public EstudioResponse(long id, String nombre, String fechaInicio, String fechaFinal, String status) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.status = status;
    }
}
