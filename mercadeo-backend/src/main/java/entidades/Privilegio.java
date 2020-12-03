package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "privilegio" )
public class Privilegio extends EntidadBase{

    @Column( name = "descripcion" )
    private String _descripcion;

    @Column( name = "tipoAccion" )
    private String _tipoAccion;

    @Column( name = "estado" )
    private String _estado;

    public Privilegio( long codigo )
    {
        super( codigo );
    }

    public Privilegio()
    {

    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String get_tipoAccion() {
        return _tipoAccion;
    }

    public void set_tipoAccion(String _tipoAccion) {
        this._tipoAccion = _tipoAccion;
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
