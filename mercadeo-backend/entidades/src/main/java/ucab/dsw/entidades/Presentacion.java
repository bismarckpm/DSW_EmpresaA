package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "presentacion" )
public class Presentacion extends EntidadBase{

    @Column( name = "titulo" )
    private String _titulo;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "caracteristicas" )
    private String _caracteristicas;

    public Presentacion( long id )
    {
        super( id );
    }

    public Presentacion()
    {

    }

    public String get_titulo() {
        return _titulo;
    }

    public void set_titulo(String _titulo) {
        this._titulo = _titulo;
    }

    public String get_caracteristicas() {
        return _caracteristicas;
    }

    public void set_caracteristicas(String _caracteristicas) {
        this._caracteristicas = _caracteristicas;
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

}
