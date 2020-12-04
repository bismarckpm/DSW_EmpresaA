package ucab.dsw.dtos;

import java.util.Date;

public class Dato_usuarioDto extends DtoBase{

    private String cedula;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String sexo;

    private Date fechaNacimiento;

    private String estadoCivil;

    private String disponibilidadEnLinea;

    private String conCuantasPersonasVive;

    private Nivel_economicoDto nivelEconomicoDto;

    private Nivel_academicoDto nivelAcademicoDto;

    private LugarDto lugarDto;

    private OcupacionDto ocupacionDto;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDisponibilidadEnLinea() {
        return disponibilidadEnLinea;
    }

    public void setDisponibilidadEnLinea(String disponibilidadEnLinea) {
        this.disponibilidadEnLinea = disponibilidadEnLinea;
    }

    public String getConCuantasPersonasVive() {
        return conCuantasPersonasVive;
    }

    public void setConCuantasPersonasVive(String conCuantasPersonasVive) {
        this.conCuantasPersonasVive = conCuantasPersonasVive;
    }

    public Nivel_economicoDto getNivelEconomicoDto() {
        return nivelEconomicoDto;
    }

    public void setNivelEconomicoDto(Nivel_economicoDto nivelEconomicoDto) {
        this.nivelEconomicoDto = nivelEconomicoDto;
    }

    public Nivel_academicoDto getNivelAcademicoDto() {
        return nivelAcademicoDto;
    }

    public void setNivelAcademicoDto(Nivel_academicoDto nivelAcademicoDto) {
        this.nivelAcademicoDto = nivelAcademicoDto;
    }

    public LugarDto getLugarDto() {
        return lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    public OcupacionDto getOcupacionDto() {
        return ocupacionDto;
    }

    public void setOcupacionDto(OcupacionDto ocupacionDto) {
        this.ocupacionDto = ocupacionDto;
    }

    public Dato_usuarioDto()
    {
    }

    public Dato_usuarioDto( long id ) throws Exception
    {
        super( id );
    }
}
