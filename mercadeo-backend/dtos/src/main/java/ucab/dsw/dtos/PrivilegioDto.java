package ucab.dsw.dtos;

public class PrivilegioDto extends DtoBase{

    private String descripcion;

    private String tipoAccion;

    private String estado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PrivilegioDto()
    {
    }

    public PrivilegioDto( long id ) throws Exception
    {
        super( id );
    }
}
