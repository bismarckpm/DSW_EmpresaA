package ucab.dsw.servicio;

import logica.comando.lugar.*;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Lugar;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.LugarMapper;
import ucab.dsw.entidades.Response.ApiRestResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/lugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LugarORMWS {

    private static Logger logger = LoggerFactory.getLogger(LugarORMWS.class);

    /**
     * Este método registra en el sistema un nuevo lugar
     *
     * @param  lugarDto  lugar a ser registrado
     * @return      el lugarDto que ha sido registrado
     */
    @PUT
    @Path( "/addlugar" )
    public Response addLugar(LugarDto lugarDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un lugar");
        JsonObject resultado;
        try
        {
            AddLugarComando comando = Fabrica.crearComandoConEntidad(AddLugarComando.class, LugarMapper.mapDtoToEntityInsert(lugarDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un lugar");
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
     * Este método actualiza un lugar específico
     *
     * @param  lugarDto  lugar a ser actualizado
     * @param  id  id del lugar a ser actualizado
     * @return      el lugarDto que ha sido actualizado
     */
    @PUT
    @Path( "/updatelugar/{id}" )
    public Response updateLugar( @PathParam("id") long id , LugarDto lugarDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un lugar");
        JsonObject resultado;
        try
        {
            EditLugarComando comando=Fabrica.crearComandoConEntidad(EditLugarComando.class,LugarMapper.mapDtoToEntityUpdate(id,lugarDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un lugar");
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
     * Este método retorna la lista con todos los lugares
     *
     * @return      la lista completa de lugares registrados
     */
    @GET
    @Path("/buscar")
    public Response getList(){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los lugares");
        JsonObject resul;
        try {
            BuscarLugarComando comando= Fabrica.crear(BuscarLugarComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los lugares");
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
     * Este método retorna los lugares de tipo Estado
     *
     * @return      una lista de lugares de tipo Estado
     */
    @GET
    @Path("/getEstados")
    public Response getEstados() {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los lugares de tipo Estado");
        ResponseDto resultado;
        JsonObject resul;
        try{
            ObtenerEstadosComando comando= Fabrica.crear(ObtenerEstadosComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta los lugares de tipo Estado");
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
     * Este método retorna los lugares de tipo Municipio
     *
     * @return      una lista de lugares de tipo Municipio
     */
    @GET
    @Path("/getMunicipios")
    public Response getMunicipios() {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los lugares de tipo Municipio");
        ResponseDto resultado;
        JsonObject resul;
        try{
            ObtenerMunicipiosComando comando= Fabrica.crear(ObtenerMunicipiosComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta los lugares de tipo Municipio");
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

}
