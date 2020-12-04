package ucab.dsw.dtos;

public class Region_estudioDto extends DtoBase{

    private String estado;

    private LugarDto lugarDto;

    private Solicitud_estudioDto solicitudEstudioDto;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LugarDto getLugarDto() {
        return lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    public Solicitud_estudioDto getSolicitudEstudioDto() {
        return solicitudEstudioDto;
    }

    public void setSolicitudEstudioDto(Solicitud_estudioDto solicitudEstudioDto) {
        this.solicitudEstudioDto = solicitudEstudioDto;
    }

    public Region_estudioDto()
    {
    }

    public Region_estudioDto( long id ) throws Exception
    {
        super( id );
    }
}
