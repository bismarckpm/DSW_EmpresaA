package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "tipo" )
public class Tipo extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "descripcion" )
    private String _descripcion;

    public Tipo( long id )
    {
        super( id );
    }

    public Tipo()
    {

    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
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
