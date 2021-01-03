package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "estudio" )
@NamedQueries({
        @NamedQuery(name = "obtenerRecomendaciones", query = "SELECT es FROM Estudio es, Solicitud_estudio sent, Solicitud_estudio scom WHERE sent._id = :id_solicitud AND es._solicitudEstudio._id = scom._id AND sent._conCuantasPersonasVive = scom._conCuantasPersonasVive AND sent._edadMaximaPoblacion = scom._edadMaximaPoblacion AND sent._edadMinimaPoblacion = scom._edadMinimaPoblacion AND sent._nivelEconomico = scom._nivelEconomico AND sent._ocupacion = scom._ocupacion AND sent._producto._subcategoria = scom._producto._subcategoria AND sent._generoPoblacional = scom._generoPoblacional AND sent._disponibilidadEnLinea = scom._disponibilidadEnLinea"),
        @NamedQuery(name = "getEstudiosUsuario", query = "SELECT es FROM Estudio es WHERE es._usuario._id = :id_usuario"),
        @NamedQuery(name = "getEstudiosCliente", query = "SELECT es FROM Estudio es, Solicitud_estudio  se WHERE se._usuario._id = :id_usuario AND es._solicitudEstudio._id = se._id"),
        @NamedQuery(name = "contarParticipantes", query = "SELECT count(distinct re._usuario) FROM Respuesta re, Pregunta_estudio pe, Estudio es WHERE re._preguntaEstudio._id = pe._id AND pe._estudio._id = :id_estudio"),
        @NamedQuery(name = "getEstudiosRespondidosEncuestado", query = "SELECT distinct (es) FROM Estudio es, Respuesta re, Pregunta_estudio pe WHERE re._usuario._id = :id_usuario AND re._preguntaEstudio._id = pe._id AND pe._estudio._id = es._id")
})
public class Estudio extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

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
