package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarSolicitud_estudioComando extends BaseComando {

    public List<Solicitud_estudio> solicitud_estudios= null;

    @Override
    public void execute() {
        try{
            DaoSolicitud_estudio dao= Fabrica.crear(DaoSolicitud_estudio.class);
            solicitud_estudios= dao.findAll(Solicitud_estudio.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las solicitudes de estudio");
        data.setObjeto(solicitud_estudios);

        return data;
    }

}
