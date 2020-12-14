package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
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
    @Path( "/addRespuesta_pregunta" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Respuesta_preguntaDto addRespuesta_pregunta(Respuesta_preguntaDto respuesta_preguntaDto)
    {
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();
        try
        {
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta respuesta_pregunta = new Respuesta_pregunta();
            respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
            respuesta_pregunta.set_estado( "A" );
            Pregunta_encuesta pregunta_encuesta= new Pregunta_encuesta(respuesta_preguntaDto.getPreguntaEncuestaDto().getId());
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
    @Path ("/deleteRespuesta_pregunta/{id}")
    public Respuesta_preguntaDto deleteRespuesta_pregunta (@PathParam("id") long id){
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();

        try{
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta respuesta_pregunta = dao.find(id, Respuesta_pregunta.class);
            if(respuesta_pregunta != null){
                Respuesta_pregunta result = dao.delete(respuesta_pregunta);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showRespuesta_pregunta")
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

    @PUT
    @Path( "/updateRespuesta_pregunta/{id}" )
    public Respuesta_preguntaDto updateRespuesta_pregunta( @PathParam("id") long id , Respuesta_preguntaDto respuesta_preguntaDto)
    {
        Respuesta_preguntaDto resultado = new Respuesta_preguntaDto();
        try
        {
            DaoRespuesta_pregunta dao = new DaoRespuesta_pregunta();
            Respuesta_pregunta respuesta_pregunta = dao.find(id, Respuesta_pregunta.class);
            respuesta_pregunta.set_nombre( respuesta_preguntaDto.getNombre() );
            respuesta_pregunta.set_estado( respuesta_preguntaDto.getEstado() );
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta(respuesta_preguntaDto.getPreguntaEncuestaDto().getId());
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

