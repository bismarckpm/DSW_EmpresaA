package logica.comando.usuario;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPoblacion;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;

import java.util.List;

public class ObtenerEstudiosEncuestadoComando extends BaseComando {

    public List<Estudio> estudios = null;
    public long _id;

    public ObtenerEstudiosEncuestadoComando(long _id){
        this._id=_id;
    }

    @Override
    public void execute() {
        try{
            DaoPoblacion daoPoblacion = Fabrica.crear(DaoPoblacion.class);
            estudios = daoPoblacion.listarEstudiosUsuario(_id);
        }catch ( Exception ex )
        {
            ex.printStackTrace();
        }
    }


    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Respuesta consultada");
        data.setObjeto(this.estudios);
        return data;
    }
}
