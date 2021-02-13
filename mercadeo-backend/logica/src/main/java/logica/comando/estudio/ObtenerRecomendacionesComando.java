package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.CustomException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.List;

public class ObtenerRecomendacionesComando extends BaseComando {

    public List<Estudio> estudios = null;
    public long id;

    public ObtenerRecomendacionesComando(long id) {
        this.id = id;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoEstudio dao= Fabrica.crear(DaoEstudio.class);
            estudios = dao.obtenerRecomendaciones(id);
        }
        catch ( CustomException ex ) {
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
        data.setMensaje("Cargando Estudios Recomendados");
        data.setObjeto(estudios);
        return data;
    }
}
