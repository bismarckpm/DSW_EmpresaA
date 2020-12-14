package ucab.dsw.dtos;

public class Producto_presentacion_tipoDto extends DtoBase{

    private String estado;

    private ProductoDto productoDto;

    private TipoDto tipoDto;

    private PresentacionDto presentacionDto;

    public Producto_presentacion_tipoDto()
    {
    }

    public Producto_presentacion_tipoDto( long id ) throws Exception
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

    public PresentacionDto getPresentacionDto() {
        return presentacionDto;
    }

    public void setPresentacionDto(PresentacionDto presentacionDto) {
        this.presentacionDto = presentacionDto;
    }
}
