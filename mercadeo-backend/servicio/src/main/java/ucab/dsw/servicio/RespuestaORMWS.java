package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.entidades.Response.EncuestaResponse;
import ucab.dsw.entidades.Response.EstudioUsuarioResponse;
import ucab.dsw.entidades.Response.Respuesta_preguntaResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Pregunta_estudio;
import ucab.dsw.entidades.Respuesta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Log
@Path( "/respuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaORMWS {

    private Logger logger = Logger.getLogger(UsuarioORMWS.class.getName());


    /**
     * Este método lista todas las preguntas de una encuesta
     *
     * @param  "id"  id del estudio
     * @return      la lista de preguntas que posee asignado la pregunta_estudio de ese estudio
     */
    @GET
    @Path("/preguntas/{id}/{idUsuario}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EncuestaResponse> obtenerPreguntaEncuesta(@PathParam("id") long id, @PathParam("idUsuario") long idUsuario) throws Exception {

        try {
            logger.info("Accediendo al servicio de traer preguntas de encuestas");

            DaoRespuesta daoRespuesta = new DaoRespuesta();
            List<Object[]> preguntas_respuestas = daoRespuesta.listarPreguntaEncuesta(id, idUsuario);

            List<EncuestaResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new EncuestaResponse((long)r[0], (String)r[1], (String)r[2], (long)r[3]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de preguntas de una encuesta");

        }

    }

    @GET
    @Path("/validarEstatus/{id}/{idUsuario}")
    public Object validarEstatusEncuesta(@PathParam("id") long idEstudio, @PathParam("idUsuario") long idUsuario) throws Exception {

        Object validar;
        try {
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            long cantidadPreguntas = (long) daoRespuesta.contarPreguntas(idEstudio);
            long cantidadRespondidas = (long) daoRespuesta.contarPreguntasRespondidas(idEstudio, idUsuario);

            if (cantidadRespondidas == 0) {
                validar = "En Espera";
            } else if (cantidadPreguntas==cantidadRespondidas) {
                validar = "Finalizado";
            } else{
                validar = "En Proceso";
            }
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las respuestas de las preguntas de una encuesta");

        }
        return validar;
    }

    /**
     * Este método lista todas las respuestas de una encuesta
     *
     * @param  "id"  id del estudio
     * @return      la lista de respuestas que posee asignado la pregunta_estudio de ese estudio
     */
    @GET
    @Path("/respuestas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Respuesta_preguntaResponse> obtenerRespuestaEncuesta(@PathParam("id") long id) throws Exception {

        try {
            logger.info("Accediendo al servicio de traer respuesta de las preguntas de encuestas");

            DaoRespuesta daoRespuesta = new DaoRespuesta();
            List<Object[]> respuestas = daoRespuesta.listarRespuestaEncuesta(id);

            List<Respuesta_preguntaResponse> ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {
                ResponseListUpdate.add(new Respuesta_preguntaResponse((Long)r[0], (String)r[1]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las respuestas de las preguntas de una encuesta");

        }

    }


    /**
     * Este método registra en el sistema todas las respuestas de una encuesta
     *
     * @param  "List<RespuestaDto>"  lista de Respuestas a ser registrada
     * @return      la RespuestaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public RespuestaDto addRespuesta(RespuestaDto respuestaDto ) throws Exception
    {
        RespuestaDto resultado = new RespuestaDto();
        try
        {
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            DaoUsuario daoUsuario = new DaoUsuario();

            Respuesta respuesta = new Respuesta();
            respuesta.set_pregunta(respuestaDto.getPregunta());
            respuesta.set_estado(respuestaDto.getEstado());

            respuesta.set_escala(respuestaDto.getEscala());
            respuesta.set_respuestaAbierta(respuestaDto.getRespuertaAbierta());
            respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
            respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
            respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());

            Pregunta_estudio pregunta_estudio = daoPregunta_estudio.find(respuestaDto.getPreguntaEstudioDto().getId(), Pregunta_estudio.class);
            Usuario usuario = daoUsuario.find(respuestaDto.getUsuarioDto().getId(), Usuario.class);

            respuesta.set_usuario(usuario);
            respuesta.set_preguntaEstudio(pregunta_estudio);

            Respuesta resul = dao.insert(respuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {

            throw new ucab.dsw.excepciones.CreateException( "Error agregando una respuesta a una pregunta de un estudio");
        }
        return  resultado;
    }

    /**
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta de
     * selección simple
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta de
     * selección simple de un estudio específico
     */
    @Path("/showRespuestasAPreguntaSimple/{id}")
    public List<Respuesta> showRespuestasAPreguntaSimple(@PathParam("id") long id) throws Exception{
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaSimple(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaSimple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas de selección simple de un estudio");
        }
        return respuestas;
    }

    /**
     * Este método retorna la cantidad de personas que respondieron a una opción de una pregunta de
     * selección simple de un estudio específico
     *
     * @param  id  id de la respuesta de la cual se desea contar los encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción de una
     * pregunta de selección simple de un estudio
     */
    @GET
    @Path("/contarRespuestasSimples/{id}")
    public List<Long> contarRespuestasSimples(@PathParam("id") long id) throws Exception{
        List<Long> cantidad = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoRespuesta daoRespuesta= new DaoRespuesta();
            cantidad = dao.contarRespuestasSimples(daoRespuesta.find(id, Respuesta.class));
            System.out.println("Cantidad:");
            for (Long numero : cantidad) {
                System.out.print(numero);
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de usuarios que respondieron una pregunta de selección simple");
        }
        return cantidad;
    }

    /**
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta de
     * selección múltiple
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta de
     * selección múltiple de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaMultiple/{id}")
    public List<Respuesta> showRespuestasAPreguntaMultiple(@PathParam("id") long id) throws Exception{
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaMultiple(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas de selección múltiple de un estudio");
        }
        return respuestas;
    }

    /**
     * Este método retorna la cantidad de personas que respondieron a una opción de una pregunta de
     * selección múltiple de un estudio específico
     *
     * @param  id  id de la respuesta de la cual se desea contar los encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción de una
     * pregunta de selección múltiple de un estudio
     */
    @GET
    @Path("/contarRespuestasMultiples/{id}")
    public List<Long> contarRespuestasMultiples(@PathParam("id") long id) throws Exception{
        List<Long> cantidad = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoRespuesta daoRespuesta= new DaoRespuesta();
            cantidad = dao.contarRespuestasMultiples(daoRespuesta.find(id, Respuesta.class));
            System.out.println("Cantidad:");
            for (Long numero : cantidad) {
                System.out.print(numero);
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de usuarios que respondieron una pregunta de selección múltiple");
        }
        return cantidad;
    }

    /**
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta de
     * verdadero o falso
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta de
     * verdadero o falso de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaVF/{id}")
    public List<Respuesta> showRespuestasAPreguntaVF(@PathParam("id") long id) throws  Exception{
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaVF(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas de verdadero o falso de un estudio");
        }
        return respuestas;
    }

    /**
     * Este método retorna la cantidad de personas que respondieron a una opción de una pregunta de
     * verdadero o falso de un estudio específico
     *
     * @param  id  id de la respuesta de la cual se desea contar los encuestados que la respondieron
     * @return      un long que representa la cantidad de encuestados que respondieron a una opción de una
     * pregunta de verdadero o falso de un estudio
     */
    @GET
    @Path("/contarRespuestasVF/{id}")
    public List<Long> contarRespuestasVF(@PathParam("id") long id) throws Exception{
        List<Long> cantidad = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoRespuesta daoRespuesta= new DaoRespuesta();
            cantidad = dao.contarRespuestasVF(daoRespuesta.find(id, Respuesta.class));
            System.out.println("Cantidad:");
            for (Long numero : cantidad) {
                System.out.print(numero);
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la cantidad de usuarios que respondieron una pregunta de Verdadero o falso");
        }
        return cantidad;
    }

    /**
     * Este método retorna las respuestas de los encuestados de un estudio a una pregunta abierta
     *
     * @param  id  id de la pregunta_estudio de la cual se desean obtener sus respuestas
     * @return      la lista de respuestas de los encuestados ante una pregunta abierta de un estudio específico
     */
    @GET
    @Path("/showRespuestasAPreguntaAbierta/{id}")
    public List<Respuesta> showRespuestasAPreguntaAbierta(@PathParam("id") long id) throws Exception{
        List<Respuesta> respuestas = null;
        try{
            DaoRespuesta dao = new DaoRespuesta();
            DaoPregunta_estudio daoPregunta_estudio = new DaoPregunta_estudio();
            respuestas = dao.getRespuestasAPreguntaAbierta(daoPregunta_estudio.find(id, Pregunta_estudio.class));
            System.out.println("Respuestas:");
            for (Respuesta respuesta : respuestas) {
                System.out.print(respuesta.get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaMultiple());
                System.out.print(", ");
                System.out.print(respuesta.get_respuestaAbierta());
                System.out.print(", ");
                System.out.print(respuesta.get_verdaderoFalso());
                System.out.print(", ");
                System.out.print(respuesta.get_estado());
                System.out.print(", ");
                System.out.print(respuesta.get_preguntaEstudio().get_id());
                System.out.print(", ");
                System.out.print(respuesta.get_usuario().get_id());
                System.out.print(", ");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de todas las respuestas de las preguntas abiertas de un estudio");
        }
        return respuestas;
    }


}
