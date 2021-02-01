package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarMarcaComando extends BaseComando {

    public MarcaDto marcaDto;
    public JsonObject marcaJson;
    public long _id;

    public ConsultarMarcaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(_id,Marca.class);
            this.marcaDto= MarcaMapper.mapEntityToDto(marca);

            marcaJson= Json.createObjectBuilder()
                    .add("id",marca.get_id())
                    .add("nombre",marca.get_nombre())
                    .add("estado",marca.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Marca consultada")
                .add("marca",marcaJson).build();

        return data;
    }
}
