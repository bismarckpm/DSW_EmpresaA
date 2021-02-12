package ucab.dsw.dtos;

public class Nivel_economicoDto extends DtoBase{

    private long id;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

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

    public Nivel_economicoDto() { }

    public Nivel_economicoDto( long id ) throws Exception { super( id ); }

}
