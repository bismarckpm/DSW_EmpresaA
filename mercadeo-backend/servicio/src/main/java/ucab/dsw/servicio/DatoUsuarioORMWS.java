package ucab.dsw.servicio;


import logica.comando.datoUsuario.AddDatoUsuarioComando;
import logica.comando.datoUsuario.BuscarDato_usuarioComando;
import logica.comando.datoUsuario.ConsultarDato_usuarioComando;
import logica.comando.datoUsuario.EditDato_usuarioComando;
import logica.fabrica.Fabrica;
import lombok.extern.java.Log;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.DatoUsuarioMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Log
@Path( "/dato-usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class DatoUsuarioORMWS {

    private Logger logger = Logger.getLogger(DatoUsuarioORMWS.class.getName());

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

        JsonObject resultado;
        try
        {
            logger.info("Creando y ejecuntado comando DatoUsuario ");
            AddDatoUsuarioComando comando = Fabrica.crearComandoConEntity(AddDatoUsuarioComando.class, DatoUsuarioMapper.mapDtoToEntityInsert(dato_usuarioDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch (PersistenceException | DatabaseException ex){

            ex.printStackTrace();

            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","La categoria ya existe").build();

            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();

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
        JsonObject resultado;
        try
        {
            EditDato_usuarioComando comando=Fabrica.crearComandoBoth(EditDato_usuarioComando.class,dato_usuarioDto.getId(),DatoUsuarioMapper.mapDtoToEntityUpdate(dato_usuarioDto.getId(),dato_usuarioDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch (PersistenceException | DatabaseException ex){

            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","La categoria ya existe").build();

            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();

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
            JsonObject resul;
            try {
                BuscarDato_usuarioComando comando = Fabrica.crear(BuscarDato_usuarioComando.class);
                comando.execute();

                return Response.status(Response.Status.OK).entity(comando.getResult()).build();
            } catch (Exception ex) {
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

        JsonObject resultado;
        try {
            ConsultarDato_usuarioComando comando=Fabrica.crearComandoConId(ConsultarDato_usuarioComando.class,id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
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
