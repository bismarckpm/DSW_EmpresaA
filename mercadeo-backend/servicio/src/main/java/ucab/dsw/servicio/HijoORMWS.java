package ucab.dsw.servicio;

import logica.comando.estudio.ObtenerResultadosEstudioComando;
import logica.comando.hijo.AddHijoComando;
import logica.comando.hijo.BuscarHijoComando;
import logica.comando.hijo.ConsultarHijoComando;
import logica.comando.hijo.EditHijoComando;
import logica.comando.rol.AddRolComando;
import logica.comando.rol.BuscarRolComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.HijoMapper;
import ucab.dsw.mappers.RolMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HijoORMWS {

    private static Logger logger = LoggerFactory.getLogger(HijoORMWS.class);

    /**
     * Este método registra en el sistema una lista de hijos de un usuario
     *
     * @param  hijos lista de hijos a ser registrada en el sistema
     * @return      el hijoDto que ha sido registrado
     */
    @POST
    @Path( "/addHijo" )
    public Response addHijo(List<HijoDto> hijos ) throws Exception
    {
        JsonObject resultado;
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega los hijos de un usuario");
        try
        {
            AddHijoComando comando = Fabrica.crearComandoLista(AddHijoComando.class, HijoMapper.mapDtoToEntityInsert(hijos));
            comando.execute();
            logger.debug("Saliendo del método que agrega los hijos de un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna la lista con todos los hijos de usuarios registrados
     *
     * @return      la lista completa de hijos registrados
     */
    @GET
    @Path("/showHijo")
    public Response showHijos() throws Exception{
        JsonObject resultado;
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los hijos registrados");
        try{
            BuscarHijoComando comando= Fabrica.crear(BuscarHijoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los hijos registrados");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método actualiza la información de una lista de hijos
     *
     * @param  hijos  lista de hijos a ser actualizados
     * @return      el hijoDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateHijo" )
    public Response updateHijo(List<HijoDto> hijos) throws Exception {
        JsonObject resultado;
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza los hijos de un usuario");
        try
        {
            EditHijoComando comando = Fabrica.crearComandoLista(EditHijoComando.class, HijoMapper.mapDtoToEntityUpdate(hijos));
            comando.execute();
            logger.debug("Saliendo del método que actualiza los hijos de un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método obtiene la información de una lista de hijos de un usuario especifico
     *
     * @param  "id"  id usuario al cual se le buscaran los hijos
     * @return      la lista de hijos a obtener
     */
    @GET
    @Path("/HijosUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerHijosUsuario(@PathParam("id") long idDatousuario) throws Exception{
        JsonObject resultado;
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los hijos de un usuario");
        try {
            ConsultarHijoComando comando= Fabrica.crearComandoConId(ConsultarHijoComando.class, idDatousuario);
            comando.execute();
            logger.debug("Saliendo del método que consulta los hijos de un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }

    }
}
