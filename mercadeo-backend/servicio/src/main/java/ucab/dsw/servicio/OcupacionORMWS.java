package ucab.dsw.servicio;

import logica.comando.ocupacion.AddOcupacionComando;
import logica.comando.ocupacion.BuscarOcupacionComando;
import logica.comando.ocupacion.EditOcupacionComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.mappers.OcupacionMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionORMWS {

    /**
     * Este método registra en el sistema una nueva ocupación
     *
     * @param  ocupacionDto ocupación a ser registrada
     * @return      la ocupacionDto que ha sido registrada en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Response addOcupacion(OcupacionDto ocupacionDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddOcupacionComando comando = Fabrica.crearComandoConEntity(AddOcupacionComando.class, OcupacionMapper.mapDtoToEntityInsert(ocupacionDto));
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
     * Este método retorna la lista con todas las ocupaciones
     *
     * @return      la lista completa de ocupaciones registradas
     */
    @GET
    @Path("/buscar")
    public Response showOcupacion() throws  Exception
    {
        JsonObject resul;
        try {
            BuscarOcupacionComando comando= Fabrica.crear(BuscarOcupacionComando.class);
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
     * Este método actualiza una ocupación específica
     *
     * @param  ocupacionDto  ocupación a ser actualizada
     * @return      la ocupacionDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editOcupacion( OcupacionDto ocupacionDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            EditOcupacionComando comando=Fabrica.crearComandoBoth(EditOcupacionComando.class,ocupacionDto.getId(),OcupacionMapper.mapDtoToEntityUpdate(ocupacionDto.getId(),ocupacionDto));
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
