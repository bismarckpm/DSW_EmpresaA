package ucab.dsw.servicio;

import logica.comando.solicitud_estudio.AddSolicitud_estudioComando;
import logica.comando.solicitud_estudio.BuscarSolicitud_estudioComando;
import logica.comando.solicitud_estudio.ConsultarSolicitud_estudioComando;
import logica.comando.solicitud_estudio.EditSolicitud_estudioComando;
import logica.fabrica.Fabrica;
import org.junit.Assert;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.Response.TipoPregunta.ProductoSolicitudResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.SolicitudEstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path( "/solicitud_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Solicitud_estudioORMWS {

    /**
     * Este método registra en el sistema una nueva solicitud de estudio
     *
     * @param  solicitud_estudioDto  solicitud de estudio a ser registrada
     * @return      la solicitud_estudioDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    public Response addSolicitud_estudio(Solicitud_estudioDto solicitud_estudioDto) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddSolicitud_estudioComando comando = Fabrica.crearComandoConEntity(AddSolicitud_estudioComando.class, SolicitudEstudioMapper.mapDtoToEntityInsert(solicitud_estudioDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método consulta una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio a ser consultada
     * @return      la solicitud de estudio completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarSolicitud_estudio(@PathParam("id") long id) throws Exception{
        JsonObject resultado;
        try {
            ConsultarSolicitud_estudioComando comando=Fabrica.crearComandoConId(ConsultarSolicitud_estudioComando.class,id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna la lista con todas las solicitudes de estudio
     *
     * @return      la lista completa de solicitudes de estudio registradas
     */
    @GET
    @Path("/buscar")
    public Response showSolicitud_estudios() throws Exception{
        JsonObject resul;
        try {
            BuscarSolicitud_estudioComando comando= Fabrica.crear(BuscarSolicitud_estudioComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resul= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método retorna la lista de preguntas recomendada de la BD que no esten ya asignadas al estudio
     *
     * @param  "id"  id del estudio al cual se le quieren agregar pregunta
     * @return      una lista de preguntas para asignar al estudio
     */
    @GET
    @Path("/ProductoDeSolicitud/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<ProductoSolicitudResponse> obtenerProductoSolicitud(@PathParam("id") long idSolicitud) throws Exception {

        try {

            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            List<Object[]> Lista = daoSolicitud_estudio.ListarProductoSolicitud(idSolicitud);

            List<ProductoSolicitudResponse> ResponseListUpdate = new ArrayList<>(Lista.size());

            for (Object[] r : Lista) {
                ResponseListUpdate.add(new ProductoSolicitudResponse((Producto)r[0], (Marca)r[1], (Subcategoria) r[2], (Categoria)r[3]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw new ucab.dsw.excepciones.GetException( "Error consultando las preguntas recomendadas para un estudio");

        }

    }

    /**
     * Este método actualiza una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio que se va a actualizar
     * @param  solicitud_estudioDto  solicitud de estudio a ser actualizada
     * @return      la solicitud_estudioDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response updateSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            EditSolicitud_estudioComando comando=Fabrica.crearComandoBoth(EditSolicitud_estudioComando.class,solicitud_estudioDto.getId(),SolicitudEstudioMapper.mapDtoToEntityUpdate(solicitud_estudioDto.getId(),solicitud_estudioDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna las solicitudes de estudio de un cliente
     *
     * @param  id  id del cliente del cual se desea obtener sus solicitudes de estudio
     * @return      una lista de solicitudes de estudio pertenecientes a un cliente
     */
    @GET
    @Path("/showSolicitudUsuario/{id}")
    public List<Solicitud_estudio> showSolicitud_estudio_usuario(@PathParam("id") long id) throws Exception{
        List<Solicitud_estudio> solicitud_estudios = null;
        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            solicitud_estudios = dao.solicitudesCliente(id);
            System.out.println("Solicitud_estudios:");
            for (Solicitud_estudio solicitud_estudio : solicitud_estudios) {
                System.out.print(solicitud_estudio.get_id());
                System.out.print(", ");
            }

        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de solicitudes de estudio de un usuario");
        }
        return solicitud_estudios;
    }


    /**
     * Este método inactiva en el sistema una solicitud de estudio
     *
     * @param  id  id de solicitud de estudio a ser inativada
     * @param  solicitud_estudioDto  solicitud de estudio a ser inactivada
     * @return      la solicitud_estudioDto que ha sido inactivada en el sistema
     */
    @PUT
    @Path( "/inactivar/{id}" )
    public Solicitud_estudioDto inactivarSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto ) throws Exception
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            Solicitud_estudio solicitud_estudio = dao.find(id, Solicitud_estudio.class);
            solicitud_estudio.set_estado( "I" );
            Solicitud_estudio resul = dao.update( solicitud_estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error inactivando una solicitud de estudio");
        }
        return  resultado;
    }


}
