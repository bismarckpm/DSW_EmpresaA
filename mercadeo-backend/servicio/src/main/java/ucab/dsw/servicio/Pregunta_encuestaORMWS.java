package ucab.dsw.servicio;

import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.PreguntaEncuestaResponse;
import ucab.dsw.Response.TipoPregunta.MultipleResponse;
import ucab.dsw.accesodatos.DaoPregunta_encuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Pregunta_encuestaDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path( "/pregunta_encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Pregunta_encuestaORMWS {

    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Pregunta_encuestaDto addPregunta_encuesta(Pregunta_encuestaDto pregunta_encuestaDto)
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            DaoUsuario daoUser = new DaoUsuario();
            DaoSubcategoria daoSub = new DaoSubcategoria();
            Pregunta_encuesta pregunta_encuesta = new Pregunta_encuesta();
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( "A" );
            Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.insert( pregunta_encuesta );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deletePregunta_encuesta/{id}")
    public Pregunta_encuestaDto deletePregunta_encuesta (@PathParam("id") long id){
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();

        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            if(pregunta_encuesta != null){
                Pregunta_encuesta result = dao.delete(pregunta_encuesta);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/show")
    public List<Pregunta_encuesta> showPregunta_encuestas(){
        List<Pregunta_encuesta> pregunta_encuestas = null;
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            pregunta_encuestas = dao.findAll(Pregunta_encuesta.class);
            System.out.println("Pregunta_encuestas:");
            for (Pregunta_encuesta pregunta_encuesta : pregunta_encuestas) {
                System.out.print(pregunta_encuesta.get_id());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_descripcion());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_tipoPregunta());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_usuario().get_id());
                System.out.print("");
                System.out.print(pregunta_encuesta.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return pregunta_encuestas;
    }

    @PUT
    @Path( "/update/{id}" )
    public Pregunta_encuestaDto updatePregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto)
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            DaoUsuario daoUser = new DaoUsuario();
            DaoSubcategoria daoSub = new DaoSubcategoria();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( pregunta_encuestaDto.getEstado() );
            Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path ("/consultar/{id}")
    public Pregunta_encuesta consultarPregunta_encuesta(@PathParam("id") long id){

        DaoPregunta_encuesta pregunta_encuestaDao = new DaoPregunta_encuesta();
        return pregunta_encuestaDao.find(id, Pregunta_encuesta.class);
    }

    @PUT
    @Path( "/inactivar/{id}" )
    public Pregunta_encuestaDto incativarPregunta_encuesta( @PathParam("id") long id , Pregunta_encuestaDto pregunta_encuestaDto)
    {
        Pregunta_encuestaDto resultado = new Pregunta_encuestaDto();
        try
        {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            DaoUsuario daoUser = new DaoUsuario();
            DaoSubcategoria daoSub = new DaoSubcategoria();
            Pregunta_encuesta pregunta_encuesta = dao.find(id, Pregunta_encuesta.class);
            pregunta_encuesta.set_descripcion( pregunta_encuestaDto.getDescripcion() );
            pregunta_encuesta.set_tipoPregunta( pregunta_encuestaDto.getTipoPregunta() );
            pregunta_encuesta.set_estado( "I" );
            Usuario usuario = daoUser.find (pregunta_encuestaDto.getUsuarioDto().getId(), Usuario.class);
            pregunta_encuesta.set_usuario( usuario);
            Subcategoria subcategoria = daoSub.find(pregunta_encuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
            pregunta_encuesta.set_subcategoria( subcategoria);
            Pregunta_encuesta resul = dao.update(pregunta_encuesta);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/listar/pregunta_multiple")
    public List<Pregunta_encuesta> getAllMultipleByIdUser(){
        try {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->(i.get_tipoPregunta().equals("Multiple"))).collect(Collectors.toList());

            return respuestaPreguntaList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }

    @GET
    @Path("/listar/todas/{name}")
    public List<PreguntaEncuestaResponse> getAll(@PathParam("name") String name){
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);
            List<PreguntaEncuestaResponse> preguntaEncuestaResponseList = new ArrayList<>();

            respuestaPreguntaList.stream().filter(i->(i.get_tipoPregunta().equals(name))).collect(Collectors.toList()).forEach(x->{
                preguntaEncuestaResponseList.add(new PreguntaEncuestaResponse(x.get_id(), x.get_descripcion(), x.get_tipoPregunta()) );
            });

            return preguntaEncuestaResponseList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }



    @GET
    @Path("/listar/pregunta_abierta")
    public List<Pregunta_encuesta> getAllAbiertaByIdUser(){
        try {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->(i.get_tipoPregunta().equals("Abierta"))).collect(Collectors.toList());

            return respuestaPreguntaList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }

    @GET
    @Path("/listar/pregunta_cerrada")
    public List<Pregunta_encuesta> getAllCerradaByIdUser(){
        try {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->(i.get_tipoPregunta().equals("Cerrada"))).collect(Collectors.toList());

            return respuestaPreguntaList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }

    @GET
    @Path("/listar/pregunta_escala")
    public List<Pregunta_encuesta> getAllEscalaByIdUser(){
        try {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->(i.get_tipoPregunta().equals("Escala"))).collect(Collectors.toList());

            return respuestaPreguntaList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }

    @GET
    @Path("/listar/pregunta_simple")
    public List<Pregunta_encuesta> getAllSimpleByIdUser(){
        try {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->(i.get_tipoPregunta().equals("Simple"))).collect(Collectors.toList());

            return respuestaPreguntaList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }

    @GET
    @Path("/listar/pregunta_verdadero_falso}")
    public List<Pregunta_encuesta> getAllVerdaderoFalsoByIdUser(){
        try {
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            List<Pregunta_encuesta> respuestaPreguntaList = dao.findAll(Pregunta_encuesta.class);

            respuestaPreguntaList = respuestaPreguntaList.stream().filter(i->( i.get_tipoPregunta().equals("VerdaderoFalso"))).collect(Collectors.toList());

            return respuestaPreguntaList;

        }catch (Exception e){
            String problema = e.getMessage();
        }
        return null;
    }



    @Path("/showConOpciones")
    public List<Pregunta_encuesta> showPregunta_encuestas_con_opciones(){
        List<Pregunta_encuesta> pregunta_encuestas = null;
        try{
            DaoPregunta_encuesta dao = new DaoPregunta_encuesta();
            pregunta_encuestas = dao.getConOpciones();
            System.out.println("Pregunta_encuestas:");
            for (Pregunta_encuesta pregunta_encuesta : pregunta_encuestas) {
                System.out.print(pregunta_encuesta.get_id());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_descripcion());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_tipoPregunta());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_estado());
                System.out.print(", ");
                System.out.print(pregunta_encuesta.get_usuario().get_id());
                System.out.print("");
                System.out.print(pregunta_encuesta.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return pregunta_encuestas;
    }
}
