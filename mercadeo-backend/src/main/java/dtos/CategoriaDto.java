package dtos;

public class CategoriaDto extends DtoBase{

    private String nombre;

    private String estado;

    public CategoriaDto()
    {
    }

    public CategoriaDto( long id ) throws Exception
    {
        super( id );
    }

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
}
