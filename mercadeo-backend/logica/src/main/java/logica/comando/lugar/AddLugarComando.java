package logica.comando.lugar;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.LugarMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddLugarComando extends BaseComando {

    public Lugar lugar;

    public AddLugarComando(Lugar lugar) {
        this.lugar = lugar;
    }

    /**
     * Este comando ejecuta la inserción de un lugar
     */
    @Override
    public void execute() throws CustomException{

        try {
            DaoLugar dao = Fabrica.crear(DaoLugar.class);
            dao.insert( this.lugar );
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
        data.setMensaje("Lugar Añadido");
        data.setObjeto(this.lugar);

        return data;
    }

}
