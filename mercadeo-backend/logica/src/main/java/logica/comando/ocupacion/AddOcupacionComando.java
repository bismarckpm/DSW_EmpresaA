package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddOcupacionComando extends BaseComando {

    public Ocupacion ocupacion;

    public AddOcupacionComando(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Override
    public void execute() {

        try {
            DaoOcupacion dao = Fabrica.crear(DaoOcupacion.class);
            Ocupacion resul = dao.insert( this.ocupacion );
            this.ocupacion=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Ocupacion añadida")
                .add("ocupacion_id",this.ocupacion.get_id()).build();

        return data;
    }

}
