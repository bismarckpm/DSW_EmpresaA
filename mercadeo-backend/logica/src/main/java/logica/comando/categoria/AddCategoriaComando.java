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

    public CategoriaDto categoriaDto;

    public AddCategoriaComando(CategoriaDto categoriaDto) {
        this.categoriaDto = categoriaDto;
    }

    @Override
    public void execute() {

        try {
            DaoCategoria dao = Fabrica.crear(DaoCategoria.class);
            Categoria categoria = CategoriaMapper.mapDtoToEntityInsert(this.categoriaDto);
            Categoria resul = dao.insert( categoria );
            this.categoriaDto=CategoriaMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Categoria añadida")
                .add("categoria_id",this.categoriaDto.getId()).build();

        return data;
    }

}
