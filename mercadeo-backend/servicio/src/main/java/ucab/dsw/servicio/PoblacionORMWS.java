package ucab.dsw.servicio;

import logica.comando.categoria.AddCategoriaComando;
import logica.comando.categoria.EditCategoriaComando;
import logica.comando.poblacion.*;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PoblacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.PoblacionMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/poblacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PoblacionORMWS {

    /**
     * Este método registra en el sistema nueva informacion de producto y presentacion a un producto
     *
     * @param  "PoblacionDto"  Poblacion a ser registrada
     * @return      la PoblacionDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPoblacion(PoblacionDto poblacionDto ) throws Exception
    {
        ResponseDto resultado;
        try
        {
            AddPoblacionComando comando = Fabrica.crearComandoConEntidad(AddPoblacionComando.class, PoblacionMapper.mapDtoToEntityInsert(poblacionDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error asignando Poblacion");
        }
    }

    /**
     * Este método edita en el sistema nueva informacion de Poblacion
     *
     * @param  "PoblacionDto"  Poblacion a ser editada
     * @return      la PoblacionDto que ha sido editado en el sistema
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editPoblacion( PoblacionDto poblacionDto) throws  Exception
    {
        JsonObject resultado;
        try
        {
            EditPoblacionComando comando=Fabrica.crearComandoConEntidad(EditPoblacionComando.class,PoblacionMapper.mapDtoToEntityUpdate(poblacionDto.getId(), poblacionDto));
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

    @POST
    @Path( "/PoblacionRecomendada/{idSolicitud}/{idEstudio}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPoblacionRecomendada(@PathParam("idSolicitud") long idSolicitud,@PathParam("idEstudio") long idEstudio ) throws Exception{
        ResponseDto resultado;
        try{
            AddPoblacionRecomendadaComando comando = Fabrica.crearComandoCon2Id(AddPoblacionRecomendadaComando.class, idEstudio, idSolicitud);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }catch( Exception ex ){
            throw new ucab.dsw.excepciones.GetException( "Error consultando Poblacion");
        }
    }

    /**
     * Este método retorna los estudios a los que ha respondido un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/poblacionEstudio/{idEstudio}")
    public Response obtenerPoblacionEstudio(@PathParam("idEstudio") long id) throws Exception{

        try {
            ObtenerPoblacionEstudioComando comando = Fabrica.crearComandoConId(ObtenerPoblacionEstudioComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios respondidos por un encuestado");
        }

    }

    /**
     * Este método retorna los estudios a los que ha respondido un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/poblacionGeneral/{idEstudio}")
    public Response obtenerPoblacion(@PathParam("idEstudio") long id) throws Exception{

        try {
            ObtenerPoblacionGeneralComando comando = Fabrica.crearComandoConId(ObtenerPoblacionGeneralComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios respondidos por un encuestado");
        }

    }


}
