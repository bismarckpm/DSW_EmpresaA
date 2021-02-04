package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.CategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditCategoriaComando extends BaseComando {

    public long _id;
    public Categoria categoria;

    public EditCategoriaComando(long _id, Categoria categoria) {
        this._id = _id;
        this.categoria = categoria;
    }

    @Override
    public void execute() {
        try{
            DaoCategoria dao = Fabrica.crear(DaoCategoria.class);
            Categoria resul = dao.update(this.categoria);
            this.categoria=resul;
        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Categoria actualizada")
                .add("categoria_nombre",this.categoria.get_id()).build();

        return data;
    }

}
