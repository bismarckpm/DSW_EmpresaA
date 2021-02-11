package ucab.dsw.servicio;

import logica.comando.rol.AddRolComando;
import logica.comando.rol.BuscarRolComando;
import logica.comando.rol.ConsultarRolComando;
import logica.comando.rol.EditRolComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.RolMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolORMWS {

    private static Logger logger = LoggerFactory.getLogger(RolORMWS.class);

    /**
     * Este método registra en el sistema un nuevo rol
     *
     * @param  rolDto  rol a ser registrado
     * @return      el rolDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Response addRol(RolDto rolDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddRolComando comando = Fabrica.crearComandoConEntidad(AddRolComando.class, RolMapper.mapDtoToEntityInsert(rolDto));
            comando.execute();

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
     * Este método retorna la lista con todos los roles
     *
     * @return      la lista completa de roles registrados
     */
    @GET
    @Path("/buscar")
    public Response showRol() throws Exception
    {
        JsonObject resul;
        try {
            BuscarRolComando comando= Fabrica.crear(BuscarRolComando.class);
            comando.execute();

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
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resul= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método consulta un rol específico
     *
     * @param  id  id del rol a ser consultado
     * @return      el rol completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarRol(@PathParam("id") long id) throws Exception{
        JsonObject resultado;
        try {
            ConsultarRolComando comando=Fabrica.crearComandoConId(ConsultarRolComando.class,id);
            comando.execute();

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

    /**
     * Este método actualiza un rol específico
     *
     * @param  rolDto  rol a ser actualizado
     * @return      el rolDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar" )
    public Response editRol( RolDto rolDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            EditRolComando comando=Fabrica.crearComandoConEntidad(EditRolComando.class,RolMapper.mapDtoToEntityUpdate(rolDto.getId(),rolDto));
            comando.execute();

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
}
