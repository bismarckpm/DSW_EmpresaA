package entidades;


import javax.persistence.*;

@Entity
@Table( name = "rol_privilegio" )
public class Rol_privilegio extends EntidadBase{

    @Column( name = "estado" )
    private String _estado;

    public Rol_privilegio( long codigo )
    {
        super( codigo );
    }

    public Rol_privilegio()
    {

    }

    public Rol get_rol() {
        return _rol;
    }

    public void set_rol(Rol _rol) {
        this._rol = _rol;
    }

    public Privilegio get_privilegio() {
        return _privilegio;
    }

    public void set_privilegio(Privilegio _privilegio) {
        this._privilegio = _privilegio;
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
    @JoinColumn( name = "fk_rol" )
    private Rol _rol;

    @ManyToOne
    @JoinColumn( name = "fk_privilegio" )
    private Privilegio _privilegio;
}
