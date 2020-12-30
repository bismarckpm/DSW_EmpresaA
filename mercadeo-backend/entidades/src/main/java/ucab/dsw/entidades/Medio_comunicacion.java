package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "medio_comunicacion" )
public class Medio_comunicacion extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

    public Medio_comunicacion( long id )
    {
        super( id );
    }

    public Medio_comunicacion()
    {

    }

    public Dato_usuario get_datoUsuario() {
        return _datoUsuario;
    }

    public void set_datoUsuario(Dato_usuario _datoUsuario) {
        this._datoUsuario = _datoUsuario;
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
    @JoinColumn( name = "fk_datoUsuario" )
    private Dato_usuario _datoUsuario;

}
