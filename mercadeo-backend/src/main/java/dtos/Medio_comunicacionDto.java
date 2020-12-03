package dtos;

public class Medio_comunicacionDto extends DtoBase{

    private String nombre;

    private String estado;

    private Dato_usuarioDto datoUsuarioDto;

    private Solicitud_estudioDto solicitudEstudioDto;

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

    public Dato_usuarioDto getDatoUsuarioDto() {
        return datoUsuarioDto;
    }

    public void setDatoUsuarioDto(Dato_usuarioDto datoUsuarioDto) {
        this.datoUsuarioDto = datoUsuarioDto;
    }

    public Solicitud_estudioDto getSolicitudEstudioDto() {
        return solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(Solicitud_estudioDto solicitudEstudioDto) {
        this.solicitudEstudioDto = solicitudEstudioDto;
    }

    public Medio_comunicacionDto()
    {
    }

    public Medio_comunicacionDto(long id ) throws Exception
    {
        super( id );
    }
}
