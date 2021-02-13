package ucab.dsw.servicio;

import logica.comando.hijo.AddHijoComando;
import logica.comando.hijo.BuscarHijoComando;
import logica.comando.hijo.ConsultarHijoComando;
import logica.comando.hijo.EditHijoComando;
import logica.comando.telefono.AddTelefonoComando;
import logica.comando.telefono.BuscarTelefonoComando;
import logica.comando.telefono.ConsultarTelefonoComando;
import logica.comando.telefono.EditTelefonoComando;
import logica.fabrica.Fabrica;
import lombok.extern.java.Log;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.mappers.HijoMapper;
import ucab.dsw.mappers.TelefonoMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Log
@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoORMWS {

    /**
     * Este método registra en el sistema una lista de teléfonos de un usuario
     *
     * @param  telefonos lista de teléfonos a ser registrados
     * @return      el telefonoDto que ha sido registrado en el sistema
     */
    @POST
    @Path( "/addTelefono" )
    public Response addTelefono(List<TelefonoDto> telefonos ) throws Exception
    {
        try
        {
            AddTelefonoComando comando = Fabrica.crearComandoLista(AddTelefonoComando.class, TelefonoMapper.mapDtoToEntityInsert(telefonos));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando un nuevo teléfono");
        }
    }

    /**
     * Este método retorna la lista con todas los teléfonos registrados
     *
     * @return      la lista completa de teléfonos registrados
     */
    @GET
    @Path("/showTelefono")
    public Response showTelefonos() throws Exception{
        try{
            BuscarTelefonoComando comando= Fabrica.crear(BuscarTelefonoComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de teléfonos registrados");
        }
    }

    /**
     * Este método actualiza una lista de teléfonos
     *
     * @param  "telefonoDto"  teléfono a ser actualizado
     * @return      el telefonoDto que ha sido actualizado
     */
    @PUT
    @Path( "/updateTelefono" )
    public Response updateTelefono( List<TelefonoDto> telefonos) throws Exception
    {
        try
        {
            EditTelefonoComando comando = Fabrica.crearComandoLista(EditTelefonoComando.class, TelefonoMapper.mapDtoToEntityUpdate(telefonos));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando un teléfono");
        }
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
    public Response obtenerTelefonosUsuario(@PathParam("id") long idDatousuario) throws Exception {

        try {
            ConsultarTelefonoComando comando= Fabrica.crearComandoConId(ConsultarTelefonoComando.class, idDatousuario);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de teléfonos de un usuario");
        }
    }


}
