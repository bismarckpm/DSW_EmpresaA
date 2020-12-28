package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "solicitud_estudio" )
@NamedQueries({
        @NamedQuery(name = "solicitudesCliente", query = "SELECT se FROM Solicitud_estudio se WHERE se._usuario._id = :id_usuario ")
})
public class Solicitud_estudio extends EntidadBase{

    @Column( name = "descripcionSolicitud" )
    private String _descripcionSolicitud;

    @Column( name = "generoPoblacional" )
    private String _generoPoblacional;

    @Column( name = "fechaPeticion" )
    private Date _fechaPeticion;

    @Column( name = "edadMinimaPoblacion" )
    private String _edadMinimaPoblacion;

    @Column( name = "edadMaximaPoblacion" )
    private String _edadMaximaPoblacion;

    @Column( name = "estatus" )
    private String _estatus;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "conCuantasPersonasVive" )
    private String _conCuantasPersonasVive;

    @Column( name = "disponibilidadEnLinea" )
    private String _disponibilidadEnLinea;

    public String get_descripcionSolicitud() {
        return _descripcionSolicitud;
    }

    public void set_descripcionSolicitud(String _descripcionSolicitud) {
        this._descripcionSolicitud = _descripcionSolicitud;
    }

    public String get_generoPoblacional() {
        return _generoPoblacional;
    }

    public void set_generoPoblacional(String _generoPoblacional) {
        this._generoPoblacional = _generoPoblacional;
    }

    public Date get_fechaPeticion() {
        return _fechaPeticion;
    }

    public void set_fechaPeticion(Date _fechaPeticion) {
        this._fechaPeticion = _fechaPeticion;
    }

    public String get_edadMinimaPoblacion() {
        return _edadMinimaPoblacion;
    }

    public void set_edadMinimaPoblacion(String _edadMinimaPoblacion) {
        this._edadMinimaPoblacion = _edadMinimaPoblacion;
    }

    public String get_edadMaximaPoblacion() {
        return _edadMaximaPoblacion;
    }

    public void set_edadMaximaPoblacion(String _edadMaximaPoblacion) {
        this._edadMaximaPoblacion = _edadMaximaPoblacion;
    }

    public String get_conCuantasPersonasVive() {
        return _conCuantasPersonasVive;
    }

    public void set_conCuantasPersonasVive(String _conCuantasPersonasVive) {
        this._conCuantasPersonasVive = _conCuantasPersonasVive;
    }

    public String get_disponibilidadEnLinea() {
        return _disponibilidadEnLinea;
    }

    public void set_disponibilidadEnLinea(String _disponibilidadEnLinea) {
        this._disponibilidadEnLinea = _disponibilidadEnLinea;
    }

    public Nivel_economico get_nivelEconomico() {
        return _nivelEconomico;
    }

    public void set_nivelEconomico(Nivel_economico _nivelEconomico) {
        this._nivelEconomico = _nivelEconomico;
    }

    public Ocupacion get_ocupacion() {
        return _ocupacion;
    }

    public void set_ocupacion(Ocupacion _ocupacion) {
        this._ocupacion = _ocupacion;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Producto get_producto() {
        return _producto;
    }

    public void set_producto(Producto _producto) {
        this._producto = _producto;
    }

    public String get_estatus() {
        return _estatus;
    }

    public void set_estatus(String _estatus) {
        this._estatus = _estatus;
    }

    public Solicitud_estudio(long id )
    {
        super( id );
    }

    public Solicitud_estudio()
    {

    }

    @Override
    public String get_estado()
    {
        return _estado;
    }

    @Override
    public void set_estado( String _estado )
    {
        this._estado = _estado;
    }

    @ManyToOne
    @JoinColumn( name = "fk_nivelEconomico" )
    private Nivel_economico _nivelEconomico;

    @ManyToOne
    @JoinColumn( name = "fk_ocupacion" )
    private Ocupacion _ocupacion;

    @ManyToOne
    @JoinColumn( name = "fk_usuario" )
    private Usuario _usuario;

    @ManyToOne
    @JoinColumn( name = "fk_producto" )
    private Producto _producto;

}
