package ucab.dsw.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "dato_usuario" )
public class Dato_usuario extends EntidadBase{

    @Column( name = "cedula" )
    private String _cedula;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "primerNombre" )
    private String _primerNombre;

    @Column( name = "segundoNombre" )
    private String _segundoNombre;

    @Column( name = "primerApellido" )
    private String _primerApellido;

    @Column( name = "segundoApellido" )
    private String _segundoApellido;

    @Column( name = "sexo" )
    private String _sexo;

    @Column( name = "fechaNacimiento" )
    private Date _fechaNacimiento;

    @Column( name = "estadoCivil" )
    private String _estadoCivil;

    @Column( name = "disponibilidadEnLinea" )
    private String _disponibilidadEnLinea;

    @Column( name = "conCuantasPersonasVive" )
    private String _conCuantasPersonasVive;

    public String get_cedula() {
        return _cedula;
    }

    public void set_cedula(String _cedula) {
        this._cedula = _cedula;
    }

    @Override
    public String get_estado() {
        return _estado;
    }

    @Override
    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    public String get_primerNombre() {
        return _primerNombre;
    }

    public void set_primerNombre(String _primerNombre) {
        this._primerNombre = _primerNombre;
    }

    public String get_segundoNombre() {
        return _segundoNombre;
    }

    public void set_segundoNombre(String _segundoNombre) {
        this._segundoNombre = _segundoNombre;
    }

    public String get_primerApellido() {
        return _primerApellido;
    }

    public void set_primerApellido(String _primerApellido) {
        this._primerApellido = _primerApellido;
    }

    public String get_segundoApellido() {
        return _segundoApellido;
    }

    public void set_segundoApellido(String _segundoApellido) {
        this._segundoApellido = _segundoApellido;
    }

    public String get_sexo() {
        return _sexo;
    }

    public void set_sexo(String _sexo) {
        this._sexo = _sexo;
    }

    public Date get_fechaNacimiento() {
        return _fechaNacimiento;
    }

    public void set_fechaNacimiento(Date _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public String get_estadoCivil() {
        return _estadoCivil;
    }

    public void set_estadoCivil(String _estadoCivil) {
        this._estadoCivil = _estadoCivil;
    }

    public String get_disponibilidadEnLinea() {
        return _disponibilidadEnLinea;
    }

    public void set_disponibilidadEnLinea(String _disponibilidadEnLinea) {
        this._disponibilidadEnLinea = _disponibilidadEnLinea;
    }

    public String get_conCuantasPersonasVive() {
        return _conCuantasPersonasVive;
    }

    public void set_conCuantasPersonasVive(String _conCuantasPersonasVive) {
        this._conCuantasPersonasVive = _conCuantasPersonasVive;
    }

    public Nivel_economico get_nivelEconomico() {
        return _nivelEconomico;
    }

    public void set_nivelEconomico(Nivel_economico _nivelEconomico) {
        this._nivelEconomico = _nivelEconomico;
    }

    public Lugar get_lugar() {
        return _lugar;
    }

    public void set_lugar(Lugar _lugar) {
        this._lugar = _lugar;
    }

    public Nivel_academico get_nivelAcademico() {
        return _nivelAcademico;
    }

    public void set_nivelAcademico(Nivel_academico _nivelAcademico) {
        this._nivelAcademico = _nivelAcademico;
    }

    public Ocupacion get_ocupacion() {
        return _ocupacion;
    }

    public void set_ocupacion(Ocupacion _ocupacion) {
        this._ocupacion = _ocupacion;
    }

    @ManyToOne
    @JoinColumn( name = "fk_nivelEconomico" )
    private Nivel_economico _nivelEconomico;

    @ManyToOne
    @JoinColumn( name = "fk_lugar" )
    private Lugar _lugar;

    @ManyToOne
    @JoinColumn( name = "fk_nivelAcademico" )
    private Nivel_academico _nivelAcademico;

    @ManyToOne
    @JoinColumn( name = "fk_ocupacion" )
    private Ocupacion _ocupacion;

    public Dato_usuario( long id )
    {
        super( id );
    }

    public Dato_usuario( )
    {

    }
}
