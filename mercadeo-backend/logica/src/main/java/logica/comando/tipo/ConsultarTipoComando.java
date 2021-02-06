package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarTipoComando extends BaseComando {

    public TipoDto tipoDto;
    public JsonObject tipoJson;
    public long _id;

    public ConsultarTipoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoTipo dao = new DaoTipo();
            Tipo tipo = dao.find(_id,Tipo.class);
            this.tipoDto= TipoMapper.mapEntityToDto(tipo);

            tipoJson= Json.createObjectBuilder()
                    .add("_id",tipo.get_id())
                    .add("_nombre",tipo.get_nombre())
                    .add("_descripcion",tipo.get_descripcion())
                    .add("_estado",tipo.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Tipo consultado")
                .add("tipo",tipoJson).build();

        return data;
    }
}
