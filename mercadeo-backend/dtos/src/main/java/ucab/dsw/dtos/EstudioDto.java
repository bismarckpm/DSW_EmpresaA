package ucab.dsw.dtos;

import java.util.Date;

public class EstudioDto extends DtoBase{

    private String nombre;

    private String estado;

    private String tipoInstrumento;

    private Date fechaInicio;

    private Date fechaFinal;

    private String status;

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

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public void setTipoInstrumento(String tipoInstrumento) {
        this.tipoInstrumento = tipoInstrumento;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
