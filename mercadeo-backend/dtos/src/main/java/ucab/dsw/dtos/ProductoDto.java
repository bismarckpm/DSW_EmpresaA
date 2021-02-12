package ucab.dsw.dtos;

public class ProductoDto extends DtoBase{

    private String nombre;

    private String descripcion;

    private String estado;

    private MarcaDto marcaDto;

    private SubcategoriaDto subcategoriaDto;

    private UsuarioDto usuarioDto;

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

    public MarcaDto getMarcaDto() {
        return marcaDto;
    }

    public void setMarcaDto(MarcaDto marcaDto) {
        this.marcaDto = marcaDto;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto subcategoriaDto) {
        this.subcategoriaDto = subcategoriaDto;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public ProductoDto()
    {
    }

    public ProductoDto( long id ) throws Exception
    {
        super( id );
    }
}
