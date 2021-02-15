package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditSolicitud_estudioComando extends BaseComando {
    
    public Solicitud_estudio solicitud_estudio;

    public EditSolicitud_estudioComando(Solicitud_estudio solicitud_estudio) {
        this.solicitud_estudio = solicitud_estudio;
    }

    /**
     * Este comando ejecuta la actualización de una solicitud de estudio
     */
    @Override
    public void execute() throws CustomException{
        try{
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            dao.update(this.solicitud_estudio);
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
        data.setMensaje("Solicitud_estudio actualizada");
        data.setObjeto(this.solicitud_estudio);

        return data;
    }

}
