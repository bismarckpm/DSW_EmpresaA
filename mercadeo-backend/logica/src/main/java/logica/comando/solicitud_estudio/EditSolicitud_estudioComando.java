package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditSolicitud_estudioComando extends BaseComando {

    public long _id;
    public Solicitud_estudioDto solicitud_estudioDto;

    public EditSolicitud_estudioComando(long _id, Solicitud_estudioDto solicitud_estudioDto) {
        this._id = _id;
        this.solicitud_estudioDto = solicitud_estudioDto;
    }

    @Override
    public void execute() {
        try{
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            Solicitud_estudio solicitud_estudio= SolicitudEstudioMapper.mapDtoToEntityUpdate(_id,solicitud_estudioDto);
            Solicitud_estudio resul = dao.update(solicitud_estudio);
            this.solicitud_estudioDto=SolicitudEstudioMapper.mapEntityToDto(resul);
        }
        catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Solicitud_estudio actualizada")
                .add("solicitud_estudio_descripcion",this.solicitud_estudioDto.getDescripcionSolicitud()).build();

        return data;
    }

}
