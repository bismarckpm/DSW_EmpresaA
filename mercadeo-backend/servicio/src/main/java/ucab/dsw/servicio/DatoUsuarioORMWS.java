package ucab.dsw.servicio;


import logica.comando.datoUsuario.AddDatoUsuarioComando;
import logica.comando.datoUsuario.BuscarDato_usuarioComando;
import logica.comando.datoUsuario.ConsultarDato_usuarioComando;
import logica.comando.datoUsuario.EditDato_usuarioComando;
import logica.fabrica.Fabrica;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.DatoUsuarioMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/dato-usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class DatoUsuarioORMWS {

    private static Logger logger = LoggerFactory.getLogger(DatoUsuarioORMWS.class);

    private DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();


    /**
     * Este método registra en el sistema la informacion de encuestado de un usuario
     *
     * @param  "Dato_usuarioDto"  informacion del encuestado a ser registrada
     * @return      la Dato_usuarioDto que ha sido registrada en el sistema
     */
    @POST
    @Path("/crear")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response create(Dato_usuarioDto dato_usuarioDto) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un dato_usuario");

        JsonObject resultado;
        try
        {
            AddDatoUsuarioComando comando = Fabrica.crearComandoConEntidad(AddDatoUsuarioComando.class, DatoUsuarioMapper.mapDtoToEntityInsert(dato_usuarioDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un dato_usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método Edita en el sistema la informacion de encuestado de un usuario
     *
     * @param  "Dato_usuarioDto"  informacion del encuestado a ser editado
     * @param  "id" id del dato_usuario a editado
     * @return      la Dato_usuarioDto que ha sido editado en el sistema
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editDato_usuario(@PathParam("id") long id ,Dato_usuarioDto dato_usuarioDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un dato_usuario");
        JsonObject resultado;
        try
        {
            EditDato_usuarioComando comando=Fabrica.crearComandoConEntidad(EditDato_usuarioComando.class,DatoUsuarioMapper.mapDtoToEntityUpdate(id,dato_usuarioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un dato_usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch(CustomException ex){
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna la lista de datos usuario
     *
     * @return      la lista de usuarios de la bd
     */
    @GET
    @Path("/listar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response getAll() {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los dato_usuarios");
            JsonObject resul;
            try {
                BuscarDato_usuarioComando comando = Fabrica.crear(BuscarDato_usuarioComando.class);
                comando.execute();
                logger.debug("Saliendo del método que consulta todos los dato_usuarios");
                return Response.status(Response.Status.OK).entity(comando.getResult()).build();
            }
            catch(CustomException ex){
                ex.printStackTrace();
                resul = Json.createObjectBuilder()
                        .add("estado",ex.getCodigo())
                        .add("mensaje_soporte",ex.getMessage())
                        .add("mensaje",ex.getMensaje()).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                resul = Json.createObjectBuilder()
                        .add("estado", "error")
                        .add("mensaje_soporte", ex.getMessage())
                        .add("mensaje", "Ha ocurrido un error con el servidor").build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
            }
    }

    /**
     * Este método retorna un dato usuario especifico
     *
     * @param  "id"  id del dato usuario a buscar
     * @return      la Dato_usuarioDto que ha sido encontrada en el sistema
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarDato_usuario(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un dato_usuario");
        JsonObject resultado;
        try {
            ConsultarDato_usuarioComando comando=Fabrica.crearComandoConId(ConsultarDato_usuarioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un dato_usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

}
