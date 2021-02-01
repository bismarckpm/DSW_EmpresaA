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

public class EditSubcategoriaComando extends BaseComando {

    public long _id;
    public SubcategoriaDto subcategoriaDto;

    public EditSubcategoriaComando(long _id, SubcategoriaDto subcategoriaDto) {
        this._id = _id;
        this.subcategoriaDto = subcategoriaDto;
    }

    @Override
    public void execute() {
        try{
            DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
            Subcategoria subcategoria= SubcategoriaMapper.mapDtoToEntityUpdate(_id,subcategoriaDto);
            Subcategoria resul = dao.update(subcategoria);
            this.subcategoriaDto=SubcategoriaMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Subcategoria actualizada")
                .add("subcategoria_nombre",this.subcategoriaDto.getNombre()).build();

        return data;
    }

}
