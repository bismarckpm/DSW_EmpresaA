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

@Path( "/pregunta_encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_encuestaORMWS {

    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Pregunta_encuestaDto addPregunta_encuesta(Pregunta_encuestaDto pregunta_encuestaDto)
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta();
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( "A" );
            Usuario usuario = new Usuario(pregunta_encuestaDto.getUsuarioDto().getId());
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = new Subcategoria(pregunta_encuestaDto.getSubcategoriaDto().getId());
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.insert( pregunta_encuesta );
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
    public Pregunta_encuestaDto deletePregunta_encuesta(Pregunta_encuestaDto Pregunta_encuestaDto)
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta Pregunta_encuesta = dao.find(Pregunta_encuestaDto.getId(), Pregunta_encuesta.class);
            Pregunta_encuesta resul = dao.delete (Pregunta_encuesta );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/show")
    public List<Pregunta_encuesta> showPregunta_encuestas(){
        List<Pregunta_encuesta> pregunta_encuestas = null;
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            pregunta_encuestas = dao.findAll(Pregunta_encuesta.class);
            System.out.println("Pregunta_encuestas:");
            for (Pregunta_encuesta pregunta_encuesta : pregunta_encuestas) {
                System.out.print(pregunta_encuesta.get_id());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_descripcion());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_tipoPregunta());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_usuario().get_id());
                System.out.print("");
                System.out.print(pregunta_encuesta.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return pregunta_encuestas;
    }

    @GET
    @Path ("/consultar/{id}")
    public Pregunta_encuesta consultarPregunta_encuesta(@PathParam("id") long id){

        DaoPregunta_encuesta pregunta_encuestaDao = new DaoPregunta_encuesta();
        return pregunta_encuestaDao.find(id, Pregunta_encuesta.class);
    }

    @PUT
    @Path( "/update/{id}" )
    public Pregunta_encuestaDto updatePregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto)
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( pregunta_encuestaDto.getEstado() );
            Usuario usuario = new Usuario(pregunta_encuestaDto.getUsuarioDto().getId());
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = new Subcategoria(pregunta_encuestaDto.getSubcategoriaDto().getId());
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
