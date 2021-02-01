package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddSubcategoriaComando extends BaseComando {

    public SubcategoriaDto subcategoriaDto;

    public AddSubcategoriaComando(SubcategoriaDto subcategoriaDto) {
        this.subcategoriaDto = subcategoriaDto;
    }

    @Override
    public void execute() {

        try {
            DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
            Subcategoria subcategoria = SubcategoriaMapper.mapDtoToEntityInsert(this.subcategoriaDto);
            Subcategoria resul = dao.insert( subcategoria );
            this.subcategoriaDto=SubcategoriaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Subcategoria añadida")
                .add("subcategoria_id",this.subcategoriaDto.getId()).build();

        return data;
    }

}
