package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.CategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarCategoriaComando extends BaseComando {

    public Categoria categoria;
    public JsonObject categoriaJson;
    public long _id;

    public ConsultarCategoriaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoCategoria dao = new DaoCategoria();
            this.categoria = dao.find(_id,Categoria.class);

            categoriaJson= Json.createObjectBuilder()
                    .add("id",categoria.get_id())
                    .add("nombre",categoria.get_nombre())
                    .add("estado",categoria.get_estado()).build();

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Categoria consultada")
                .add("categoria",categoriaJson).build();

        return data;
    }
}
