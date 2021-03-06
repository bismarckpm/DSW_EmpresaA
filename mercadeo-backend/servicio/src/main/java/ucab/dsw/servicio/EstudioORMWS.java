package ucab.dsw.servicio;

import logica.comando.categoria.BuscarCategoriaComando;
import logica.comando.estudio.*;
import logica.fabrica.Fabrica;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.EstudioMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class EstudioORMWS {

    private static Logger logger = LoggerFactory.getLogger(EstudioORMWS.class);

    /**
     * Este método registra en el sistema un nuevo estudio
     *
     * @param  estudioDto  categoría a ser registrado
     * @return      el estudioDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/addEstudio" )
    public Response addEstudio(EstudioDto estudioDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un estudio");
        JsonObject resultado;
        try
        {
            AddEstudioComando comando = Fabrica.crearComandoConEntidad(AddEstudioComando.class, EstudioMapper.mapDtoToEntityInsert(estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un estudio");
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
     * Este método retorna la lista con todos los estudios
     *
     * @return      la lista completa de estudios registrados
     */
    @GET
    @Path("/showEstudio")
    public Response showEstudios() {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los estudios");
        JsonObject resul;
        try {
            BuscarEstudioComando comando= Fabrica.crear(BuscarEstudioComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los estudios");
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
     * Este método consulta un estudio específica
     *
     * @param  id  id del estudio a ser consultado
     * @return      el estudio completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarEstudio(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un estudio");
        JsonObject resultado;
        try {
            ConsultarEstudioComando comando=Fabrica.crearComandoConId(ConsultarEstudioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un estudio");
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
     * Este método actualiza un estudio específico
     *
     * @param  estudioDto  estudio a ser actualizado
     * @param  id  id del estudio a ser actualizado
     * @return      el estudioDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateEstudio/{id}" )
    public Response updateEstudio( @PathParam("id") long id , EstudioDto estudioDto)
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un estudio");
        JsonObject resultado;
        try
        {
            EditEstudioComando comando=Fabrica.crearComandoConEntidad(EditEstudioComando.class,EstudioMapper.mapDtoToEntityUpdate(id,estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un estudio");
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
     * Este método retorna las preguntas de un estudio con las respuestas y la cantidad de encuestados que
     * las respondieron, es decir, los resultados de un estudio
     *
     * @param  id  id del estudio del cual se desean obtener sus resultados
     * @return      una lista de preguntas relativas a un estudio que dentro de sí contienen la lista
     * de respuestas que los encuestados respondieron a ellas
     */
    @GET
    @Path("/resultadosEstudio/{id}")
    public Response resultadosEstudio(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los resultados de un estudio");
        JsonObject resultado;
        try {
            ObtenerResultadosEstudioComando comando= Fabrica.crearComandoConId(ObtenerResultadosEstudioComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta los resultados de un estudio");
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
     * Este método retorna los estudios que se recomendarán a una solicitud de estudio basado en que sus
     * características son similares
     *
     * @param  id  id de la solicitud de estudio para la cual se obtendrán las recomendaciones
     * @return      una lista de estudios que cumplen con las características de una solicitud de estudio
     */
    @GET
    @Path ("/obtenerRecomendaciones/{id}")
    public Response obtenerRecomendaciones(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que obtiene las recomendaciones para una solicitud de estudio");
        JsonObject resultado;
        try {
            ObtenerRecomendacionesComando comando= Fabrica.crearComandoConId(ObtenerRecomendacionesComando.class, id);
            comando.execute();
            logger.debug("Saliendo delEntrando al método que obtiene las recomendaciones para una solicitud de estudio");
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
     * Este método retorna los estudios que están asignados a un analista
     *
     * @param  id  id del analista del cual se desea obtener sus estudios asignados
     * @return      una lista de estudios asignados a un analista
     */
    @GET
    @Path ("/getEstudiosUsuario/{id}")
    public Response getEstudiosUsuario(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios de un analista");
        JsonObject resultado;
        try {
            ObtenerEstudiosUsuarioComando comando= Fabrica.crearComandoConId(ObtenerEstudiosUsuarioComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta los estudios de un analista");
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
     * Este método retorna los estudios que pertenecen a un cliente
     *
     * @param  id  id del cliente del cual se desea obtener sus estudios
     * @return      una lista de estudios pertenecientes a un cliente
     */
    @GET
    @Path ("/getEstudiosCliente/{id}")
    public Response getEstudiosCliente(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios de un cliente");
        JsonObject resultado;
        try {
            ObtenerEstudiosClienteComando comando= Fabrica.crearComandoConId(ObtenerEstudiosClienteComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta los estudios de un cliente");
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
     * Este método agrega un nuevo estudio a una solicitud de estudio, basado en otro estudio ya
     * existente que ha sido recomendado por ser de características similares
     *
     * @param  id_solicitud  id de la solicitud de estudio a la cual se le quiere asignar el estudio
     * @param  estudioDto estudio ya existente a partir del cual se creará el nuevo estudio
     * @return      el estudioDto que ha sido agregado al sistema basado en una recomendación
     */
    @PUT
    @Path( "/addEstudioPorRecomendacion/{id}" )
    public Response addEstudioPorRecomendacion(@PathParam("id") long id_solicitud, EstudioDto estudioDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un estudio por recomendación de otro");
        JsonObject resul;
        try
        {
            AddEstudioporRecomendacionComando comando= Fabrica.crearComandoAmbos(AddEstudioporRecomendacionComando.class, estudioDto.getId(), EstudioMapper.mapDtoToEntityInsertRecomendado( estudioDto, id_solicitud));
            comando.execute();
            logger.debug("Saliendo del método que agrega un estudio por recomendación de otro");
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
     * Este método recomienda una lista de estudios a una solicitud de estudio, basado en otra solicitud ya
     * existente que ha sido recomendado por poseer de características similares
     *
     * @param  idSolicitud  id de la solicitud de estudio a la cual se le quiere recomendar el estudio
     * @return      la lista de ListaEncuestasE que ha sido recomendada al sistema basado en una poblacion de solicitud
     */
    @GET
    @Path("/estudiosRecomendados/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerEstudiosRecomendados(@PathParam("id") long idSolicitud){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios recomendados para una solicitud de estudio");
        JsonObject resultado;
        try {
            ObtenerEstudiosRecomendadosComando comando= Fabrica.crearComandoConId(ObtenerEstudiosRecomendadosComando.class, idSolicitud);
            comando.execute();
            logger.debug("Saliendo del método que consulta los estudios recomendados para una solicitud de estudio");
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
     * Este método genera una lista de la poblacion que posee las caracteristicas de una solicitud de estudio
     *
     * @param  idEstudio  id de la solicitud de estudio de la cual se quiere obtener la poblacion
     * @return      la lista de Usuarios que ha sido recomendada el sistema basado en una poblacion de la solicitud
     */
    @GET
    @Path("/poblacionEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerPoblacionEstudio(@PathParam("id") long idEstudio){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la población de un estudio");
        JsonObject resultado;
        try {
            ObtenerPoblacionEstudioComando comando= Fabrica.crearComandoConId(ObtenerPoblacionEstudioComando.class, idEstudio);
            comando.execute();
            logger.debug("Saliendo del método que consulta la población de un estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }catch(CustomException ex){
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
     * Este método retorna la cantidad de encuestados que participaron en un estudio
     *
     * @param  id  id del estudio del cual se desea contar sus participantes
     * @return      un long que representa la cantidad de encuestados que han participado en un estudio
     */
    @GET
    @Path ("/contarParticipantes/{id}")
    public Response contarParticipantes(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la cantidad de participantes de un estudio");
        JsonObject resultado;
        try {
            ContarParticipantesComando comando= Fabrica.crearComandoConId(ContarParticipantesComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta la cantidad de participantes de un estudio");
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
     * Este método retorna los estudios a los que ha respondido un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/getEstudiosRespondidosEncuestado/{id}")
    public Response getEstudiosRespondidosEncuestado(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios que ha respondido un encuestado");
        JsonObject resultado;
        try {
            ObtenerEstudiosRespondidosComando comando= Fabrica.crearComandoConId(ObtenerEstudiosRespondidosComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta los estudios que ha respondido un encuestado");
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
     * Este método retorna las respuestas de un encuestado a las preguntas de un estudio específico
     *
     * @param  id_usuario  id del encuestado del cual se desea obtener sus respuestas
     * @param  id_estudio  id del estudio del cual se desea obtener las preguntas
     * @return      una lista de preguntas realtivas al estudio que dentro de sí contienen
     * las respuestas del encuestado a ellas
     */
    @GET
    @Path("/resultadosEncuestado/{id_usuario}/{id_estudio}")
    public Response resultadosEncuestado(@PathParam("id_usuario") long id_usuario, @PathParam("id_estudio") long id_estudio){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuestas de un encuestado en un estudio");
        ResponseDto resultado;
        JsonObject resul;
        try {
            ObtenerResultadosEncuestadoComando comando= Fabrica.crearComandoCon2Id(ObtenerResultadosEncuestadoComando.class,id_estudio, id_usuario );
            comando.execute();
            logger.debug("Saliendo del método que consulta las respuestas de un encuestado en un estudio");
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
     * Este método valida si un usuario ha participado en un estudio o no
     *
     * @param  id_usuario  id del encuestado del que se desea validar su participación
     * @param  id_estudio  id del estudio del cual se desea validar si el encuestado ha participado
     * @return      un valor Booleano que indica si el encuestado ha participado o no en el estudio
     */
    @GET
    @Path ("/validarParticipacion/{id_usuario}/{id_estudio}")
    public Response validarParticipacion(@PathParam("id_usuario") long id_usuario, @PathParam("id_estudio") long id_estudio){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta si un encuestado ha participado en un estudio o no");
        JsonObject resultado;
        try {
            ValidarParticipacionComando comando= Fabrica.crearComandoCon2Id(ValidarParticipacionComando.class,id_usuario, id_estudio );
            comando.execute();
            logger.debug("Saliendo del método que consulta si un encuestado ha participado en un estudio o no");
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
     * Este método valida si un estudio ha sido contestado por algún usuario
     *
     * @param  id_estudio  id del estudio del cual se desea validar si ha sido respondido por algún encuestado
     * @return      un valor Booleano que indica si el estudio ha sido contestado
     */
    @GET
    @Path ("/validarContestado/{id_estudio}")
    public Response validarContestado(@PathParam("id_estudio") long id_estudio){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta si un estudio ha sido contestado");
        JsonObject resultado;
        try {
            ValidarContestadoComando comando= Fabrica.crearComandoConId(ValidarContestadoComando.class, id_estudio);
            comando.execute();
            logger.debug("Saliendo del método que consulta si un estudio ha sido contestado");
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
     * Este método retorna los estudios a los que ha respondido por completo un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos por completo
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/getEstudiosRespondidosCompletos/{id}")
    public Response getEstudiosRespondidosCompletos(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios respondidos por completo");
        JsonObject resultado;
        try {
            ObtenerEstudiosRespondidos2Comando comando= Fabrica.crearComandoConId(ObtenerEstudiosRespondidos2Comando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta los estudios respondidos por completo");
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
