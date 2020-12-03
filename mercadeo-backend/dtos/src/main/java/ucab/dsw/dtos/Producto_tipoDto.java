package ucab.dsw.dtos;

public class Producto_tipoDto extends DtoBase{

    private String estado;

    private ProductoDto productoDto;

    private TipoDto tipoDto;

    public Producto_tipoDto()
    {
    }

    public Producto_tipoDto( long id ) throws Exception
    {
        super( id );
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

    public TipoDto getTipoDto() {
        return tipoDto;
    }

    public void setTipoDto(TipoDto tipoDto) {
        this.tipoDto = tipoDto;
    }
}
