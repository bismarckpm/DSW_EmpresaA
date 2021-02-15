package logica.comando.subcategoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarSubcategoriaComando extends BaseComando {

    public Subcategoria subcategoria;
    public long _id;

    public ConsultarSubcategoriaComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de una subcategoría
     */
    @Override
    public void execute()throws CustomException {
        try{
            DaoSubcategoria dao = new DaoSubcategoria();
            this.subcategoria = dao.find(_id, Subcategoria.class);

        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Subcategoria consultada");
        data.setObjeto(this.subcategoria);

        return data;
    }
}
