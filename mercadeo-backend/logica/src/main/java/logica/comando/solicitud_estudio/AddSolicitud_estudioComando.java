package logica.comando.solicitud_estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddSolicitud_estudioComando extends BaseComando {

    public Solicitud_estudioDto solicitud_estudioDto;

    public AddSolicitud_estudioComando(Solicitud_estudioDto solicitud_estudioDto) {
        this.solicitud_estudioDto = solicitud_estudioDto;
    }

    @Override
    public void execute() {

        try {
            DaoSolicitud_estudio dao = Fabrica.crear(DaoSolicitud_estudio.class);
            Solicitud_estudio solicitud_estudio = SolicitudEstudioMapper.mapDtoToEntityInsert(this.solicitud_estudioDto);
            Solicitud_estudio resul = dao.insert( solicitud_estudio );
            this.solicitud_estudioDto=SolicitudEstudioMapper.mapEntityToDto(resul);

        } catch (PruebaExcepcion pruebaExcepcion) {
            pruebaExcepcion.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Solicitud_estudio añadida")
                .add("solicitud_estudio_id",this.solicitud_estudioDto.getId()).build();

        return data;
    }

}
