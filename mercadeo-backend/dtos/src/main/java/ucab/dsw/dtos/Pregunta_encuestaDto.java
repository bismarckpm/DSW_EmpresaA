package ucab.dsw.dtos;

public class Pregunta_encuestaDto extends DtoBase{

    private String descripcion;

    private String tipoPregunta;

    private String estado;

    private SubcategoriaDto subcategoriaDto;

    private UsuarioDto usuarioDto;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Pregunta_encuestaDto()
    {
    }

    public Pregunta_encuestaDto( long id ) throws Exception
    {
        super( id );
    }
}
