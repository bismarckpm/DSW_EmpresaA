package ucab.dsw.dtos;

public class Producto_presentacionDto extends DtoBase{

    private String estado;

    private ProductoDto productoDto;

    private PresentacionDto presentacionDto;

    public Producto_presentacionDto()
    {
    }

    public Producto_presentacionDto( long id ) throws Exception
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

    public PresentacionDto getPresentacionDto() {
        return presentacionDto;
    }

    public void setPresentacionDto(PresentacionDto presentacionDto) {
        this.presentacionDto = presentacionDto;
    }
}
