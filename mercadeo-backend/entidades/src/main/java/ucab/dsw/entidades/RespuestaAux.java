package ucab.dsw.entidades;

import java.util.List;

public class RespuestaAux{

    private String _descripcion;

    private String _estado;

    private Long _valor;

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public Long get_valor() {
        return _valor;
    }

    public void set_valor(Long _valor) {
        this._valor = _valor;
    }

    public RespuestaAux()
    {

    }

    private String _preguntaAux;

    public String get_preguntaAux() {
        return _preguntaAux;
    }

    public void set_preguntaAux(String _preguntaAux) {
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

