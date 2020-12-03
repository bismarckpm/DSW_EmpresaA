package entidades;

import javax.persistence.*;

@Entity
@Table( name = "categoria" )
public class Categoria extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

    public Categoria( long codigo )
    {
        super( codigo );
    }

    public Categoria()
    {

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


}
