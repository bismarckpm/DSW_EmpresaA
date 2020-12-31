package ucab.dsw.servicio;

import ucab.dsw.Response.EstudioResponse;
import ucab.dsw.Response.ListaEncuestasE;
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
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public List<EstudioResponse> getAllByUser(@PathParam("id") long id) throws Exception {

        try {

            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudioList = dao.findAll(Estudio.class);
            List<EstudioResponse> estudioUpdate = new ArrayList<>();

            estudioList.stream().filter(i->(i.get_usuario().get_id() == id && i.get_estado().equals("A"))).collect(Collectors.toList()).forEach(i->{
                try {

                    estudioUpdate.add(new EstudioResponse(i.get_id(), i.get_nombre(),
                                        formatDateToString(i.get_fechaInicio()), formatDateToString(i.get_fechaFin()),
                                    i.get_estatus()));

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

            return estudioUpdate;

        }catch (Exception e){

            throw new Exception(e.getMessage());

        }

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
                    preguntaAux.set_enunciado(pregunta_estudio.get_pregunta());
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
                        List<Long> respaldoConteo = daoRespuestaCiclo.contarRespuestasSimples(respuestaCiclo);
                        respuestaAux.set_valor(respaldoConteo.get(0));
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
                        List<Long> respaldoConteo = daoRespuestaCiclo.contarRespuestasMultiples(respuestaCiclo);
                        respuestaAux.set_valor(respaldoConteo.get(0));
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
    @Path ("/obtenerRecomendaciones/{id}")
    public List<Estudio> obtenerRecomendaciones(@PathParam("id") long id){

        DaoEstudio dao = new DaoEstudio();
        List<Estudio> estudios = dao.obtenerRecomendaciones(id);
        System.out.println("Estudios recomendados:");
        for (Estudio estudioAux : estudios) {
            System.out.print(estudioAux.get_id());
            System.out.print(", ");
        }
        return estudios;
    }

    @GET
    @Path ("/getEstudiosUsuario/{id}")
    public List<Estudio> getEstudiosUsuario(@PathParam("id") long id){

        DaoEstudio dao = new DaoEstudio();
        List<Estudio> estudios = dao.getEstudiosUsuario(id);
        System.out.println("Estudios del usuario:");
        for (Estudio estudioAux : estudios) {
            System.out.print(estudioAux.get_id());
            System.out.print(", ");
        }
        return estudios;
    }

    @GET
    @Path ("/getEstudiosCliente/{id}")
    public List<Estudio> getEstudiosCliente(@PathParam("id") long id){

        DaoEstudio dao = new DaoEstudio();
        List<Estudio> estudios = dao.getEstudiosCliente(id);
        System.out.println("Estudios del cliente:");
        for (Estudio estudioAux : estudios) {
            System.out.print(estudioAux.get_id());
            System.out.print(", ");
        }
        return estudios;
    }

    @PUT
    @Path( "/addEstudioPorRecomendacion/{id}" )
    public EstudioDto addEstudioPorRecomendacion(@PathParam("id") long id_solicitud, EstudioDto estudioDto )
    {
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
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/estudiosRecomendados/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Object[]> obtenerEstudiosRecomendados(@PathParam("id") long idSolicitud) throws Exception{

        try {
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find (idSolicitud, Solicitud_estudio.class);

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            String hql = "SELECT e._id as idEstudio, e._estatus as estatus, e._nombre as nombre, " +
                    "e._fechaInicio as fechaI" +
                    " FROM Estudio as e, Solicitud_estudio as se WHERE e._solicitudEstudio._id = se._id and " +
                    " se._conCuantasPersonasVive=:PersonasVive and se._disponibilidadEnLinea=:disponibilidadLinea " +
                    "and :edadMinima >= se._edadMinimaPoblacion and :edadMaxima <= se._edadMaximaPoblacion " +
                    "and se._generoPoblacional = :genero and se._nivelEconomico._id = :nivelEconomico and " +
                    "se._ocupacion._id = :ocupacion and se._id <> :idSolicitud " +
                    "ORDER BY e._fechaInicio ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("PersonasVive", solicitud_estudio.get_conCuantasPersonasVive())
                    .setParameter("disponibilidadLinea", solicitud_estudio.get_disponibilidadEnLinea())
                    .setParameter("edadMinima", solicitud_estudio.get_edadMinimaPoblacion())
                    .setParameter("edadMaxima", solicitud_estudio.get_edadMaximaPoblacion())
                    .setParameter("genero", solicitud_estudio.get_generoPoblacional())
                    .setParameter("nivelEconomico", solicitud_estudio.get_nivelEconomico().get_id())
                    .setParameter("ocupacion", solicitud_estudio.get_ocupacion().get_id())
                    .setParameter("idSolicitud", idSolicitud);
            List<Object[]> estudios = query.getResultList();

            List<ListaEncuestasE> ResponseListUpdate = new ArrayList<>(estudios.size());

            for (Object[] r : estudios) {
                ResponseListUpdate.add(new ListaEncuestasE((long)r[0], (String)r[1], (String)r[2], (Date)r[3] ));
            }

            return estudios;

        }catch (Exception e){

            throw  new Exception(e);

        }
    }


}
