package ucab.dsw.entidades;

import java.util.List;

public class RespuestaAux{

    private String _descripcion;

    private String _estado;

    private List<Long> _valor;

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public List<Long> get_valor() {
        return _valor;
    }

    public void set_valor(List<Long> _valor) {
        this._valor = _valor;
    }

    public RespuestaAux()
    {

    }

    private PreguntaAux _preguntaAux;

    public PreguntaAux get_preguntaAux() {
        return _preguntaAux;
    }

    public void set_preguntaAux(PreguntaAux _preguntaAux) {
        this._preguntaAux = _preguntaAux;
    }

    public String get_estado()
    {
        return _estado;
    }

    public void set_estado( String _estado )
    {
        this._estado = _estado;
    }
}
