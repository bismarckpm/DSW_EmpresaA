package ucab.dsw.dtos;

public class RolDto extends DtoBase{

    private  long id;

    private String estado;

    private String nombre;

    private String descripcion;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

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

    @Override
    public long getId() { return id; }

    @Override
    public void setId(long id) { this.id = id; }

    public RolDto()
    {
    }

    public RolDto( long id ) throws Exception
    {
        super( id );
    }
}
