package ucab.dsw.dtos;

import java.util.Date;

public class EstudioDto extends DtoBase{

    private String nombre;

    private String estado;

    private String tipoDeInstrumento;

    private Date fechaInicio;

    private Date fechaFinal;

    private String estatus;

    private Solicitud_estudioDto estudioSolicitudDto;

    private UsuarioDto estudioUsuarioDto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoDeInstrumento() {
        return tipoDeInstrumento;
    }

    public void setTipoDeInstrumento(String tipoDeInstrumento) {
        this.tipoDeInstrumento = tipoDeInstrumento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Solicitud_estudioDto getEstudioSolicitudDto() {
        return estudioSolicitudDto;
    }

    public void setEstudioSolicitudDto(Solicitud_estudioDto estudioSolicitudDto) {
        this.estudioSolicitudDto = estudioSolicitudDto;
    }

    public UsuarioDto getEstudioUsuarioDto() {
        return estudioUsuarioDto;
    }

    public void setEstudioUsuarioDto(UsuarioDto estudioUsuarioDto) {
        this.estudioUsuarioDto = estudioUsuarioDto;
    }

    public EstudioDto()
    {
    }

    public EstudioDto( long id ) throws Exception
    {
        super( id );
    }
}
