package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "pregunta_encuesta" )
@NamedQueries({
        @NamedQuery(name = "getConOpciones", query = "SELECT pe FROM Pregunta_encuesta pe WHERE pe._tipoPregunta = 'Seleccion simple' OR pe._tipoPregunta = 'Seleccion multiple'  "),
        @NamedQuery(name = "getEnunciadoPregunta", query = "SELECT penc FROM Pregunta_encuesta penc, Pregunta_estudio pest WHERE penc._id = :fk_encuesta  ")
})
public class Pregunta_encuesta extends EntidadBase{

    @Column( name = "descripcion" )
    private String _descripcion;

    @Column( name = "tipoPregunta" )
    private String _tipoPregunta;

    @Column( name = "estado" )
    private String _estado;

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String get_tipoPregunta() {
        return _tipoPregunta;
    }

    public void set_tipoPregunta(String _tipoPregunta) {
        this._tipoPregunta = _tipoPregunta;
    }

    public Subcategoria get_subcategoria() {
        return _subcategoria;
    }

    public void set_subcategoria(Subcategoria _subcategoria) {
        this._subcategoria = _subcategoria;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Pregunta_encuesta(long id )
    {
        super( id );
    }

    public Pregunta_encuesta()
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
    @JoinColumn( name = "fk_subcategoria" )
    private Subcategoria _subcategoria;

    @ManyToOne
    @JoinColumn( name = "fk_usuario" )
    private Usuario _usuario;
}
