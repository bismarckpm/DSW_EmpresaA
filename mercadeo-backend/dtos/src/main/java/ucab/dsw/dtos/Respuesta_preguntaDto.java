package ucab.dsw.dtos;

public class Respuesta_preguntaDto extends DtoBase{

    private String estado;

    private String nombre;

    private Pregunta_encuestaDto preguntaEncuestaDto;

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

    public Pregunta_encuestaDto getPreguntaEncuestaDto() {
        return preguntaEncuestaDto;
    }

    public void setPreguntaEncuestaDto(Pregunta_encuestaDto preguntaEncuestaDto) {
        this.preguntaEncuestaDto = preguntaEncuestaDto;
    }

    public Respuesta_preguntaDto()
    {
    }

    public Respuesta_preguntaDto( long id ) throws Exception
    {
        super( id );
    }
}
