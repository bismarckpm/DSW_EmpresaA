package ucab.dsw.dtos;

public class PresentacionDto extends DtoBase{

    private String titulo;

    private String caracteristicas;

    private String estado;


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

    public PresentacionDto()
    {
    }

    public PresentacionDto( long id ) throws Exception
    {
        super( id );
    }
}
