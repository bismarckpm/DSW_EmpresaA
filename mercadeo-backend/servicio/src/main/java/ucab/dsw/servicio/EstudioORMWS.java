package ucab.dsw.servicio;

import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class EstudioORMWS {

    /**
     * Este método registra en el sistema un nuevo estudio
     *
     * @param  estudioDto  categoría a ser registrado
     * @return      el estudioDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/addEstudio" )
    public EstudioDto addEstudio(EstudioDto estudioDto ) throws Exception
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            Estudio estudio = new Estudio();
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFin() );
            estudio.set_estatus( estudioDto.getEstatus());
            estudio.set_estado( estudioDto.getEstado() );

            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
            estudio.set_usuario( usuario);
            Estudio resul = dao.insert( estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando un nuevo estudio");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todos los estudios
     *
     * @return      la lista completa de estudios registrados
     */
    @GET
    @Path("/showEstudio")
    public List<Estudio> showEstudios() throws Exception{
        List<Estudio> estudios = null;
        try{
            DaoEstudio dao = new DaoEstudio();
            estudios = dao.findAll(Estudio.class);
            System.out.println("Estudios:");
            for (Estudio estudio : estudios) {
                System.out.print(estudio.get_id());
                System.out.print(", ");
                System.out.print(estudio.get_nombre());
                System.out.print(", ");
                System.out.print(estudio.get_fechaInicio());
                System.out.print(", ");
                System.out.print(estudio.get_fechaFin());
                System.out.print(", ");
                System.out.print(estudio.get_estatus());
                System.out.print(", ");
                System.out.print(estudio.get_estado());
                System.out.print(", ");
                System.out.print(estudio.get_solicitudEstudio().get_id());
                System.out.print("");
                System.out.print(estudio.get_usuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando todos los estudios");
        }
        return estudios;
    }

    /**
     * Este método consulta un estudio específica
     *
     * @param  id  id del estudio a ser consultado
     * @return      el estudio completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Estudio consultarEstudio(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio estudioDao = new DaoEstudio();
            return estudioDao.find(id, Estudio.class);
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando un estudio");
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
    public EstudioDto updateEstudio( @PathParam("id") long id , EstudioDto estudioDto) throws Exception
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoUsuario daoUsuario = new DaoUsuario();

            Estudio estudio = dao.find(id, Estudio.class);
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFin() );
            estudio.set_estatus( estudioDto.getEstatus() );
            estudio.set_estado( estudioDto.getEstado() );
            estudio.set_conclusion(estudioDto.getConclusion());

            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(estudioDto.getSolicitudEstudioDto().getId(), Solicitud_estudio.class);
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = daoUsuario.find(estudioDto.getUsuarioDto().getId(), Usuario.class);
            estudio.set_usuario( usuario);
            Estudio resul = dao.update(estudio);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando un estudio");
        }
        return  resultado;
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
    public List<Estudio> obtenerRecomendaciones(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.obtenerRecomendaciones(id);
            System.out.println("Estudios recomendados:");
            for (Estudio estudioAux : estudios) {
                System.out.print(estudioAux.get_id());
                System.out.print(", ");
            }
            return estudios;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando las recomendaciones de un estudio");
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
    public List<Estudio> getEstudiosUsuario(@PathParam("id") long id) throws Exception {

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosUsuario(id);
            System.out.println("Estudios del usuario:");
            for (Estudio estudioAux : estudios) {
                System.out.print(estudioAux.get_id());
                System.out.print(", ");
            }
            return estudios;
        }
        catch(Exception e) {
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios de un analista");
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
    public List<Estudio> getEstudiosCliente(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosCliente(id);
            System.out.println("Estudios del cliente:");
            for (Estudio estudioAux : estudios) {
                System.out.print(estudioAux.get_id());
                System.out.print(", ");
            }
            return estudios;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios de un cliente");
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
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio daoRecomendado = new DaoEstudio();
            DaoEstudio daoNuevo = new DaoEstudio();
            Estudio estudioRecomendado = daoRecomendado.find(estudioDto.getId(), Estudio.class);
            Estudio estudioNuevo = new Estudio();

            estudioNuevo.set_nombre( estudioDto.getNombre() );
            Date date = new Date();
            estudioNuevo.set_fechaInicio(date);
            estudioNuevo.set_fechaFin( null);
            estudioNuevo.set_estatus( "En Espera");
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
    public List<ListaEncuestasE> obtenerEstudiosRecomendados(@PathParam("id") long idSolicitud) throws Exception{

        try {
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            List<Object[]> Lista = daoSolicitud_estudio.listarEstudiosRecomendados(idSolicitud);

            List<ListaEncuestasE> ResponseListUpdate = new ArrayList<>(Lista.size());

            for (Object[] r : Lista) {
                ResponseListUpdate.add(new ListaEncuestasE((long)r[0], (String)r[1], (String)r[2], (Date)r[3] ));
            }

            return ResponseListUpdate;

        }catch (Exception e){


            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios recomendados");

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
    public List<Usuario> obtenerPoblacionEstudio(@PathParam("id") long idEstudio) throws Exception{

        try {
            DaoPoblacion daoPoblacion = new DaoPoblacion();
            List<Usuario> poblacion = daoPoblacion.listarPoblacionEstudioUsers(idEstudio);

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
    public Long contarParticipantes(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            Long participantes = dao.contarParticipantes(id);
            System.out.println("Participantes: ");
            System.out.println(participantes);
            return participantes;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de encuestados que participaron en un estudio");
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
    public List<Estudio> getEstudiosRespondidosEncuestado(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosRespondidosEncuestado(id);
            System.out.println("Estudios respondidos:");
            for (Estudio estudioAux : estudios) {
                System.out.print(estudioAux.get_id());
                System.out.print(", ");
            }
            return estudios;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios respondidos por un encuestado");
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
        return preguntas_salida;
    }

    @GET
    @Path ("/validarParticipacion/{id_usuario}/{id_estudio}")
    public Boolean validarParticipacion(@PathParam("id_usuario") long id_usuario, @PathParam("id_estudio") long id_estudio) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Respuesta> estudios = dao.validarParticipacion(id_usuario, id_estudio);
            if (estudios.isEmpty()){
                System.out.println("No ha participado en el estudio");
                return false;
            }
            else{
                System.out.println("Si participó en el estudio");
                return true;
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error validando participación de un encuestado");
        }
    }

    @GET
    @Path ("/validarContestado/{id_estudio}")
    public Boolean validarContestado(@PathParam("id_estudio") long id_estudio) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Respuesta> estudios = dao.validarContestado(id_estudio);
            if (estudios.isEmpty()){
                System.out.println("No han participado en el estudio");
                return false;
            }
            else{
                System.out.println("Si participaron en el estudio");
                return true;
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error validando participación de un encuestado");
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
    public List<Estudio> getEstudiosRespondidosCompletos(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.getEstudiosRespondidosCompletos(id);
            System.out.println("Estudios respondidos:");
            for (Estudio estudioAux : estudios) {
                System.out.print(estudioAux.get_id());
                System.out.print(", ");
            }
            return estudios;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios respondidos por un encuestado");
        }
    }

}
