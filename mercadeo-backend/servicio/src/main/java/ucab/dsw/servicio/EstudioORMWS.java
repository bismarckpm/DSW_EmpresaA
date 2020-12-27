package ucab.dsw.servicio;

import ucab.dsw.Response.EstudioListResponse;
import ucab.dsw.Response.EstudioResponse;
import ucab.dsw.Response.UsuarioNoRespondieronEstudioResponse;
import ucab.dsw.Response.UsuarioRespondieronEstudioResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class EstudioORMWS {

    @PUT
    @Path( "/addEstudio" )
    public EstudioDto addEstudio(EstudioDto estudioDto )
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = new Estudio();
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFin() );
            estudio.set_estatus( estudioDto.getEstatus() );
            estudio.set_estado( estudioDto.getEstado() );

            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(estudioDto.getSolicitudEstudioDto().getId());
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
            estudio.set_usuario( usuario);
            Estudio resul = dao.insert( estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteEstudio/{id}")
    public EstudioDto deleteEstudio (@PathParam("id") long id){
        EstudioDto resultado = new EstudioDto();

        try{
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            if(estudio != null){
                Estudio result = dao.delete(estudio);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showEstudio")
    public List<Estudio> showEstudios(){
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
            String problem = e.getMessage();
        }
        return estudios;
    }

    @GET
    @Path ("/consultar/{id}")
    public Estudio consultarEstudio(@PathParam("id") long id){

        DaoEstudio estudioDao = new DaoEstudio();
        return estudioDao.find(id, Estudio.class);
    }

    @PUT
    @Path( "/updateEstudio/{id}" )
    public EstudioDto updateEstudio( @PathParam("id") long id , EstudioDto estudioDto)
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFin() );
            estudio.set_estatus( estudioDto.getEstatus() );
            estudio.set_estado( estudioDto.getEstado() );
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(estudioDto.getSolicitudEstudioDto().getId());
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
            estudio.set_usuario( usuario);
            Estudio resul = dao.update(estudio);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("listar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EstudioListResponse> getAllByUser (@PathParam("id") long id) throws Exception {

        try {

            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudioList = dao.findAll(Estudio.class);
            List<EstudioListResponse> estudioListUpdate = new ArrayList<>();
            estudioList.stream().filter(i->(i.get_usuario().get_id() == id && i.get_estado().equals("A"))).collect(Collectors.toList()).forEach(i->{
                try {
                    estudioListUpdate.add(new EstudioListResponse(i.get_id(), i.get_nombre(), i.get_estado(), i.get_estatus(), formatDateToString(i.get_fechaInicio()), formatDateToString(i.get_fechaFin()), i.get_solicitudEstudio(), i.get_usuario()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });



            return estudioListUpdate;

        }catch (Exception e){

            throw new Exception(e.getMessage());

        }

    }

    private String formatDate(Date date){
        return DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format((TemporalAccessor) date);
    }

    private String formatDateToString(Date date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String dateUpdate = sdf.format(date);

        return dateUpdate;
    }

    @GET
    @Path("/resultadosEstudio/{id}")
    public List<PreguntaAux> resultadosEstudio(@PathParam("id") long id){
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
                    preguntaAux.set_enunciado(pregunta_encuestaAux.get_descripcion());
                    preguntaAux.set_tipoPregunta(pregunta_encuestaAux.get_tipoPregunta());
                    preguntaAux.set_estado("A");
                }
                if (preguntaAux.get_tipoPregunta().equals("Seleccion simple")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaSimple(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaSimple());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        respuestaAux.set_valor(daoRespuestaCiclo.contarRespuestasSimples(respuestaCiclo));
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                if (preguntaAux.get_tipoPregunta().equals("Seleccion multiple")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaMultiple(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_respuestaMultiple());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        respuestaAux.set_valor(daoRespuestaCiclo.contarRespuestasMultiples(respuestaCiclo));
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                if (preguntaAux.get_tipoPregunta().equals("Verdadero o falso")){
                    DaoRespuesta daoRespuesta = new DaoRespuesta();
                    List<Respuesta> respuestas = daoRespuesta.getRespuestasAPreguntaVF(pregunta_estudio);
                    List<RespuestaAux> lista_interna = new ArrayList<RespuestaAux>();
                    for (Respuesta respuestaCiclo : respuestas) {
                        RespuestaAux respuestaAux = new RespuestaAux();
                        DaoRespuesta daoRespuestaCiclo = new DaoRespuesta();
                        respuestaAux.set_descripcion(respuestaCiclo.get_verdaderoFalso());
                        respuestaAux.set_estado(respuestaCiclo.get_estado());
                        respuestaAux.set_valor(daoRespuestaCiclo.contarRespuestasVF(respuestaCiclo));
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
                        List<Long> valorx = null;
                        respuestaAux.set_valor(valorx);
                        lista_interna.add(respuestaAux);
                    }
                    preguntaAux.set_listaRespuestas(lista_interna);
                }
                preguntas_salida.add(preguntaAux);
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return preguntas_salida;
    }


    @GET
    @Path("/listar/encuestados-realizar-encuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<UsuarioNoRespondieronEstudioResponse> getAllByUserNotReponse(@PathParam("id") long id) throws Exception {

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            String hql = "select distinct du.codigo , du.primerNombre , se.descripcionSolicitud, e.estatus, se.disponibilidadEnLinea from mercadeoucab.estudio as e, mercadeoucab.hijo as h, mercadeoucab.solicitud_estudio as se\n" +
                    "INNER JOIN mercadeoucab.dato_usuario as du ON se.fk_nivelEconomico = du.fk_nivelEconomico\n" +
                    "INNER JOIN mercadeoucab.region_estudio as re ON re.fk_lugar = du.fk_Lugar\n" +
                    "where\n" +
                    "du.sexo = se.generoPoblacional\n" +
                    "and (du.disponibilidadEnLinea =  se.disponibilidadEnLinea) \n" +
                    "and du.fk_ocupacion = se.fk_ocupacion\n" +
                    "and TIMESTAMPDIFF(YEAR,du.fechaNacimiento,CURDATE()) between se.edadMinimaPoblacion and se.edadMaximaPoblacion\n" +
                    "and se.conCuantasPersonasVive = du.conCuantasPersonasVive\n" +
                    "and ((select count(*) from mercadeoucab.hijo as h where h.fk_datoUsuario = du.codigo) = se.cantidadHijos )\n" +
                    "and e.fk_solicitudEstudio = du.codigo\n" +
                    "and e.fk_usuario =?1" ;

            Query query = entitymanager.createNativeQuery(hql);
            query.setParameter(1, id);
            List<Object[]> objects = query.getResultList();
            List<UsuarioNoRespondieronEstudioResponse> usuarioNoRespondieronEstudioResponses = new ArrayList<>();

            for (Object[] r : objects) {

                usuarioNoRespondieronEstudioResponses.add(new UsuarioNoRespondieronEstudioResponse((Integer) r[0],(String)r[1],(String)r[2],(String)r[3],(String)r[4]));

            }


            return usuarioNoRespondieronEstudioResponses;

        }catch (Exception e){

            throw  new Exception(e);

        }

    }
}
