package ucab.dsw.dtos;

public class Rol_privilegioDto extends DtoBase{

    private String estado;

    private RolDto rolDto;

    private PrivilegioDto privilegioDto;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public RolDto getRolDto() {
        return rolDto;
    }

    public void setRolDto(RolDto rolDto) {
        this.rolDto = rolDto;
    }

    public PrivilegioDto getPrivilegioDto() {
        return privilegioDto;
    }

    public void setPrivilegioDto(PrivilegioDto privilegioDto) {
        this.privilegioDto = privilegioDto;
    }

    public Rol_privilegioDto()
    {
    }

    public Rol_privilegioDto( long id ) throws Exception
    {
        super( id );
    }
}
