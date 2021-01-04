package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HijoORMWS {

    @POST
    @Path( "/addHijo" )
    public HijoDto addHijo(List<HijoDto> hijos )
    {
        HijoDto resultado = new HijoDto();
        try
        {
            DaoHijo dao = new DaoHijo();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (HijoDto hijoDto : hijos) {
                Hijo hijo = new Hijo();
                hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
                hijo.set_genero(hijoDto.getGenero());
                hijo.set_estado(hijoDto.getEstado());
                Dato_usuario dato_usuario = daoDatoUsuario.find(hijoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
                hijo.set_datoUsuario(dato_usuario);
                Hijo resul = dao.insert(hijo);
                resultado.setId(resul.get_id());
            }

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteHijo/{id}")
    public HijoDto deleteHijo (@PathParam("id") long id){
        HijoDto resultado = new HijoDto();

        try{
            DaoHijo dao = new DaoHijo();
            Hijo hijo = dao.find(id, Hijo.class);
            if(hijo != null){
                Hijo result = dao.delete(hijo);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showHijo")
    public List<Hijo> showHijos(){
        List<Hijo> hijos = null;
        try{
            DaoHijo dao = new DaoHijo();
            hijos = dao.findAll(Hijo.class);
            System.out.println("Hijos:");
            for (Hijo hijo : hijos) {
                System.out.print(hijo.get_id());
                System.out.print(", ");
                System.out.print(hijo.get_fechaNacimiento());
                System.out.print(", ");
                System.out.print(hijo.get_genero());
                System.out.print(", ");
                System.out.print(hijo.get_estado());
                System.out.print(", ");
                System.out.print(hijo.get_datoUsuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return hijos;
    }

    @PUT
    @Path( "/updateHijo/{id}" )
    public HijoDto updateHijo( @PathParam("id") long id , List<HijoDto> hijos)
    {
        HijoDto resultado = new HijoDto();
        try
        {
            DaoHijo dao = new DaoHijo();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (HijoDto hijoDto : hijos) {
                Hijo hijo = dao.find(id, Hijo.class);
                hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
                hijo.set_genero(hijoDto.getGenero());
                hijo.set_estado(hijoDto.getEstado());
                Dato_usuario dato_usuario = daoDatoUsuario.find(hijoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
                hijo.set_datoUsuario(dato_usuario);
                Hijo resul = dao.update(hijo);
                resultado.setId(resul.get_id());
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/HijosUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Hijo> obtenerHijosUsuario(@PathParam("id") long idDatousuario) throws Exception {

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            List<Hijo> hijos = entitymanager.createQuery("SELECT h FROM Hijo as h WHERE h._datoUsuario._id = :id")
                    .setParameter("id", idDatousuario)
                    .getResultList();

            return hijos;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }
}
