package ucab.dsw.dtos;

public class MarcaDto extends DtoBase{

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

    public MarcaDto()
    {
    }

    public MarcaDto( long id ) throws Exception
    {
        super( id );
    }
}
