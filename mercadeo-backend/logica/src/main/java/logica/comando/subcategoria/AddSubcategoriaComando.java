package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddSubcategoriaComando extends BaseComando {

    public Subcategoria subcategoriaDto;

    public AddSubcategoriaComando(Subcategoria subcategoriaDto) {
        this.subcategoriaDto = subcategoriaDto;
    }

    @Override
    public void execute() {

        try {
            DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
            Subcategoria resul = dao.insert( this.subcategoriaDto );
            this.subcategoriaDto=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Subcategoria añadida")
                .add("subcategoria_id",this.subcategoriaDto.get_id()).build();

        return data;
    }

}
