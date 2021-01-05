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

    /**
     * Este método registra en el sistema una lista de hijos de un usuario
     *
     * @param  hijos lista de hijos a ser registrada en el sistema
     * @return      el hijoDto que ha sido registrado
     */
    @POST
    @Path( "/addHijo" )
    public HijoDto addHijo(List<HijoDto> hijos ) throws Exception
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
            throw new ucab.dsw.excepciones.CreateException( "Error agregando los hijos de un usuario");
        }
        return  resultado;
    }

    /**
     * Este método elimina en el sistema un hijo de un usuario
     *
     * @param  id de hijos a ser eliminado en el sistema
     * @return      el hijoDto que ha sido eliminado
     */
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

    /**
     * Este método retorna la lista con todos los hijos de usuarios registrados
     *
     * @return      la lista completa de hijos registrados
     */
    @GET
    @Path("/showHijo")
    public List<Hijo> showHijos() throws Exception{
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
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de hijos registrados");
        }
        return hijos;
    }

    /**
     * Este método actualiza la información de una lista de hijos
     *
     * @param  hijos  lista de hijos a ser actualizados
     * @return      el hijoDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateHijo" )
    public HijoDto updateHijo(List<HijoDto> hijos) throws Exception
    {
        HijoDto resultado = new HijoDto();
        try
        {
            DaoHijo dao = new DaoHijo();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (HijoDto hijoDto : hijos) {
                Hijo hijo = dao.find(hijoDto.getId(), Hijo.class);
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
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando los hijos de un usuario");
        }
        return  resultado;
    }

    /**
     * Este método obtiene la información de una lista de hijos de un usuario especifico
     *
     * @param  "id"  id usuario al cual se le buscaran los hijos
     * @return      la lista de hijos a obtener
     */
    @GET
    @Path("/HijosUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Hijo> obtenerHijosUsuario(@PathParam("id") long idDatousuario) throws Exception{

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormprueba");
            EntityManager entitymanager = factory.createEntityManager();

            List<Hijo> hijos = entitymanager.createQuery("SELECT h FROM Hijo as h WHERE h._datoUsuario._id = :id")
                    .setParameter("id", idDatousuario)
                    .getResultList();

            return hijos;
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando los hijos de un usuario");

        }

    }
}
