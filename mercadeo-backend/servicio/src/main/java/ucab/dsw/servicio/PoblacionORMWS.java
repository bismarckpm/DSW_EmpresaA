package ucab.dsw.servicio;

import logica.comando.categoria.AddCategoriaComando;
import logica.comando.categoria.EditCategoriaComando;
import logica.comando.poblacion.*;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PoblacionDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.PoblacionMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(PoblacionORMWS.class);

    /**
     * Este método agrega población de un estudio
     *
     * @param  poblacionDto  Poblacion a ser registrada
     * @return      la PoblacionDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPoblacion(PoblacionDto poblacionDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega la población de un estudio");
        ResponseDto resultado;
        JsonObject resul;
        try
        {
            AddPoblacionComando comando = Fabrica.crearComandoConEntidad(AddPoblacionComando.class, PoblacionMapper.mapDtoToEntityInsert(poblacionDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega la población de un estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método edita en el sistema nueva informacion de Poblacion
     *
     * @param  poblacionDto  Poblacion a ser editada
     * @return      la PoblacionDto que ha sido editado en el sistema
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editPoblacion( PoblacionDto poblacionDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza la población de un estudio");
        JsonObject resultado;
        try
        {
            EditPoblacionComando comando=Fabrica.crearComandoConEntidad(EditPoblacionComando.class,PoblacionMapper.mapDtoToEntityUpdate(poblacionDto.getId(), poblacionDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza la población de un estudio");
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
     * Este método agrega población recomendada a un estudio
     *
     * @param  idSolicitud  Solicitud de estudio del estudio a ser agregada la población
     * @param  idEstudio  Id del estudio al cual será agregada la población
     * @return      la PoblacionDto que ha sido agregada al sistema
     */
    @POST
    @Path( "/PoblacionRecomendada/{idSolicitud}/{idEstudio}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPoblacionRecomendada(@PathParam("idSolicitud") long idSolicitud,@PathParam("idEstudio") long idEstudio ) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega la población recomendada de un estudio");
        ResponseDto resultado;
        JsonObject resul;
        try{
            AddPoblacionRecomendadaComando comando = Fabrica.crearComandoCon2Id(AddPoblacionRecomendadaComando.class, idEstudio, idSolicitud);
            comando.execute();
            logger.debug("Saliendo del método que agrega la población recomendada de un estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método consulta la población de un Estudio
     *
     * @param  id  Id del estudio del cual se consulta la población
     * @return      la PoblacionDto del estudio consultado
     */
    @GET
    @Path ("/poblacionEstudio/{idEstudio}")
    public Response obtenerPoblacionEstudio(@PathParam("idEstudio") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la población de un estudio");
        JsonObject resultado;
        try {
            ObtenerPoblacionEstudioComando comando = Fabrica.crearComandoConId(ObtenerPoblacionEstudioComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta la población de un estudio");
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
     * Este método consulta la población general de un Estudio
     *
     * @param  id  Id del estudio del cual se consulta la población general
     * @return      la PoblacionDto general del estudio consultado
     */
    @GET
    @Path ("/poblacionGeneral/{idEstudio}")
    public Response obtenerPoblacion(@PathParam("idEstudio") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la población");
        JsonObject resultado;
        try {
            ObtenerPoblacionGeneralComando comando = Fabrica.crearComandoConId(ObtenerPoblacionGeneralComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta la población");
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


}
