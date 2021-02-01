package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarSubcategoriaComando extends BaseComando {

    public SubcategoriaDto subcategoriaDto;
    public JsonObject subcategoriaJson;
    public long _id;

    public ConsultarSubcategoriaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoSubcategoria dao = new DaoSubcategoria();
            Subcategoria subcategoria = dao.find(_id,Subcategoria.class);
            this.subcategoriaDto= SubcategoriaMapper.mapEntityToDto(subcategoria);

            subcategoriaJson= Json.createObjectBuilder()
                    .add("id",subcategoria.get_id())
                    .add("nombre",subcategoria.get_nombre())
                    .add("estado",subcategoria.get_estado()).build();

        }catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Subcategoria consultada")
                .add("subcategoria",subcategoriaJson).build();

        return data;
    }
}
