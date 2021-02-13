package logica.comando.pregunta_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import java.util.List;

public class addListaPreguntasComando extends BaseComando {

    public Estudio estudio;
    public long id;
    public List<Pregunta_encuesta> listaPregunta_encuesta;

    public addListaPreguntasComando(List<Pregunta_encuesta> listaPregunta_encuesta, long id) {
        this.listaPregunta_encuesta = listaPregunta_encuesta;
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoPregunta_estudio dao = Fabrica.crear(DaoPregunta_estudio.class);
            DaoEstudio daoEstudio = Fabrica.crear(DaoEstudio.class);
            estudio = daoEstudio.find(id, Estudio.class);
            for (Pregunta_encuesta pregunta_encuestaAux : listaPregunta_encuesta) {
                dao.insert(PreguntaEstudioMapper.mapDtoToEntityInsertLista(pregunta_encuestaAux, estudio));
            }
        } catch ( CustomException ex ) {
            throw ex;
        }catch (Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Telefonos Añadidos");
        data.setObjeto(this.estudio);

        return data;
    }
}
