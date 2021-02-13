package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPresentacionComando extends BaseComando {
    
    public Presentacion presentacion;

    public EditPresentacionComando(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    @Override
    public void execute() {
        try{
            DaoPresentacion dao = Fabrica.crear(DaoPresentacion.class);
            dao.update(this.presentacion);
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Presentacion actualizada");
        data.setObjeto(this.presentacion.get_id());

        return data;
    }

}
