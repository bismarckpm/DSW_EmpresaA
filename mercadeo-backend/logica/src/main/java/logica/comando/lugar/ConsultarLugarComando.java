package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.LugarMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarLugarComando extends BaseComando {

    public LugarDto lugarDto;
    public JsonObject lugarJson;
    public long _id;

    public ConsultarLugarComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoLugar dao = new DaoLugar();
            Lugar lugar = dao.find(_id,Lugar.class);
            this.lugarDto= LugarMapper.mapEntityToDto(lugar);

            lugarJson= Json.createObjectBuilder()
                    .add("_id",lugar.get_id())
                    .add("_nombre",lugar.get_nombre())
                    .add("_tipo",lugar.get_tipo())
                    .add("_categoriaSocioEconomica",lugar.get_categoriaSocioEconomica())
                    .add("_estado",lugar.get_estado())
                    .add("_lugar",lugar.get_lugar().get_id()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Lugar consultado")
                .add("lugar",lugarJson).build();

        return data;
    }
}
