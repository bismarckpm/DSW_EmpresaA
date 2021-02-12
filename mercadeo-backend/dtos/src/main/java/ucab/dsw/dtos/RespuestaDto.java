package ucab.dsw.dtos;

public class RespuestaDto extends DtoBase{

    private String pregunta;

    private String estado;

    private String respuestaSimple;

    private String respuestaMultiple;

    private String respuertaAbierta;

    private String escala;

    private String respuestacol;

    private String verdaderoFalso;

    private UsuarioDto usuarioDto;

    private Pregunta_estudioDto preguntaEstudioDto;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuestaSimple() {
        return respuestaSimple;
    }

    public void setRespuestaSimple(String respuestaSimple) {
        this.respuestaSimple = respuestaSimple;
    }

    public String getRespuestaMultiple() {
        return respuestaMultiple;
    }

    public void setRespuestaMultiple(String respuestaMultiple) {
        this.respuestaMultiple = respuestaMultiple;
    }

    public String getRespuertaAbierta() {
        return respuertaAbierta;
    }

    public void setRespuertaAbierta(String respuertaAbierta) {
        this.respuertaAbierta = respuertaAbierta;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getRespuestacol() {
        return respuestacol;
    }

    public void setRespuestacol(String respuestacol) {
        this.respuestacol = respuestacol;
    }

    public String getVerdaderoFalso() {
        return verdaderoFalso;
    }

    public void setVerdaderoFalso(String verdaderoFalso) {
        this.verdaderoFalso = verdaderoFalso;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public Pregunta_estudioDto getPreguntaEstudioDto() {
        return preguntaEstudioDto;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setPreguntaEstudioDto(Pregunta_estudioDto preguntaEstudioDto) {
        this.preguntaEstudioDto = preguntaEstudioDto;
    }

    public RespuestaDto()
    {
    }

    public RespuestaDto( long id ) throws Exception
    {
        super( id );
    }
}
