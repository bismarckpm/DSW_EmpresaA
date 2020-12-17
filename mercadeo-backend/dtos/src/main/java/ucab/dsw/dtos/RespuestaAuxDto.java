package ucab.dsw.dtos;

import java.util.List;

public class RespuestaAuxDto extends DtoBase{

    private String descripcion;

    private List<Long> valor;

    private PreguntaAuxDto preguntaAuxDto;

    private String estado;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Long> getValor() {
        return valor;
    }

    public void setValor(List<Long> valor) {
        this.valor = valor;
    }

    public PreguntaAuxDto getPreguntaAuxDto() {
        return preguntaAuxDto;
    }

    public void setPreguntaAuxDto(PreguntaAuxDto preguntaAuxDto) {
        this.preguntaAuxDto = preguntaAuxDto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public RespuestaAuxDto()
    {
    }

    public RespuestaAuxDto( long id ) throws Exception
    {
        super( id );
    }
}
