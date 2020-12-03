package dtos;

public class PresentacionDto extends DtoBase{

    private String titulo;

    private String caracteristicas;

    private String estado;

    private ProductoDto productoDto;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
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

    public PresentacionDto()
    {
    }

    public PresentacionDto( long id ) throws Exception
    {
        super( id );
    }
}
