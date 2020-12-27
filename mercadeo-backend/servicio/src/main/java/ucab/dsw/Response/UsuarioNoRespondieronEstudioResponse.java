package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioNoRespondieronEstudioResponse {
    private int id;
    private String nombre;
    private String solicitud;
    private String disponibilidad;
    private String estatus;

    public UsuarioNoRespondieronEstudioResponse(int id, String nombre, String solicitud,String estatus, String disponibilidad) {
        this.id = id;
        this.nombre = nombre;
        this.solicitud = solicitud;
        this.estatus = estatus;
        this.disponibilidad = disponibilidad;
    }
}
