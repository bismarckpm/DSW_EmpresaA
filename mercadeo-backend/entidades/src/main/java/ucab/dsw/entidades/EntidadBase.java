package ucab.dsw.entidades;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class EntidadBase implements Serializable
{

    @Id
    @Column( name = "codigo" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;


    @Column( name = "estado" )
    private String _estado;

    //endregion

    //region Method


    public EntidadBase( long id )
    {
        _id = id;
    }

    public EntidadBase()
    {
    }


    public long get_id()
    {
        return _id;
    }

    public String get_estado()
    {
        return _estado;
    }

    public void set_estado( String _estado )
    {
        this._estado = _estado;
    }


}
