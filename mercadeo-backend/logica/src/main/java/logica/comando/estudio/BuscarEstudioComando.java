package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarEstudioComando extends BaseComando {

    public List<Estudio> estudios= null;
    public Long id;

    public BuscarEstudioComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() {
        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            estudios= dao.findAll(Estudio.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los estudios");
        data.setObjeto(estudios);

        return data;
    }

}
