package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddMarcaComando extends BaseComando {

    public Marca marca;

    public AddMarcaComando(Marca marca) {
        this.marca = marca;
    }

    @Override
    public void execute()throws CustomException {

        try {
            DaoMarca dao = Fabrica.crear(DaoMarca.class);
            dao.insert( this.marca );
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
        data.setMensaje("Marca Añadida");
        data.setObjeto(this.marca);

        return data;
    }

}
