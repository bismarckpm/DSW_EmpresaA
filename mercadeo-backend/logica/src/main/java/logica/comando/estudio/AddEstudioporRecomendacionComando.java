package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PreguntaEstudioMapper;

import java.util.Date;
import java.util.List;

public class AddEstudioporRecomendacionComando extends BaseComando {

    public Estudio estudioNuevo;
    public long id;

    public AddEstudioporRecomendacionComando(long id, Estudio estudio) {
        this.estudioNuevo = estudio;
        this.id = id;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoEstudio daoRecomendado = Fabrica.crear(DaoEstudio.class);
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();

            daoRecomendado.insert( estudioNuevo );

            Estudio estudioRecomendado = daoRecomendado.find(id, Estudio.class);

            List<Pregunta_estudio> preguntasOriginales = daoPregunta_estudio.getPreguntasEstudio(estudioRecomendado);

            for (Pregunta_estudio preguntaAux : preguntasOriginales) {
                daoPregunta_estudio.insert(PreguntaEstudioMapper.mapDtoToEntityInsertRecomendado(preguntaAux, estudioNuevo));
            }

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
        data.setObjeto(this.estudioNuevo);
        return data;
    }
}
