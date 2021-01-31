package logica.comando.telefono;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TelefonoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarTelefonoComando extends BaseComando {

    public TelefonoDto telefonoDto;
    public JsonObject telefonoJson;
    public long _id;

    public ConsultarTelefonoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find(_id,Telefono.class);
            this.telefonoDto= TelefonoMapper.mapEntityToDtoSingle(telefono);

            telefonoJson= Json.createObjectBuilder()
                    .add("id",telefono.get_id())
                    .add("nombre",telefono.get_numero())
                    .add("estado",telefono.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Telefono consultado")
                .add("telefono",telefonoJson).build();

        return data;
    }
}
