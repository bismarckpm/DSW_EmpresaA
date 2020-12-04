package ucab.dsw.dtos;

public class LugarDto extends DtoBase{

    private String nombre;

    private String tipo;

    private String categoriaSocioEconomica;

    private String estado;

    private LugarDto lugarDto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoriaSocioEconomica() {
        return categoriaSocioEconomica;
    }

    public void setCategoriaSocioEconomica(String categoriaSocioEconomica) {
        this.categoriaSocioEconomica = categoriaSocioEconomica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LugarDto getLugarDto() {
        return lugarDto;
    }

    public void setLugarDto(LugarDto lugarDto) {
        this.lugarDto = lugarDto;
    }

    public LugarDto()
    {
    }

    public LugarDto( long id ) throws Exception
    {
        super( id );
    }
}
