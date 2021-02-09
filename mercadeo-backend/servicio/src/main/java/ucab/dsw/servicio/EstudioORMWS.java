package ucab.dsw.servicio;

import logica.comando.categoria.BuscarCategoriaComando;
import logica.comando.estudio.*;
import logica.fabrica.Fabrica;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.ListaEncuestasE;
import ucab.dsw.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.EstudioMapper;

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
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            EditEstudioComando comando=Fabrica.crearComandoConEntidad(EditEstudioComando.class,EstudioMapper.mapDtoToEntityUpdate(estudioDto.getId(),estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un estudio");
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
     * Este método retorna las preguntas de un estudio con las respuestas y la cantidad de encuestados que
     * las respondieron, es decir, los resultados de un estudio
     *
     * @param  id  id del estudio del cual se desean obtener sus resultados
     * @return      una lista de preguntas relativas a un estudio que dentro de sí contienen la lista
     * de respuestas que los encuestados respondieron a ellas
     */
    @GET
    @Path("/resultadosEstudio/{id}")
    public List<PreguntaAux> resultadosEstudio(@PathParam("id") long id) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los resultados de un estudio");
        List<PreguntaAux> preguntas_salida = new ArrayList<PreguntaAux>();
        try {
            List<Pregunta_estudio> preguntas_estudio = null;
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoPregunta_estudio daoPest = new DaoPregunta_estudio();
            preguntas_estudio = daoPest.getPreguntasEstudio(daoEstudio.find(id, Estudio.class));
            for (Pregunta_estudio pregunta_estudio : preguntas_estudio) {
                PreguntaAux preguntaAux= new PreguntaAux();
                DaoPregunta_encuesta daoPenc = new DaoPregunta_encuesta();
                List<Pregunta_encuesta> pregunta_encuesta = daoPenc.getEnunciadoPregunta(pregunta_estudio);
                for (Pregunta_encuesta pregunta_encuestaAux : pregunta_encuesta) {
                    preguntaAux.set_enunciado(pregunta_estudio.get_pregunta());
                    preguntaAux.set_tipoPregunta(pregunta_encuestaAux.get_tipoPregunta());
                    preguntaAux.set_estado("A");
                }
                if (preguntaAux.get_tipoPregunta().equals("Seleccion Simple")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaSimple(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaSimple());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        List<Long> respaldoConteo = daoRespuestaCiclo.contarRespuestasSimples(respuestaCiclo);
                        respuestaAux.set_valor(respaldoConteo.get(0));
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                if (preguntaAux.get_tipoPregunta().equals("Seleccion Multiple")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaMultiple(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaMultiple());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        List<Long> respaldoConteo = daoRespuestaCiclo.contarRespuestasMultiples(respuestaCiclo);
                        respuestaAux.set_valor(respaldoConteo.get(0));
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                if (preguntaAux.get_tipoPregunta().equals("Verdadero o Falso")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaVF(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_verdaderoFalso());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        List<Long> respaldoConteo = daoRespuestaCiclo.contarRespuestasVF(respuestaCiclo);
                        respuestaAux.set_valor(respaldoConteo.get(0));
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                if (preguntaAux.get_tipoPregunta().equals("Escala")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaEscala(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_escala());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        List<Long> respaldoConteo = daoRespuestaCiclo.contarRespuestasEscala(respuestaCiclo);
                        respuestaAux.set_valor(respaldoConteo.get(0));
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                if (preguntaAux.get_tipoPregunta().equals("Abierta")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaAbierta(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaAbierta());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        respuestaAux.set_valor(null);
                        respuestaAux.set_preguntaAux(respuestaCiclo.get_usuario().get_nombreUsuario());
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                preguntas_salida.add(preguntaAux);
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los resultados de un estudio");
        }
        logger.debug("Saliendo del método que consulta los resultados de un estudio");
        return preguntas_salida;
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
    public Response obtenerRecomendaciones(@PathParam("id") long id) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que obtiene los estudios recomendados para una solicitud de estudio");
        JsonObject resultado;
        try {
            ObtenerRecomendacionesComando comando= Fabrica.crearComandoConId(ObtenerRecomendacionesComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que obtiene los estudios recomendados para una solicitud de estudio");
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
     * Este método retorna los estudios que están asignados a un analista
     *
     * @param  id  id del analista del cual se desea obtener sus estudios asignados
     * @return      una lista de estudios asignados a un analista
     */
    @GET
    @Path ("/getEstudiosUsuario/{id}")
    public Response getEstudiosUsuario(@PathParam("id") long id) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios asignados a un usuario");
        JsonObject resultado;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosUsuario(id);
            logger.debug("Saliendo del método que consulta los estudios asignados a un usuario");
            return Response.status(Response.Status.OK).entity(estudios).build();
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
     * Este método retorna los estudios que pertenecen a un cliente
     *
     * @param  id  id del cliente del cual se desea obtener sus estudios
     * @return      una lista de estudios pertenecientes a un cliente
     */
    @GET
    @Path ("/getEstudiosCliente/{id}")
    public Response getEstudiosCliente(@PathParam("id") long id) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios de un cliente");
        JsonObject resultado;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosCliente(id);
            logger.debug("Saliendo del método que consulta los estudios de un cliente");
            return Response.status(Response.Status.OK).entity(estudios).build();
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
     * Este método agrega un nuevo estudio a una solicitud de estudio, basado en otro estudio ya
     * existente que ha sido recomendado por ser de características similares
     *
     * @param  id_solicitud  id de la solicitud de estudio a la cual se le quiere asignar el estudio
     * @param  estudioDto estudio ya existente a partir del cual se creará el nuevo estudio
     * @return      el estudioDto que ha sido agregado al sistema basado en una recomendación
     */
    @PUT
    @Path( "/addEstudioPorRecomendacion/{id}" )
    public EstudioDto addEstudioPorRecomendacion(@PathParam("id") long id_solicitud, EstudioDto estudioDto ) throws Exception
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un estudio basado en otro por recomendación");
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio daoRecomendado = new DaoEstudio();
            DaoEstudio daoNuevo = new DaoEstudio();
            Estudio estudioRecomendado = daoRecomendado.find(estudioDto.getId(), Estudio.class);
            Estudio estudioNuevo = new Estudio();

            estudioNuevo.set_nombre( estudioRecomendado.get_nombre() );
            Date date = new Date();
            estudioNuevo.set_fechaInicio(date);
            estudioNuevo.set_fechaFin( null);
            estudioNuevo.set_estatus( "En Proceso");
            estudioNuevo.set_estado( "A" );

            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(id_solicitud);
            estudioNuevo.set_solicitudEstudio( solicitud_estudio);

            Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
            estudioNuevo.set_usuario( usuario);
            Estudio resul = daoNuevo.insert( estudioNuevo );
            resultado.setId( resul.get_id() );

            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            List<Pregunta_estudio> preguntasOriginales = daoPregunta_estudio.getPreguntasEstudio(estudioRecomendado);
            for (Pregunta_estudio preguntaAux : preguntasOriginales) {
                Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
                pregunta_estudio.set_pregunta( preguntaAux.get_pregunta() );
                pregunta_estudio.set_estado( "A" );
                pregunta_estudio.set_preguntaEncuesta(preguntaAux.get_preguntaEncuesta());
                pregunta_estudio.set_estudio(resul);
                Pregunta_estudio resulAux = daoPregunta_estudio.insert( pregunta_estudio );
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando estudios por recomendación");
        }
        logger.debug("Saliendo del método que agrega un estudio basado en otro por recomendación");
        return  resultado;
    }


    /**
     * Este método recomienda una lista de estudios a una solicitud de estudio, basado en otra solicitud ya
     * existente que ha sido recomendado por poseer de características similares
     *
     * @param  "id_solicitud"  id de la solicitud de estudio a la cual se le quiere recomendar el estudio
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
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            List<Object[]> Lista = daoSolicitud_estudio.listarEstudiosRecomendados(idSolicitud);

            List<ListaEncuestasE> ResponseListUpdate = new ArrayList<>(Lista.size());

            for (Object[] r : Lista) {
                ResponseListUpdate.add(new ListaEncuestasE((long)r[0], (String)r[1], (String)r[2], (Date)r[3] ));
            }
            logger.debug("Saliendo del método que consulta los estudios recomendados para una solicitud de estudio");
            return Response.status(Response.Status.OK).entity(ResponseListUpdate).build();
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
     * Este método genera una lista de la poblacion que posee las caracteristicas de una solicitud de estudio
     *
     * @param  "id_solicitud"  id de la solicitud de estudio de la cual se quiere obtener la poblacion
     * @return      la lista de Usuarios que ha sido recomendada el sistema basado en una poblacion de la solicitud
     */
    @GET
    @Path("/poblacionEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Usuario> obtenerPoblacionEstudio(@PathParam("id") long idSolicitud) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la población de un estudio");

        try {
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();

            List<Usuario> poblacion = daoSolicitud_estudio.listarPoblacionEstudio(idSolicitud);
            logger.debug("Saliendo del método que consulta la población de un estudio");
            return poblacion;

        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando la población de un estudio");

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
    public Response contarParticipantes(@PathParam("id") long id) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta la cantidad de participantes de un estudio");
        JsonObject resultado;
        try {
            DaoEstudio dao = new DaoEstudio();
            Long participantes = dao.contarParticipantes(id);
            System.out.println("Participantes: ");
            System.out.println(participantes);
            logger.debug("Saliendo del método que consulta la cantidad de participantes de un estudio");
            return Response.status(Response.Status.OK).entity(participantes).build();
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
     * Este método retorna los estudios a los que ha respondido un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/getEstudiosRespondidosEncuestado/{id}")
    public Response getEstudiosRespondidosEncuestado(@PathParam("id") long id) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los estudios respondidos por un encuestado");
        JsonObject resultado;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosRespondidosEncuestado(id);
            logger.debug("Saliendo del método que consulta los estudios respondidos por un encuestado");
            return Response.status(Response.Status.OK).entity(estudios).build();
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
     * Este método retorna las respuestas de un encuestado a las preguntas de un estudio específico
     *
     * @param  id_usuario  id del encuestado del cual se desea obtener sus respuestas
     * @param  id_estudio  id del estudio del cual se desea obtener las preguntas
     * @return      una lista de preguntas realtivas al estudio que dentro de sí contienen
     * las respuestas del encuestado a ellas
     */
    @GET
    @Path("/resultadosEncuestado/{id_usuario}/{id_estudio}")
    public List<PreguntaAux> resultadosEncuestado(@PathParam("id_usuario") long id_usuario, @PathParam("id_estudio") long id_estudio) throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las respuestas de un encuestado en un estudio");
        List<PreguntaAux> preguntas_salida = new ArrayList<PreguntaAux>();
        try {
            List<Pregunta_estudio> preguntas_estudio = null;
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoPregunta_estudio daoPest = new DaoPregunta_estudio();
            preguntas_estudio = daoPest.getPreguntasEstudio(daoEstudio.find(id_estudio, Estudio.class));
            for (Pregunta_estudio pregunta_estudio : preguntas_estudio) {
                PreguntaAux preguntaAux= new PreguntaAux();
                DaoPregunta_encuesta daoPenc = new DaoPregunta_encuesta();
                List<Pregunta_encuesta> pregunta_encuesta = daoPenc.getEnunciadoPregunta(pregunta_estudio);
                for (Pregunta_encuesta pregunta_encuestaAux : pregunta_encuesta) {
                    preguntaAux.set_enunciado(pregunta_estudio.get_pregunta());
                    preguntaAux.set_tipoPregunta(pregunta_encuestaAux.get_tipoPregunta());
                    preguntaAux.set_estado("A");
                }
                DaoRespuesta daoRespuesta = new DaoRespuesta();
                List<Respuesta> respuestas = daoRespuesta.getRespuestasDeEncuestado(pregunta_estudio, id_usuario);
                List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                for (Respuesta respuestaCiclo : respuestas) {
                    RespuestaAux respuestaAux = new RespuestaAux();
                    DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                    if (preguntaAux.get_tipoPregunta().equals("Seleccion Simple"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaSimple());
                    if (preguntaAux.get_tipoPregunta().equals("Seleccion Multiple"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaMultiple());
                    if (preguntaAux.get_tipoPregunta().equals("Verdadero o Falso"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_verdaderoFalso());
                    if (preguntaAux.get_tipoPregunta().equals("Escala"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_escala());
                    if (preguntaAux.get_tipoPregunta().equals("Abierta"))
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaAbierta());
                    respuestaAux.set_estado(respuestaCiclo.get_estado());
                    respuestaAux.set_valor(null);
                    lista_interna.add(respuestaAux);
                }
                preguntaAux.set_listaRespuestas(lista_interna);
                preguntas_salida.add(preguntaAux);
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando las respuestas del encuestado para un estudio");
        }
        logger.debug("Saliendo del método que consulta las respuestas de un encuestado en un estudio");
        return preguntas_salida;
    }

    @GET
    @Path ("/validarParticipacion/{id_usuario}/{id_estudio}")
    public Boolean validarParticipacion(@PathParam("id_usuario") long id_usuario, @PathParam("id_estudio") long id_estudio) throws Exception{


        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta si un encuestado ha participado en un estudio");
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Respuesta> estudios = dao.validarParticipacion(id_usuario, id_estudio);
            if (estudios.isEmpty()){
                System.out.println("No ha participado en el estudio");
                logger.debug("Saliendo del método que consulta si un encuestado ha participado en un estudio");
                return false;
            }
            else{
                System.out.println("Si participó en el estudio");
                logger.debug("Saliendo del método que consulta si un encuestado ha participado en un estudio");
                return true;
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error validando participación de un encuestado");
        }
    }

}
