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

public class EditCategoriaComando extends BaseComando {

    public long _id;
    public CategoriaDto categoriaDto;

    public EditCategoriaComando(long _id, CategoriaDto categoriaDto) {
        this._id = _id;
        this.categoriaDto = categoriaDto;
    }

    @Override
    public void execute() {
        try{
            DaoCategoria dao = Fabrica.crear(DaoCategoria.class);
            Categoria categoria= CategoriaMapper.mapDtoToEntityUpdate(_id,categoriaDto);
            Categoria resul = dao.update(categoria);
            this.categoriaDto=CategoriaMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        ResponseDto responseDto = Fabrica.crear(ResponseDto.class);
        responseDto.mensaje="Categoria actualizada";
        responseDto.estado="Éxito";
        responseDto.objeto=this.categoriaDto.getNombre();

        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Categoria actualizada")
                .add("categoria_nombre",this.categoriaDto.getNombre()).build();

        return data;
    }

}
