package entidades;

import javax.persistence.*;

@Entity
@Table( name = "respuesta_pregunta" )
public class Respuesta_pregunta extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

    public Respuesta_pregunta( long codigo )
    {
        super( codigo );
    }

    public Respuesta_pregunta()
    {

    }

    public Pregunta_encuesta get_preguntaEncuesta() {
        return _preguntaEncuesta;
    }

    public void set_preguntaEncuesta(Pregunta_encuesta _preguntaEncuesta) {
        this._preguntaEncuesta = _preguntaEncuesta;
    }

    public String get_nombre()
    {
        return _nombre;
    }

    public void set_nombre( String _nombre )
    {
        this._nombre = _nombre;
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
    @JoinColumn( name = "fk_preguntaEncuesta" )
    private Pregunta_encuesta _preguntaEncuesta;
}
