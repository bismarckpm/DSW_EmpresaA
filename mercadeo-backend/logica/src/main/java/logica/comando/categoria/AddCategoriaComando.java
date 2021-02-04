package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.CategoriaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddCategoriaComando extends BaseComando {

    public Categoria categoria;

    public AddCategoriaComando(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void execute() {

        try {
            DaoCategoria dao = Fabrica.crear(DaoCategoria.class);
            Categoria resul = dao.insert( this.categoria );
            this.categoria=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Categoria añadida")
                .add("categoria_id",this.categoria.get_id()).build();

        return data;
    }

}
