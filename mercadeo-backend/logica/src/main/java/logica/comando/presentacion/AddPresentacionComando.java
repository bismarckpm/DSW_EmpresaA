package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddPresentacionComando extends BaseComando {

    public Presentacion presentacion;

    public AddPresentacionComando(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    @Override
    public void execute() {

        try {
            DaoPresentacion dao = Fabrica.crear(DaoPresentacion.class);
            Presentacion resul = dao.insert( this.presentacion );
            this.presentacion=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Presentacion añadida")
                .add("presentacion_id",this.presentacion.get_id()).build();

        return data;
    }

}
