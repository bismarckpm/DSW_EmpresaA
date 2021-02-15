package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPresentacionComando extends BaseComando {

    public Presentacion presentacion;

    public AddPresentacionComando(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoPresentacion dao = Fabrica.crear(DaoPresentacion.class);
            dao.insert( this.presentacion );
        } catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Presentacion Añadida");
        data.setObjeto(this.presentacion);

        return data;
    }

}
