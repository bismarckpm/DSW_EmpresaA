package entidades;

import javax.persistence.*;

@Entity
@Table( name = "respuesta" )
public class Respuesta extends EntidadBase{

    @Column( name = "estatus" )
    private String _estatus;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "respuestaSimple" )
    private String _respuestaSimple;

    @Column( name = "respuestaMultiple" )
    private String _respuestaMultiple;

    @Column( name = "respuestaAbierta" )
    private String _respuestaAbierta;

    @Column( name = "escala" )
    private String _escala;

    @Column( name = "respuestacol" )
    private String _respuestacol;

    @Column( name = "verdaderoFalso" )
    private String _verdaderoFalso;

    public Respuesta( long codigo )
    {
        super( codigo );
    }

    public Respuesta()
    {

    }

    public String get_estatus() {
        return _estatus;
    }

    public void set_estatus(String _estatus) {
        this._estatus = _estatus;
    }

    public String get_respuestaSimple() {
        return _respuestaSimple;
    }

    public void set_respuestaSimple(String _respuestaSimple) {
        this._respuestaSimple = _respuestaSimple;
    }

    public String get_respuestaMultiple() {
        return _respuestaMultiple;
    }

    public void set_respuestaMultiple(String _respuestaMultiple) {
        this._respuestaMultiple = _respuestaMultiple;
    }

    public String get_respuestaAbierta() {
        return _respuestaAbierta;
    }

    public void set_respuestaAbierta(String _respuestaAbierta) {
        this._respuestaAbierta = _respuestaAbierta;
    }

    public String get_escala() {
        return _escala;
    }

    public void set_escala(String _escala) {
        this._escala = _escala;
    }

    public String get_respuestacol() {
        return _respuestacol;
    }

    public void set_respuestacol(String _respuestacol) {
        this._respuestacol = _respuestacol;
    }

    public String get_verdaderoFalso() {
        return _verdaderoFalso;
    }

    public void set_verdaderoFalso(String _verdaderoFalso) {
        this._verdaderoFalso = _verdaderoFalso;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Pregunta_estudio get_preguntaEstudio() {
        return _preguntaEstudio;
    }

    public void set_preguntaEstudio(Pregunta_estudio _preguntaEstudio) {
        this._preguntaEstudio = _preguntaEstudio;
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
    @JoinColumn( name = "fk_usuario" )
    private Usuario _usuario;

    @ManyToOne
    @JoinColumn( name = "fk_preguntaEstudio" )
    private Pregunta_estudio _preguntaEstudio;
}
