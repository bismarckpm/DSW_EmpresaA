package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.EstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditEstudioComando extends BaseComando {
    
    public Estudio estudio;

    public EditEstudioComando(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public void execute() throws CustomException{
        try{
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            dao.update(this.estudio);
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
        data.setMensaje("Estudio actualizado");
        data.setObjeto(this.estudio);

        return data;
    }

}
