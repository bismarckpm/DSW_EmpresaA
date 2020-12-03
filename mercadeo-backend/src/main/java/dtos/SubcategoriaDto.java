package dtos;

public class SubcategoriaDto extends DtoBase{

    private String nombre;

    private String descripcion;

    private String estado;

    private CategoriaDto categoriaDto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CategoriaDto getCategoriaDto() {
        return categoriaDto;
    }

    public void setCategoriaDto(CategoriaDto categoriaDto) {
        this.categoriaDto = categoriaDto;
    }

    public SubcategoriaDto()
    {
    }

    public SubcategoriaDto( long id ) throws Exception
    {
        super( id );
    }
}
