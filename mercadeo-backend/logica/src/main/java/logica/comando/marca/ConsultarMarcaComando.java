package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarMarcaComando extends BaseComando {

    public Marca marca;
    public long _id;

    public ConsultarMarcaComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoMarca dao = new DaoMarca();
            this.marca = dao.find(_id, Marca.class);

        }catch ( CustomException ex ) {
            throw ex;
        } catch ( Exception ex )
        {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Marca consultada");
        data.setObjeto(this.marca);

        return data;
    }
}
