package ucab.dsw.dtos;

public class Pregunta_estudioDto extends DtoBase{

    private String estado;

    private EstudioDto estudioDto;

    private Pregunta_encuestaDto preguntaEncuestaDto;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstudioDto getEstudioDto() {
        return estudioDto;
    }

    public void setEstudioDto(EstudioDto estudioDto) {
        this.estudioDto = estudioDto;
    }

    public Pregunta_encuestaDto getPreguntaEncuestaDto() {
        return preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(Pregunta_encuestaDto preguntaEncuestaDto) {
        this.preguntaEncuestaDto = preguntaEncuestaDto;
    }

    public Pregunta_estudioDto()
    {
    }

    public Pregunta_estudioDto( long id ) throws Exception
    {
        super( id );
    }
}
