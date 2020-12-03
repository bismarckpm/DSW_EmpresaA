package dtos;

public class Nivel_academicoDto extends DtoBase{

    private String nivel;

    private String estado;

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Nivel_academicoDto()
    {
    }

    public Nivel_academicoDto( long id ) throws Exception
    {
        super( id );
    }
}
