package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.RespuestaMapper;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class EditRespuestaComando extends BaseComando {
    
    public List<Respuesta> respuesta;

    public EditRespuestaComando(List<Respuesta> respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public void execute() {
        try{
            DaoRespuesta dao = Fabrica.crear(DaoRespuesta.class);
            for (Respuesta respuestax : respuesta) {
                dao.update(respuestax);
            }
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuestas actualizadas");
        data.setObjeto(this.respuesta);

        return data;
    }

}
