package entidades;

import javax.persistence.*;

@Entity
@Table( name = "usuario" )
public class Usuario extends EntidadBase{

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "nombreUsuario" )
    private String _nombreUsuario;

    @Column( name = "correo" )
    private String _correo;

    @Column( name = "password" )
    private String _password;

    @Column( name = "codigoRecuperacion" )
    private String _codigoRecuperacion;

    public Usuario(long codigo )
    {
        super( codigo );
    }

    public Usuario()
    {

    }

    public String get_nombreUsuario() {
        return _nombreUsuario;
    }

    public void set_nombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }

    public String get_correo() {
        return _correo;
    }

    public void set_correo(String _correo) {
        this._correo = _correo;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_codigoRecuperacion() {
        return _codigoRecuperacion;
    }

    public void set_codigoRecuperacion(String _codigoRecuperacion) {
        this._codigoRecuperacion = _codigoRecuperacion;
    }

    public Rol get_rol() {
        return _rol;
    }

    public void set_rol(Rol _rol) {
        this._rol = _rol;
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
    @JoinColumn( name = "fk_rol" )
    private Rol _rol;

    @ManyToOne
    @JoinColumn( name = "fk_datoUsuario" )
    private Dato_usuario _datoUsuario;
}
