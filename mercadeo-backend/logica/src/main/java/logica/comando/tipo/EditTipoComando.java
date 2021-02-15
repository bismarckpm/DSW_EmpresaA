package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.TipoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditTipoComando extends BaseComando {
    
    public Tipo tipo;

    public EditTipoComando(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Este comando ejecuta la actualización de un tipo de producto
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            dao.update(this.tipo);
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
        data.setMensaje("Tipo actualizado");
        data.setObjeto(this.tipo);

        return data;
    }

}
