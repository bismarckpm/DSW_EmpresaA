package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "pregunta_estudio" )
public class Pregunta_estudio extends EntidadBase{

    @Column( name = "pregunta" )
    private String _pregunta;

    @Column( name = "estado" )
    private String _estado;

    public Pregunta_estudio( long id )
    {
        super( id );
    }

    public Pregunta_estudio()
    {

    }

    public Estudio get_estudio() {
        return _estudio;
    }

    public void set_estudio(Estudio _estudio) {
        this._estudio = _estudio;
    }

    public String get_pregunta() {
        return _pregunta;
    }

    public void set_pregunta(String _pregunta) {
        this._pregunta = _pregunta;
    }

    public Pregunta_encuesta get_preguntaEncuesta() {
        return _preguntaEncuesta;
    }

    public void set_preguntaEncuesta(Pregunta_encuesta _preguntaEncuesta) {
        this._preguntaEncuesta = _preguntaEncuesta;
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
    @JoinColumn( name = "fk_estudio" )
    private Estudio _estudio;

    @ManyToOne
    @JoinColumn( name = "fk_preguntaEncuesta" )
    private Pregunta_encuesta _preguntaEncuesta;
}
