package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class ConsultarPregunta_estudioComando extends BaseComando {

    public Pregunta_estudio pregunta_estudio;
    public long _id;

    public ConsultarPregunta_estudioComando(long _id){
        this._id=_id;
    }

    /**
     * Este comando ejecuta la consulta de una pregunta asignada a un estudio
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            this.pregunta_estudio = dao.find(_id, Pregunta_estudio.class);

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
        data.setMensaje("Pregunta_estudio consultada");
        data.setObjeto(this.pregunta_estudio);

        return data;
    }
}
