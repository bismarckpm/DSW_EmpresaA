package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(HijoORMWS.class);

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
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega los hijos de un usuario");
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
            logger.debug("Saliendo del método que agrega los hijos de un usuario");
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando los hijos de un usuario");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todos los hijos de usuarios registrados
     *
     * @return      la lista completa de hijos registrados
     */
    @GET
    @Path("/showHijo")
    public List<Hijo> showHijos() throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los hijos registrados");
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
            logger.debug("Saliendo del método que consulta todos los hijos registrados");
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
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza los hijos de un usuario");
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
            logger.debug("Saliendo del método que actualiza los hijos de un usuario");
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

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los hijos de un usuario");
        try {
            DaoHijo daoHijo = new DaoHijo();
            List<Hijo> hijos = daoHijo.listarHijosUsuario(idDatousuario);
            logger.debug("Saliendo del método que consulta los hijos de un usuario");
            return hijos;
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando los hijos de un usuario");

        }

    }
}
