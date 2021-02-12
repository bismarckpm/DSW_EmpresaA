package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.CategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarCategoriaComando extends BaseComando {

    public Categoria categoria;
    public long _id;

    public ConsultarCategoriaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoCategoria dao = new DaoCategoria();
            this.categoria = dao.find(_id,Categoria.class);

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
        data.setMensaje("Categoria consultada");
        data.setObjeto(this.categoria);

        return data;
    }
}
