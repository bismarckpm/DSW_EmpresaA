package ucab.dsw.Response;


import lombok.Getter;
import lombok.Setter;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class EstudioListResponse {
    private Long _id;

    private String _nombre;

    private String _estado;

    private String _estatus;

    private String _fechaInicio;

    private String _fechaFin;

    private Solicitud_estudio _solicitudEstudio;

    private Usuario _usuario;

    public EstudioListResponse(long _id, String _nombre, String _estado, String _estatus, String _fechaInicio, String _fechaFin, Solicitud_estudio _solicitudEstudio, Usuario _usuario){
        this._id = _id;
        this._nombre = _nombre;
        this._estado = _estado;
        this._estatus = _estatus;
        this._fechaInicio = _fechaInicio;
        this._fechaFin = _fechaFin;
        this._solicitudEstudio = _solicitudEstudio;
        this._usuario = _usuario;
    }
}
