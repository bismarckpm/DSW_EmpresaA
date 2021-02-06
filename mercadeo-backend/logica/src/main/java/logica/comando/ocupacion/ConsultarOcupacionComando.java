package logica.comando.ocupacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarOcupacionComando extends BaseComando {

    public OcupacionDto ocupacionDto;
    public JsonObject ocupacionJson;
    public long _id;

    public ConsultarOcupacionComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = dao.find(_id,Ocupacion.class);
            this.ocupacionDto= OcupacionMapper.mapEntityToDto(ocupacion);

            ocupacionJson= Json.createObjectBuilder()
                    .add("_id",ocupacion.get_id())
                    .add("_nombre",ocupacion.get_nombre())
                    .add("_estado",ocupacion.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Ocupacion consultada")
                .add("ocupacion",ocupacionJson).build();

        return data;
    }
}
