package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log
@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoORMWS {

    private DaoTelefono daoTelefono = new DaoTelefono();

    private static Logger logger = LoggerFactory.getLogger(TelefonoORMWS.class);

    /**
     * Este método registra en el sistema una lista de teléfonos de un usuario
     *
     * @param  telefonos lista de teléfonos a ser registrados
     * @return      el telefonoDto que ha sido registrado en el sistema
     */
    @POST
    @Path( "/addTelefono" )
    public TelefonoDto addTelefono(List<TelefonoDto> telefonos ) throws Exception
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega los teléfonos de un usuario");
        TelefonoDto resultado = new TelefonoDto();
        try
        {
            DaoTelefono dao = new DaoTelefono();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (TelefonoDto telefonoDto : telefonos) {
                Telefono telefono = new Telefono();
                telefono.set_numero(telefonoDto.getNumero());
                telefono.set_estado(telefonoDto.getEstado());
                Dato_usuario dato_usuario = daoDatoUsuario.find(telefonoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
                telefono.set_datoUsuario(dato_usuario);
                Telefono resul = dao.insert(telefono);
                resultado.setId(resul.get_id());
            }

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando un nuevo teléfono");
        }
        logger.debug("Saliendo del método que agrega los teléfonos de un usuario");
        return  resultado;
    }

    /**
     * Este método retorna la lista con todas los teléfonos registrados
     *
     * @return      la lista completa de teléfonos registrados
     */
    @GET
    @Path("/showTelefono")
    public List<Telefono> showTelefonos() throws Exception{

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los teléfonos");
        List<Telefono> telefonos = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            telefonos = dao.findAll(Telefono.class);
            System.out.println("Telefonos:");
            for (Telefono telefono : telefonos) {
                System.out.print(telefono.get_id());
                System.out.print(", ");
                System.out.print(telefono.get_numero());
                System.out.print(", ");
                System.out.print(telefono.get_estado());
                System.out.print(", ");
                System.out.print(telefono.get_datoUsuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de teléfonos registrados");
        }
        logger.debug("Saliendo del método que consulta todos los teléfonos");
        return telefonos;
    }

    /**
     * Este método actualiza una lista de teléfonos
     *
     * @param  "telefonoDto"  teléfono a ser actualizado
     * @return      el telefonoDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateTelefono" )
    public TelefonoDto updateTelefono( List<TelefonoDto> telefonos) throws Exception
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un teléfono");
        TelefonoDto resultado = new TelefonoDto();
        try
        {
            DaoTelefono dao = new DaoTelefono();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (TelefonoDto telefonoDto : telefonos) {
                Telefono telefono = dao.find(telefonoDto.getId(), Telefono.class);
                telefono.set_numero(telefonoDto.getNumero());
                telefono.set_estado(telefonoDto.getEstado());
                Dato_usuario dato_usuario = daoDatoUsuario.find(telefonoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
                telefono.set_datoUsuario(dato_usuario);
                Telefono resul = dao.update(telefono);
                resultado.setId(resul.get_id());
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando un teléfono");
        }
        logger.debug("Saliendo del método que actualiza un teléfono");
        return  resultado;
    }

    /**
     * Este método obtiene la información de una lista de telefonos de un usuario especifico
     *
     * @param  "id"  id usuario al cual se le buscaran los telefonos
     * @return      la lista de telefonos a obtener
     */
    @GET
    @Path("/TelefonosUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Telefono> obtenerTelefonosUsuario(@PathParam("id") long idDatousuario) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los teléfonos de un usuario");

        try {
            DaoTelefono daoTelefono = new DaoTelefono();
            List<Telefono> telefonos = daoTelefono.listarTelefonosUsuario(idDatousuario);
            logger.debug("Saliendo del método que consulta los teléfonos de un usuario");
            return telefonos;
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de teléfonos de un usuario");

        }

    }


}
