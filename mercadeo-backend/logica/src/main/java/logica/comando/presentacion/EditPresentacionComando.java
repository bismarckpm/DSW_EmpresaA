package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditPresentacionComando extends BaseComando {

    public long _id;
    public Presentacion presentacion;

    public EditPresentacionComando(long _id, Presentacion presentacion) {
        this._id = _id;
        this.presentacion = presentacion;
    }

    @Override
    public void execute() {
        try{
            DaoPresentacion dao = Fabrica.crear(DaoPresentacion.class);
            Presentacion resul = dao.update(this.presentacion);
            this.presentacion=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Presentacion actualizada")
                .add("presentacion_nombre",this.presentacion.get_id()).build();

        return data;
    }

}
