package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Presentacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarPresentacionComando extends BaseComando {

    public List<Presentacion> presentacions= null;

    @Override
    public void execute() {
        try{
            DaoPresentacion dao= Fabrica.crear(DaoPresentacion.class);
            presentacions= dao.findAll(Presentacion.class);
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando todas las presentaciones");
        data.setObjeto(this.presentacions);

        return data;
    }

}