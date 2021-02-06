package logica.comando.presentacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarPresentacionComando extends BaseComando {

    public PresentacionDto presentacionDto;
    public JsonObject presentacionJson;
    public long _id;

    public ConsultarPresentacionComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find(_id,Presentacion.class);
            this.presentacionDto= PresentacionMapper.mapEntityToDto(presentacion);

            presentacionJson= Json.createObjectBuilder()
                    .add("_id",presentacion.get_id())
                    .add("_titulo",presentacion.get_titulo())
                    .add("_caracteristicas",presentacion.get_caracteristicas())
                    .add("_estado",presentacion.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Presentacion consultada")
                .add("presentacion",presentacionJson).build();

        return data;
    }
}
