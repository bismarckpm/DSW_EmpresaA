package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "respuesta" )
@NamedQueries({
        @NamedQuery(name = "getRespuestasAPreguntaSimple", query = "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._respuestaSimple = re._respuestaSimple AND re2._preguntaEstudio= :pregunta) "),
        @NamedQuery(name = "contarRespuestasSimples", query = "SELECT count(re) FROM Respuesta re WHERE re._respuestaSimple= :respuesta AND re._preguntaEstudio._id = :pestudio"),
        @NamedQuery(name = "getRespuestasAPreguntaMultiple", query = "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._respuestaMultiple = re._respuestaMultiple AND re2._preguntaEstudio= :pregunta) "),
        @NamedQuery(name = "contarRespuestasMultiples", query = "SELECT count(re) FROM Respuesta re WHERE re._respuestaMultiple= :respuesta AND re._preguntaEstudio._id = :pestudio"),
        @NamedQuery(name = "getRespuestasAPreguntaVF", query = "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._verdaderoFalso = re._verdaderoFalso AND re2._preguntaEstudio= :pregunta) "),
        @NamedQuery(name = "contarRespuestasVF", query = "SELECT count(re) FROM Respuesta re WHERE re._verdaderoFalso= :respuesta AND re._preguntaEstudio._id = :pestudio"),
        @NamedQuery(name = "getRespuestasAPreguntaAbierta", query = "SELECT re FROM Respuesta re WHERE re._id IN (Select max(re2._id) from Respuesta re2 WHERE re2._respuestaAbierta = re._respuestaAbierta AND re2._preguntaEstudio= :pregunta) "),
})
public class Respuesta extends EntidadBase{

    @Column( name = "pregunta" )
    private String _pregunta;

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

    @Column( name = "verdaderoFalso" )
    private String _verdaderoFalso;

    public Respuesta( long id )
    {
        super( id );
    }

    public Respuesta()
    {

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

    public String get_pregunta() {
        return _pregunta;
    }

    public void set_pregunta(String _pregunta) {
        this._pregunta = _pregunta;
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
