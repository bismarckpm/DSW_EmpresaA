package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ListaEncuestasE {

    private long idEstudio;
    private String estatus;
    private String nombre;
    private Date fechaI;

    public ListaEncuestasE(long idEstudio, String estatus, String nombre, Date fechaI) {
        this.idEstudio = idEstudio;
        this.estatus = estatus;
        this.nombre = nombre;
        this.fechaI = fechaI;
    }
}
