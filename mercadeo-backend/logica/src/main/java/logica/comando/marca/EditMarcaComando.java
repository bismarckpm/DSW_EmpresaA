package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditMarcaComando extends BaseComando {
    
    public Marca marca;

    public EditMarcaComando(Marca marca) {
        this.marca = marca;
    }

    /**
     * Este comando ejecuta la actualización de una marca
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoMarca dao = Fabrica.crear(DaoMarca.class);
            dao.update(this.marca);
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
        data.setMensaje("Marca actualizada");
        data.setObjeto(this.marca);

        return data;
    }

}
