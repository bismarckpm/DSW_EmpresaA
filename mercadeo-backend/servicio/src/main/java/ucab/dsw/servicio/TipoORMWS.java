package ucab.dsw.servicio;
import logica.comando.tipo.AddTipoComando;
import logica.comando.tipo.BuscarTipoComando;
import logica.comando.tipo.ConsultarTipoComando;
import logica.comando.tipo.EditTipoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.Response.ApiRestResponse;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.mappers.TipoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/tipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TipoORMWS {

    private DaoTipo daoTipo = new DaoTipo();

    /**
     * Este método registra en el sistema un nuevo tipo de producto
     *
     * @param  tipoDto  tipo de producto a ser registrado
     * @return      el tipoDto que ha sido registrado en el sistema
     */
    @POST
    @Path( "/agregar" )
    public Response addTipo(TipoDto tipoDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddTipoComando comando = Fabrica.crearComandoConEntity(AddTipoComando.class, TipoMapper.mapDtoToEntityInsert(tipoDto));
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
     * Este método retorna la lista con todos los tipos de proudcto
     *
     * @return      la lista completa de tipos de productos registrados
     */
    @GET
    @Path("/buscar")
    public Response showTipo() throws Exception
    {
        JsonObject resul;
        try {
            BuscarTipoComando comando= Fabrica.crear(BuscarTipoComando.class);
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
     * Este método consulta un tipo de producto específico
     *
     * @param  id  id del producto a ser consultado
     * @return      el producto completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarTipo(@PathParam("id") long id) throws Exception{
        JsonObject resultado;
        try {
            ConsultarTipoComando comando=Fabrica.crearComandoConId(ConsultarTipoComando.class,id);
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
     * Este método actualiza un tipo de producto específico
     *
     * @param  tipoDto  tipo de proudcto a ser actualizado
     * @return      el tipoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editTipo( TipoDto tipoDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            EditTipoComando comando= Fabrica.crearComandoBoth(EditTipoComando.class,tipoDto.getId(), TipoMapper.mapDtoToEntityUpdate(tipoDto.getId(),tipoDto));
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
