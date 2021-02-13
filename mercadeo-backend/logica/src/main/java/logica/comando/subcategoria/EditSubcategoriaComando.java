package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditSubcategoriaComando extends BaseComando {
    
    public Subcategoria subcategoria;

    public EditSubcategoriaComando(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoSubcategoria dao = Fabrica.crear(DaoSubcategoria.class);
            dao.update(this.subcategoria);
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Subcategoria actualizada");
        data.setObjeto(this.subcategoria.get_id());

        return data;
    }

}
