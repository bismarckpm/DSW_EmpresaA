package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.TipoPregunta.MultipleResponse;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/pregunta_encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_encuestaORMWS {

    /**
     * Este método registra en el sistema una nueva pregunta al repositorio de preguntas
     *
     * @param  pregunta_encuestaDto  pregunta a ser registrada en el sistema
     * @return      la pregunta_encuestaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Pregunta_encuestaDto addPregunta_encuesta(Pregunta_encuestaDto pregunta_encuestaDto) throws  Exception
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            DaoUsuario daoUser = new DaoUsuario();
            DaoSubcategoria daoSub = new DaoSubcategoria();
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta();
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( "A" );
            Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.insert( pregunta_encuesta );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando una nueva pregunta");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todas las preguntas registradas
     *
     * @return      la lista completa de preguntas registradas
     */
    @GET
    @Path("/show")
    public List<Pregunta_encuesta> showPregunta_encuestas() throws  Exception{
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
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de preguntas registradas");
        }
        return pregunta_encuestas;
    }

    /**
     * Este método actualiza una pregunta específica
     *
     * @param  id  id de la pregunta que se va a actualizar
     * @param  pregunta_encuestaDto  pregunta a ser actualizado
     * @return      la pregunta_enceustaDto que ha sido actualizada
     */
    @PUT
    @Path( "/update/{id}" )
    public Pregunta_encuestaDto updatePregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto) throws Exception
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            DaoUsuario daoUser = new DaoUsuario();
            DaoSubcategoria daoSub = new DaoSubcategoria();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( pregunta_encuestaDto.getEstado() );
            Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando una pregunta");
        }
        return  resultado;
    }

    /**
     * Este método consulta una pregunta específica
     *
     * @param  id  id de la pregunta a ser consultada
     * @return      la pregunta completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Pregunta_encuesta consultarPregunta_encuesta(@PathParam("id") long id) throws Exception{

        try {
            DaoPregunta_encuesta pregunta_encuestaDao = new DaoPregunta_encuesta();
            return pregunta_encuestaDao.find(id, Pregunta_encuesta.class);
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando una pregunta");
        }
    }

    /**
     * Este método incativa una pregunta_encuesta
     *
     * @param  id  id de la pregunta que se desea incativar
     * @param  pregunta_encuestaDto pregunta completa que se desea incativar
     * @return      la pregunta_encuestaDto que ha sido inactivada, con su estado actualizado
     */
    @PUT
    @Path( "/inactivar/{id}" )
    public Pregunta_encuestaDto incativarPregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto) throws Exception
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_estado( "I" );
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error inactivando una pregunta");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista de preguntas que tienen opciones personalizadas, es decir,
     * las preguntas de selección simple y de selección múltiple
     *
     * @return      la lista de preguntas que tienen opciones personalizadas
     */
    @GET
    @Path("/showConOpciones")
    public List<Pregunta_encuesta> showPregunta_encuestas_con_opciones() throws Exception{
        List<Pregunta_encuesta> pregunta_encuestas = null;
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            pregunta_encuestas = dao.getConOpciones();
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
            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas que poseen opciones personalizadas");
        }
        return pregunta_encuestas;
    }
}
