package logica.comando.hijo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class BuscarHijoComando extends BaseComando {

    public List<Hijo> hijos= null;


    @Override
    public void execute() throws CustomException {
        try{
            DaoHijo dao= Fabrica.crear(DaoHijo.class);
            hijos= dao.findAll(Hijo.class);
        }catch ( CustomException ex ) {
            throw ex;
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todos los hijos");
        data.setObjeto(hijos);

        return data;
    }

}
