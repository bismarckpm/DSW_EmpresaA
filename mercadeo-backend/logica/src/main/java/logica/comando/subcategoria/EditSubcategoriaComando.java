package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditSubcategoriaComando extends BaseComando {

    public long _id;
    public Subcategoria subcategoria;

    public EditSubcategoriaComando(long _id, Subcategoria subcategoria) {
        this._id = _id;
        this.subcategoria = subcategoria;
    }

    @Override
    public void execute() {
        try{
            DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
            Subcategoria resul = dao.update(subcategoria);
            this.subcategoria=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Subcategoria actualizada")
                .add("subcategoria_nombre",this.subcategoria.get_id()).build();

        return data;
    }

}
