package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditOcupacionComando extends BaseComando {

    public long _id;
    public Ocupacion ocupacion;

    public EditOcupacionComando(long _id, Ocupacion ocupacion) {
        this._id = _id;
        this.ocupacion = ocupacion;
    }

    @Override
    public void execute() {
        try{
            DaoOcupacion dao = Fabrica.crear(DaoOcupacion.class);
            Ocupacion resul = dao.update(this.ocupacion);
            this.ocupacion=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Ocupacion actualizada")
                .add("ocupacion_nombre",this.ocupacion.get_id()).build();

        return data;
    }

}
