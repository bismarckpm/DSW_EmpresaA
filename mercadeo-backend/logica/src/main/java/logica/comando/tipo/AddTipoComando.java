package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.TipoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddTipoComando extends BaseComando {

    public Tipo tipo;

    public AddTipoComando(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Este comando ejecuta la inserción de un tipo de producto
     */
    @Override
    public void execute()throws CustomException {

        try {
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            dao.insert( this.tipo );
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
        data.setMensaje("Tipo Añadido");
        data.setObjeto(this.tipo);

        return data;
    }

}
