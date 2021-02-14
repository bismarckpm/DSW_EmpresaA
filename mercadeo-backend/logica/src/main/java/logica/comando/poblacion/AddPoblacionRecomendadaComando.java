package logica.comando.poblacion;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPoblacion;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.PoblacionMapper;

import java.util.List;

public class AddPoblacionRecomendadaComando extends BaseComando {

    public Poblacion poblacion;
    public long id1;
    public long id2;

    public AddPoblacionRecomendadaComando(long id1, long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public void execute() throws CustomException{

        try {
            DaoSolicitud_estudio daoSolicitud_estudio = Fabrica.crear(DaoSolicitud_estudio.class);
            DaoPoblacion daoPoblacion =Fabrica.crear(DaoPoblacion.class);
            DaoEstudio daoEstudio =Fabrica.crear(DaoEstudio.class);

            Estudio estudio = daoEstudio.find(id1, Estudio.class);
            List<Usuario> listaPoblacion = daoSolicitud_estudio.listarPoblacionEstudio(id2);

            for (Usuario user : listaPoblacion){
                poblacion = daoPoblacion.update (PoblacionMapper.mapEntityInsert(estudio, user));
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
        data.setMensaje("Poblacion Añadida");
        data.setObjeto(this.poblacion);
        return data;
    }
}
