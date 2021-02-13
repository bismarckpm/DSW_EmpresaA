package logica.comando.respuesta;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.excepciones.CustomException;

import java.util.ArrayList;
import java.util.List;

public class ValidarEstatusEncuestaComando extends BaseComando {

    public String validar;
    public long id1;
    public long id2;

    public ValidarEstatusEncuestaComando(long id1, long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public void execute() throws CustomException {

        try{
            DaoRespuesta daoRespuesta = Fabrica.crear(DaoRespuesta.class);
            long cantidadPreguntas = (long) daoRespuesta.contarPreguntas(id1);
            long cantidadRespondidas = (long) daoRespuesta.contarPreguntasRespondidas(id1, id2);

            if (cantidadRespondidas == 0) {
                validar = "En Espera";
            } else if (cantidadPreguntas==cantidadRespondidas) {
                validar = "Finalizado";
            } else{
                validar = "En Proceso";
            }
        }catch ( CustomException ex ) {
            throw ex;
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Cargando Validacion");
        data.setObjeto(validar);

        return data;
    }
}
