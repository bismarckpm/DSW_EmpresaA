package dtos;

public class OcupacionDto extends DtoBase{

    private String nombre;

    private String estado;

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

    public OcupacionDto()
    {
    }

    public OcupacionDto( long id ) throws Exception
    {
        super( id );
    }
}
