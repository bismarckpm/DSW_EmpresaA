package ucab.dsw.dtos;


import java.util.List;

public class PreguntaAuxDto extends DtoBase{

    private String enunciado;

    private String tipoPregunta;

    private String estado;

    public String get_enunciado() {
        return enunciado;
    }

    public void set_enunciado(String _enunciado) {
        this.enunciado = _enunciado;
    }

    public String get_tipoPregunta() {
        return tipoPregunta;
    }

    public void set_tipoPregunta(String _tipoPregunta) {
        this.tipoPregunta = _tipoPregunta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PreguntaAuxDto()
    {
    }

    public PreguntaAuxDto( long id ) throws Exception
    {
        super( id );
    }

    public List<RespuestaAuxDto> getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(List<RespuestaAuxDto> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

    private List<RespuestaAuxDto> listaRespuestas;
}
