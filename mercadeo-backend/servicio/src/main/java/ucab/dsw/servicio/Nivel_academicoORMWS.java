package ucab.dsw.servicio;

import logica.comando.nivel_academico.AddNivel_academicoComando;
import logica.comando.nivel_academico.BuscarNivel_academicoComando;
import logica.comando.nivel_academico.EditNivel_academicoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.NivelAcademicoMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/nivelAcademico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Nivel_academicoORMWS {

    private static Logger logger = LoggerFactory.getLogger(Nivel_academicoORMWS.class);

    /**
     * Este método registra en el sistema un nuevo nivel académico
     *
     * @param  nivel_academicoDto  nivel académico a ser registrado
     * @return      el nivel_academicoDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Response addNivel_academico(Nivel_academicoDto nivel_academicoDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un nivel académico");
        JsonObject resultado;
        try
        {
            AddNivel_academicoComando comando = Fabrica.crearComandoConEntidad(AddNivel_academicoComando.class, NivelAcademicoMapper.mapDtoToEntityInsert(nivel_academicoDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un nivel académico");
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
     * Este método retorna la lista con todos los niveles académicos
     *
     * @return      la lista completa de niveles académicos registrados
     */
    @GET
    @Path("/buscar")
    public Response showNivel_academico()
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los niveles académicos");
        JsonObject resul;
        try {
            BuscarNivel_academicoComando comando= Fabrica.crear(BuscarNivel_academicoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los niveles académicos");
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
     * Este método actualiza un nivel académico específico
     *
     * @param  nivel_academicoDto  nivel académico a ser actualizado
     * @return      el nivel_academicoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editNivel_academico( Nivel_academicoDto nivel_academicoDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un nivel académico");
        JsonObject resultado;
        try
        {
            EditNivel_academicoComando comando= Fabrica.crearComandoConEntidad(EditNivel_academicoComando.class, NivelAcademicoMapper.mapDtoToEntityUpdate(nivel_academicoDto.getId(),nivel_academicoDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un nivel académico");
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
