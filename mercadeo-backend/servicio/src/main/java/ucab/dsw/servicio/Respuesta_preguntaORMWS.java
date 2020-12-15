package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/respuesta_pregunta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Respuesta_preguntaORMWS {

    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Respuesta_preguntaDto addRespuesta_pregunta(Respuesta_preguntaDto respuesta_preguntaDto)
    {
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();
        try
        {
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
            Respuesta_pregunta respuesta_pregunta = new Respuesta_pregunta();
            respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
            respuesta_pregunta.set_estado( "A" );
            Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
            respuesta_pregunta.set_preguntaEncuesta( pregunta_encuesta);
            Respuesta_pregunta resul = dao.insert( respuesta_pregunta );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path( "/borrar/{id}" )
    public Respuesta_preguntaDto deleteRespuesta_pregunta( Respuesta_preguntaDto Respuesta_preguntaDto)
    {
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();
        try
        {
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta Respuesta_pregunta = dao.find(Respuesta_preguntaDto.getId(), Respuesta_pregunta.class);
            Respuesta_pregunta resul = dao.delete (Respuesta_pregunta );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path ("/consultar/{id}")
    public Respuesta_pregunta consultarRespuesta_pregunta(@PathParam("id") long id){

        DaoRespuesta_pregunta respuesta_preguntaDao = new DaoRespuesta_pregunta();
        return respuesta_preguntaDao.find(id, Respuesta_pregunta.class);
    }

    @GET
    @Path("/show")
    public List<Respuesta_pregunta> showRespuesta_preguntas(){
        List<Respuesta_pregunta> respuesta_preguntas = null;
        try{
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            respuesta_preguntas = dao.findAll(Respuesta_pregunta.class);
            System.out.println("Respuesta_preguntas:");
            for (Respuesta_pregunta respuesta_pregunta : respuesta_preguntas) {
                System.out.print(respuesta_pregunta.get_id());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_nombre());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_preguntaEncuesta().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return respuesta_preguntas;
    }

    @GET
    @Path("/showRespuestasPregunta/{id}")
    public List<Respuesta_pregunta> showRespuesta_preguntas_respuestas(@PathParam("id") long id){
        List<Respuesta_pregunta> respuesta_preguntas = null;
        try{
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            DaoPregunta_encuesta daoPregunta = new DaoPregunta_encuesta();
            respuesta_preguntas = dao.getRespuestasPregunta(daoPregunta.find(id, Pregunta_encuesta.class));
            System.out.println("Respuesta_preguntas:");
            for (Respuesta_pregunta respuesta_pregunta : respuesta_preguntas) {
                System.out.print(respuesta_pregunta.get_id());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_nombre());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta_pregunta.get_preguntaEncuesta().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return respuesta_preguntas;
    }

    @PUT
    @Path( "/update/{id}" )
    public Respuesta_preguntaDto updateRespuesta_pregunta( @PathParam("id") long id , Respuesta_preguntaDto respuesta_preguntaDto)
    {
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();
        try
        {
            DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta respuesta_pregunta = dao.find(id, Respuesta_pregunta.class);
            respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
            respuesta_pregunta.set_estado( respuesta_preguntaDto.getEstado() );
            Pregunta_encuesta pregunta_encuesta = daoPregunta_encuesta.find(respuesta_preguntaDto.getPreguntaEncuestaDto().getId(), Pregunta_encuesta.class);
            respuesta_pregunta.set_preguntaEncuesta(pregunta_encuesta);
            Respuesta_pregunta resul = dao.update(respuesta_pregunta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}

