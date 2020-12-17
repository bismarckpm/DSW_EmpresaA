package ucab.dsw.entidades;


import java.util.List;

public class PreguntaAux{

    private String _enunciado;

    private String _tipoPregunta;

    private String _estado;

    public String get_enunciado() {
        return _enunciado;
    }

    public void set_enunciado(String _enunciado) {
        this._enunciado = _enunciado;
    }

    public String get_tipoPregunta() {
        return _tipoPregunta;
    }

    public void set_tipoPregunta(String _tipoPregunta) {
        this._tipoPregunta = _tipoPregunta;
    }

    public PreguntaAux()
    {

    }

    public String get_estado()
    {
        return _estado;
    }

    public void set_estado( String _estado )
    {
        this._estado = _estado;
    }

    public List<RespuestaAux> _listaRespuestas;

    public List<RespuestaAux> get_listaRespuestas() {
        return _listaRespuestas;
    }

    public void set_listaRespuestas(List<RespuestaAux> _listaRespuestas) {
        this._listaRespuestas = _listaRespuestas;
    }
}
