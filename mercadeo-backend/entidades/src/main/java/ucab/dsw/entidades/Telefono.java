package ucab.dsw.entidades;


import javax.persistence.*;

@Entity
@Table( name = "telefono" )
public class Telefono extends EntidadBase{

    @Column( name = "numero" )
    private String _numero;

    @Column( name = "estado" )
    private String _estado;

    public Telefono( long id )
    {
        super( id );
    }

    public Telefono()
    {

    }

    public String get_numero() {
        return _numero;
    }

    public void set_numero(String _numero) {
        this._numero = _numero;
    }

    public Dato_usuario get_datoUsuario() {
        return _datoUsuario;
    }

    public void set_datoUsuario(Dato_usuario _datoUsuario) {
        this._datoUsuario = _datoUsuario;
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
