package logica.comando.nivel_economico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelEconomicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarNivel_economicoComando extends BaseComando {

    public Nivel_economicoDto nivel_economicoDto;
    public JsonObject nivel_economicoJson;
    public long _id;

    public ConsultarNivel_economicoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoNivel_economico dao = new DaoNivel_economico();
            Nivel_economico nivel_economico = dao.find(_id,Nivel_economico.class);
            this.nivel_economicoDto= NivelEconomicoMapper.mapEntityToDto(nivel_economico);

            nivel_economicoJson= Json.createObjectBuilder()
                    .add("_id",nivel_economico.get_id())
                    .add("_nivel",nivel_economico.get_nivel())
                    .add("_estado",nivel_economico.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_economico consultado")
                .add("nivel_economico",nivel_economicoJson).build();

        return data;
    }
}
