package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "hijo" )
public class Hijo extends EntidadBase{

    @Column( name = "fechaNacimiento" )
    private Date _fechaNacimiento;

    @Column( name = "genero" )
    private String _genero;

    @Column( name = "estado" )
    private String _estado;

    public Date get_fechaNacimiento() {
        return _fechaNacimiento;
    }

    public void set_fechaNacimiento(Date _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public String get_genero() {
        return _genero;
    }

    public void set_genero(String _genero) {
        this._genero = _genero;
    }

    public Dato_usuario get_datoUsuario() {
        return _datoUsuario;
    }

    public void set_datoUsuario(Dato_usuario _datoUsuario) {
        this._datoUsuario = _datoUsuario;
    }

    @Override
    public String get_estado() {
        return _estado;
    }

    @Override
    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    @ManyToOne
    @JoinColumn( name = "fk_datoUsuario" )
    private Dato_usuario _datoUsuario;

    public Hijo( long id )
    {
        super( id );
    }

    public Hijo( )
    {

    }
}
