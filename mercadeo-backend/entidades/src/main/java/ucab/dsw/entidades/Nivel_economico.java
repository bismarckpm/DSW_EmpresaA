package ucab.dsw.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "nivel_economico" )
public class Nivel_economico extends EntidadBase{

    @Column( name = "nivel" )
    private String _nivel;

    @Column( name = "estado" )
    private String _estado;

    public Nivel_economico( long id )
    {
        super( id );
    }

    public Nivel_economico()
    {

    }

    public String get_nivel()
    {
        return _nivel;
    }

    public void set_nivel( String _nivel )
    {
        this._nivel = _nivel;
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
