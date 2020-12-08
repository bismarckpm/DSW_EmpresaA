package ucab.dsw.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DatoUsuarioResponse {

    private long id;

    private String cedula;

    private String estado;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String sexo;

    private Date fechaNacimiento;

    private String estadoCivil;

    private String disponibilidadEnLinea;

    private String conCuantasPersonasVive;

    private String nivelAcademico;

    private String nivelEconomico;

    private String lugarNombre;

    private String lugarTipo;

    private String lugarCategoriaSocieconomica;

    private String ocupacionNombre;

    public DatoUsuarioResponse(long id, String cedula, String estado, String primerNombre, String segundoNombre,
                               String primerApellido, String segundoApellido, String sexo, Date fechaNacimiento,
                               String estadoCivil, String disponibilidadEnLinea, String conCuantasPersonasVive,
                               String nivelAcademico, String nivelEconomico, String lugarNombre, String lugarTipo,
                               String lugarCategoriaSocieconomica, String ocupacionNombre) {
        this.id = id;
        this.cedula = cedula;
        this.estado = estado;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.disponibilidadEnLinea = disponibilidadEnLinea;
        this.conCuantasPersonasVive = conCuantasPersonasVive;
        this.nivelAcademico = nivelAcademico;
        this.nivelEconomico = nivelEconomico;
        this.lugarNombre = lugarNombre;
        this.lugarTipo = lugarTipo;
        this.lugarCategoriaSocieconomica = lugarCategoriaSocieconomica;
        this.ocupacionNombre = ocupacionNombre;
    }
}
