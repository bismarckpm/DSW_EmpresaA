package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Respuesta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarRespuestaComando extends BaseComando {

    public List<Respuesta> respuestas= null;

    @Override
    public void execute() {
        try{
            DaoRespuesta dao= Fabrica.crear(DaoRespuesta.class);
            respuestas= dao.findAll(Respuesta.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las respuestas");
        data.setObjeto(respuestas);

        return data;
    }

}
