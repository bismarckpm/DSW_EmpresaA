package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.EstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddEstudioComando extends BaseComando {

    public Estudio estudio;

    public AddEstudioComando(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            dao.insert( this.estudio );
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
        data.setMensaje("Estudio Añadido");
        data.setObjeto(this.estudio);

        return data;
    }

}
