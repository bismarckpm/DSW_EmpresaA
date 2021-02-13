package ucab.dsw.servicio;

import logica.comando.hijo.AddHijoComando;
import logica.comando.hijo.BuscarHijoComando;
import logica.comando.hijo.ConsultarHijoComando;
import logica.comando.hijo.EditHijoComando;
import logica.comando.telefono.AddTelefonoComando;
import logica.comando.telefono.BuscarTelefonoComando;
import logica.comando.telefono.ConsultarTelefonoComando;
import logica.comando.telefono.EditTelefonoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.HijoMapper;
import ucab.dsw.mappers.TelefonoMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoORMWS {

    private static Logger logger = LoggerFactory.getLogger(TelefonoORMWS.class);

    /**
     * Este método registra en el sistema una lista de teléfonos de un usuario
     *
     * @param  telefonos lista de teléfonos a ser registrados
     * @return      el telefonoDto que ha sido registrado en el sistema
     */
    @POST
    @Path( "/addTelefono" )
    public Response addTelefono(List<TelefonoDto> telefonos ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega los teléfonos de un usuario");
        JsonObject resultado;
        try
        {
            AddTelefonoComando comando = Fabrica.crearComandoLista(AddTelefonoComando.class, TelefonoMapper.mapDtoToEntityInsert(telefonos));
            comando.execute();
            logger.debug("Saliendo del método que agrega los teléfonos de un usuario");
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
     * Este método retorna la lista con todas los teléfonos registrados
     *
     * @return      la lista completa de teléfonos registrados
     */
    @GET
    @Path("/showTelefono")
    public Response showTelefonos() throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los teléfonos registrados");
        JsonObject resultado;
        try{
            BuscarTelefonoComando comando= Fabrica.crear(BuscarTelefonoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los teléfonos registrados");
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
     * Este método actualiza una lista de teléfonos
     *
     * @param  "telefonoDto"  teléfono a ser actualizado
     * @return      el telefonoDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateTelefono" )
    public Response updateTelefono( List<TelefonoDto> telefonos) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un teléfono");
        JsonObject resultado;
        try
        {
            EditTelefonoComando comando = Fabrica.crearComandoLista(EditTelefonoComando.class, TelefonoMapper.mapDtoToEntityUpdate(telefonos));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un teléfono");
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
     * Este método obtiene la información de una lista de telefonos de un usuario especifico
     *
     * @param  "id"  id usuario al cual se le buscaran los telefonos
     * @return      la lista de telefonos a obtener
     */
    @GET
    @Path("/TelefonosUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerTelefonosUsuario(@PathParam("id") long idDatousuario) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los teléfonos de un usuario");
        JsonObject resultado;
        try {
            ConsultarTelefonoComando comando= Fabrica.crearComandoConId(ConsultarTelefonoComando.class, idDatousuario);
            comando.execute();
            logger.debug("Saliendo del método que consulta los teléfonos de un usuario");
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
