package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddSubcategoriaComando extends BaseComando {

    public Subcategoria subcategoria;

    public AddSubcategoriaComando(Subcategoria subcategoriaDto) {
        this.subcategoria = subcategoriaDto;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
            dao.insert( this.subcategoria );
        } catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Subcategoria Añadida");
        data.setObjeto(this.subcategoria);

        return data;
    }

}
