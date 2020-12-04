package ucab.dsw.dtos;

public class TipoDto extends DtoBase{

    private String nombre;

    private String descripcion;

    private String estado;

    private ProductoDto productoDto;

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

    public ProductoDto getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    public TipoDto()
    {
    }

    public TipoDto( long id ) throws Exception
    {
        super( id );
    }
}
