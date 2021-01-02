package ucab.dsw.servicio;

//import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.Response.TipoPregunta.*;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoPregunta_estudio;
import ucab.dsw.accesodatos.DaoRespuesta_pregunta;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.dtos.Pregunta_estudioDto;
import ucab.dsw.dtos.Respuesta_preguntaDto;
import ucab.dsw.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path( "/pregunta_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_estudioORMWS {

    @POST
    @Path( "/addPregunta_estudio" )
    public Pregunta_estudioDto addPregunta_estudio(Pregunta_estudioDto pregunta_estudioDto )
    {
        Pregunta_estudioDto resultado = new Pregunta_estudioDto();
        try
        {
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
            pregunta_estudio.set_estado( pregunta_estudioDto.getEstado() );
            pregunta_estudio.set_pregunta(pregunta_estudioDto.getPregunta());
            Estudio estudio = new Estudio(pregunta_estudioDto.getEstudioDto().getId());
            pregunta_estudio.set_estudio( estudio);
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta(pregunta_estudioDto.getPreguntaEncuestaDto().getId());
            pregunta_estudio.set_preguntaEncuesta( pregunta_encuesta);
            Pregunta_estudio resul = dao.insert( pregunta_estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deletePregunta_estudio/{id}")
    public Pregunta_estudioDto deletePregunta_estudio (@PathParam("id") long id){
        Pregunta_estudioDto resultado = new Pregunta_estudioDto();

        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            Pregunta_estudio pregunta_estudio = dao.find(id, Pregunta_estudio.class);
            if(pregunta_estudio != null){
                Pregunta_estudio result = dao.delete(pregunta_estudio);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showPregunta_estudio")
    public List<Pregunta_estudio> showPregunta_estudios(){
        List<Pregunta_estudio> pregunta_estudios = null;
        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            pregunta_estudios = dao.findAll(Pregunta_estudio.class);
            System.out.println("Pregunta_estudios:");
            for (Pregunta_estudio pregunta_estudio : pregunta_estudios) {
                System.out.print(pregunta_estudio.get_id());
                System.out.print(", ");
                System.out.print(pregunta_estudio.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_estudio.get_estudio().get_id());
                System.out.print("");
                System.out.print(pregunta_estudio.get_preguntaEncuesta().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return pregunta_estudios;
    }

    @GET
    @Path("/mostrarPregunta_estudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<PreguntasResponse> obtenerPreguntasDeEstudio(@PathParam("id") long idEstudio) throws Exception {

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();


            String hql = "select pt._id as idPreguntaEncuesta, pt._pregunta as pregunta , pe._tipoPregunta as tipoPregunta" +
                    " from Pregunta_encuesta as pe, Pregunta_estudio as pt WHERE " +
                    "pe._id = pt._preguntaEncuesta._id and pt._estudio._id =: id ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", idEstudio);
            List<Object[]> preguntas = query.getResultList();

            List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntas.size());

            for (Object[] r : preguntas) {
                ResponseListUpdate.add(new PreguntasResponse((long)r[0], (String)r[1], (String)r[2]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @GET
    @Path("/preguntasGenerales/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<PreguntasResponse> obtenerPreguntasGenerales(@PathParam("id") long idestudio) throws Exception {

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta" +
                    " from Pregunta_encuesta as pe where " +
                    "pe._id not in (select pt._preguntaEncuesta._id from Pregunta_estudio as pt where pt._estudio._id =: id) " +
                    "ORDER BY pe._id ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", idestudio);
            List<Object[]> preguntas_respuestas = query.getResultList();

            List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new PreguntasResponse((long)r[0], (String)r[1], (String)r[2]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @GET
    @Path("/preguntasRecomendadas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<PreguntasResponse> obtenerPreguntasRecomendadas(@PathParam("id") long idestudio) throws Exception {

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            String hql = "select pe._id as idPreguntaEncuesta, pe._descripcion as descripcion , pe._tipoPregunta as tipoPregunta" +
                    " from Pregunta_encuesta as pe where " +
                    "pe._id not in (select pt._preguntaEncuesta._id from Pregunta_estudio as pt where pt._estudio._id =: id) and " +
                    "pe._subcategoria._id = (select pr._subcategoria._id from Estudio as e, Solicitud_estudio as se, " +
                    "Producto as pr where e._id =: id and e._solicitudEstudio._id = se._id and se._producto._id = pr._id) " +
                    "ORDER BY pe._id ";
            Query query = entitymanager.createQuery( hql);
            query.setParameter("id", idestudio);
            List<Object[]> preguntas_respuestas = query.getResultList();

            List<PreguntasResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new PreguntasResponse((long)r[0], (String)r[1], (String)r[2]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @PUT
    @Path( "/updatePregunta_estudio/{id}" )
    public Pregunta_estudioDto updatePregunta_estudio( @PathParam("id") long id , Pregunta_estudioDto pregunta_estudioDto)
    {
        Pregunta_estudioDto resultado = new Pregunta_estudioDto();
        try
        {
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            Pregunta_estudio pregunta_estudio = dao.find(id, Pregunta_estudio.class);
            pregunta_estudio.set_estado( pregunta_estudioDto.getEstado() );
            pregunta_estudio.set_pregunta(pregunta_estudioDto.getPregunta());
            Estudio estudio = new Estudio(pregunta_estudioDto.getEstudioDto().getId());
            pregunta_estudio.set_estudio( estudio);
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta(pregunta_estudioDto.getPreguntaEncuestaDto().getId());
            pregunta_estudio.set_preguntaEncuesta( pregunta_encuesta);
            Pregunta_estudio resul = dao.update(pregunta_estudio);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /*@GET
    @Path("/obtener-encuesta/{id}")
    public EncuestaResponse getEncuesta(@PathParam("id") long id){
        try {
            DaoPregunta_estudio daoPreguntaEstudio = new DaoPregunta_estudio();
            List<Pregunta_estudio> preguntaEstudioList = daoPreguntaEstudio.findAll(Pregunta_estudio.class);

            List<AbiertaResponse> abiertaResponseList = new ArrayList<>();
            List<CerradaResponse> cerradaResponseList = new ArrayList<>();
            List<VerdaderoFalseResponse> verdaderoFalseResponseList = new ArrayList<>();
            List<MultipleResponse> multipleResponseList = new ArrayList<>();
            List<SimpleResponse> simpleResponseList = new ArrayList<>();
            List<EscalaResponse> escalaResponseList = new ArrayList<>();

            preguntaEstudioList.stream().filter(i->(i.get_estudio().get_id() == id)).collect(Collectors.toList()).forEach(i->{

                if(i.get_preguntaEncuesta().get_tipoPregunta().equals("Escala")) { escalaResponseList.add(new EscalaResponse(i.get_preguntaEncuesta().get_descripcion()));}
                if(i.get_preguntaEncuesta().get_tipoPregunta().equals("Abierta")) { abiertaResponseList.add(new AbiertaResponse(i.get_preguntaEncuesta().get_descripcion()));}
                if(i.get_preguntaEncuesta().get_tipoPregunta().equals("Cerrada")) { cerradaResponseList.add(new CerradaResponse(i.get_preguntaEncuesta().get_descripcion()));}
                if(i.get_preguntaEncuesta().get_tipoPregunta().equals("VerdaderoFalso")) { verdaderoFalseResponseList.add(new VerdaderoFalseResponse(i.get_preguntaEncuesta().get_descripcion()));}
                if(i.get_preguntaEncuesta().get_tipoPregunta().equals("Multiple")) { multipleResponseList.add(new MultipleResponse(i.get_preguntaEncuesta().get_descripcion(), getAllByIdEncuesta(i.get_preguntaEncuesta().get_id())));}
                if(i.get_preguntaEncuesta().get_tipoPregunta().equals("Simple")) { simpleResponseList.add(new SimpleResponse(i.get_preguntaEncuesta().get_descripcion(), getAllByIdEncuesta(i.get_preguntaEncuesta().get_id())));}

            });

            return new EncuestaResponse(verdaderoFalseResponseList, abiertaResponseList, cerradaResponseList, escalaResponseList, multipleResponseList, simpleResponseList );

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }*/

    private List<SolucionResponse> getAllByIdEncuesta(long idRespuesta){
        DaoRespuesta_pregunta daoRespuestaPregunta = new DaoRespuesta_pregunta();
        List<Respuesta_pregunta> respuestaPreguntaList = daoRespuestaPregunta.findAll(Respuesta_pregunta.class);

        List<SolucionResponse> solucionResponseList = new ArrayList<>();

        respuestaPreguntaList.stream().filter(i->(i.get_preguntaEncuesta().get_id() == idRespuesta))
                .collect(Collectors.toList()).forEach(i->{
                    solucionResponseList.add(new SolucionResponse(i.get_nombre(),i.get_id(), "0"));
        });

        return solucionResponseList;
    }

    @GET
    @Path("/getEnunciadoPregunta/{id}")
    public List<Pregunta_encuesta> getEnunciadoPregunta(@PathParam("id") long id){
        List<Pregunta_encuesta> pregunta_encuesta = null;
        try{
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
            pregunta_encuesta = daoPregunta_encuesta.getEnunciadoPregunta(dao.find(id, Pregunta_estudio.class));
            System.out.println("Pregunta_encuestas:");
            for (Pregunta_encuesta pregunta_encuestax : pregunta_encuesta) {
                System.out.print(pregunta_encuestax.get_id());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_descripcion());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_tipoPregunta());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_encuestax.get_usuario().get_id());
                System.out.print("");
                System.out.print(pregunta_encuestax.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return pregunta_encuesta;
    }

    @PUT
    @Path( "/addListaPreguntasEstudio/{id}" )
    public EstudioDto addListaPreguntasEstudio(@PathParam("id") long id_estudio, List<Pregunta_encuestaDto> listaPregunta_encuestaDto)
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoPregunta_estudio dao = new DaoPregunta_estudio();
            DaoEstudio daoEstudio = new DaoEstudio();
            Estudio estudio = daoEstudio.find(id_estudio, Estudio.class);
            resultado.setId(estudio.get_id());
            for (Pregunta_encuestaDto pregunta_encuestaAux : listaPregunta_encuestaDto) {
                Pregunta_estudio pregunta_estudio = new Pregunta_estudio();
                pregunta_estudio.set_pregunta( pregunta_encuestaAux.getDescripcion() );
                pregunta_estudio.set_estado( "A" );
                DaoPregunta_encuesta daoPregunta_encuesta = new DaoPregunta_encuesta();
                Pregunta_encuesta pregAux = daoPregunta_encuesta.find(pregunta_encuestaAux.getId(), Pregunta_encuesta.class);
                pregunta_estudio.set_preguntaEncuesta( pregAux);
                pregunta_estudio.set_estudio(estudio);
                Pregunta_estudio resul = dao.insert( pregunta_estudio );
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

}
