package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.entidades.Respuesta_pregunta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path( "/respuestapregunta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaPreguntaORMWS {

    @GET
    @Path("/listar/{id}")
    public List<Respuesta_pregunta> getAllRespuestaPreguntaByIdEstudio(@PathParam("id") long id) throws Exception {

        try {
            DaoRespuesta_pregunta daoRespuestaPregunta = new DaoRespuesta_pregunta();
            List<Respuesta_pregunta> respuestaPreguntaList = daoRespuestaPregunta.findAll(Respuesta_pregunta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->(i.get_preguntaEncuesta().get_id() == id)).collect(Collectors.toList());

            return respuestaPreguntaList;
        }
        catch(Exception e){

            throw new Exception(e);

        }


    }
}
