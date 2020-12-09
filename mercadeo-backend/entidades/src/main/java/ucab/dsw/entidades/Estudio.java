package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "estudio" )
public class Estudio extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "tipoDeInstrumento" )
    private String _tipoDeInstrumento;

    @Column( name = "estatus" )
    private String _estatus;

    @Column( name = "fechaInicio" )
    private Date _fechaInicio;

    @Column( name = "fechaFin" )
    private Date _fechaFin;

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_tipoDeInstrumento() {
        return _tipoDeInstrumento;
    }

    public void set_tipoDeInstrumento(String _tipoDeInstrumento) {
        this._tipoDeInstrumento = _tipoDeInstrumento;
    }

    public String get_estatus() {
        return _estatus;
    }

    public void set_estatus(String _estatus) {
        this._estatus = _estatus;
    }

    public Date get_fechaInicio() {
        return _fechaInicio;
    }

    public void set_fechaInicio(Date _fechaInicio) {
        this._fechaInicio = _fechaInicio;
    }

    public Date get_fechaFin() {
        return _fechaFin;
    }

    public void set_fechaFin(Date _fechaFin) {
        this._fechaFin = _fechaFin;
    }

    public Solicitud_estudio get_solicitudEstudio() {
        return _solicitudEstudio;
    }

    public void set_solicitudEstudio(Solicitud_estudio _solicitudEstudio) {
        this._solicitudEstudio = _solicitudEstudio;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    @ManyToOne
    @JoinColumn( name = "fk_solicitudEstudio" )
    private Solicitud_estudio _solicitudEstudio;

    @ManyToOne
    @JoinColumn( name = "fk_usuario" )
    private Usuario _usuario;

    @Override
    public String get_estado() {
        return _estado;
    }

    @Override
    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    public Estudio( long id )
    {
        super( id );
    }

    public Estudio( )
    {

    }
}
