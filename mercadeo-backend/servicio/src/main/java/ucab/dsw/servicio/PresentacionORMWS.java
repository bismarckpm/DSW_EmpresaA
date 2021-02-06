package ucab.dsw.servicio;

import logica.comando.presentacion.AddPresentacionComando;
import logica.comando.presentacion.BuscarPresentacionComando;
import logica.comando.presentacion.ConsultarPresentacionComando;
import logica.comando.presentacion.EditPresentacionComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.PresentacionMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentacionORMWS {

    /**
     * Este método registra en el sistema una nueva presentación de producto
     *
     * @param  presentacionDto  presentación de producto a ser registrada en el sistema
     * @return      la presentacionDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/addPresentacion" )
    public Response addPresentacion(PresentacionDto presentacionDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddPresentacionComando comando = Fabrica.crearComandoConEntidad(AddPresentacionComando.class, PresentacionMapper.mapDtoToEntityInsert(presentacionDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
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
     * Este método retorna la lista con todas las presentaciones de productos
     *
     * @return      la lista completa de presentaciones de producto registradas
     */
    @GET
    @Path("/showPresentacion")
    public Response showPresentaciones() throws  Exception {
        JsonObject resul;
        try {
            BuscarPresentacionComando comando= Fabrica.crear(BuscarPresentacionComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
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
     * Este método consulta una presentación de producto específica
     *
     * @param  id  id de la presentación de producto a ser consultada
     * @return      la presentación de producto completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarPresentacion(@PathParam("id") long id) {
        JsonObject resultado;
        try {
            ConsultarPresentacionComando comando=Fabrica.crearComandoConId(ConsultarPresentacionComando.class,id);
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

    /**
     * Este método actualiza una presentación de producto específica
     *
     * @param  id  id de la presentación que se va a actualizar
     * @param  presentacionDto  presentación a ser actualizado
     * @return      la presentacionDto que ha sido actualizada
     */
    @PUT
    @Path( "/updatePresentacion/{id}" )
    public Response updatePresentacion( @PathParam("id") long id , PresentacionDto presentacionDto)
    {
        JsonObject resultado;
        try
        {
            EditPresentacionComando comando= Fabrica.crearComandoConEntidad(EditPresentacionComando.class, PresentacionMapper.mapDtoToEntityUpdate(id,presentacionDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
