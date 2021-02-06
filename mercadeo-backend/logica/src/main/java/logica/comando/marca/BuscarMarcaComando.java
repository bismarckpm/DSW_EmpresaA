package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class BuscarMarcaComando extends BaseComando {

    public JsonArrayBuilder marcas= Json.createArrayBuilder();

    @Override
    public void execute() {

        DaoMarca dao= Fabrica.crear(DaoMarca.class);
        List<Marca> Lista= dao.findAll(Marca.class);

        for(Marca obj: Lista){

            JsonObject marca = Json.createObjectBuilder().add("_id",obj.get_id())
                    .add("_nombre",obj.get_nombre())
                    .add("_estado",obj.get_estado()).build();

            marcas.add(marca);
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder().add("mensaje","Cargando todas las marcas")
                .add("estado","Éxito")
                .add("marcas",marcas).build();

        return data;
    }

}
