package ucab.dsw.servicio;

import logica.comando.region_estudio.AddRegion_estudioComando;
import logica.comando.region_estudio.BuscarRegion_estudioComando;
import logica.comando.region_estudio.ConsultarRegion_estudioComando;
import logica.comando.region_estudio.EditRegion_estudioComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.RegionEstudioMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/region_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Region_estudioORMWS {

    private static Logger logger = LoggerFactory.getLogger(Region_estudioORMWS.class);

    /**
     * Este método registra en el sistema una región de estudio
     *
     * @param  region_estudioDto  región de estudio a ser registrada
     * @return      la region_estudioDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRegion_estudio(Region_estudioDto region_estudioDto ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una región de estudio");
        JsonObject resultado;
        try
        {
            AddRegion_estudioComando comando = Fabrica.crearComandoConEntidad(AddRegion_estudioComando.class, RegionEstudioMapper.mapDtoToEntityInsert(region_estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una región de estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método consulta una región de estudio específica
     *
     * @param  id  id de la región de estudio a ser consultada
     * @return      la región de estudio completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarRegion_estudio(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una región de estudio");

        JsonObject resultado;
        try {
            ConsultarRegion_estudioComando comando=Fabrica.crearComandoConId(ConsultarRegion_estudioComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una región de estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna la lista con todas las regiones de estudio de todas las solicitudes de estudio
     *
     * @return      la lista completa de regiones de estudio registradas
     */
    @GET
    @Path("/buscar")
    public Response showRegion_estudio() throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una región de estudio");
        JsonObject resul;
        try {
            BuscarRegion_estudioComando comando= Fabrica.crear(BuscarRegion_estudioComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta una región de estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método actualiza una región de estudio específica
     *
     * @param  region_estudioDto  región de estudio a ser actualizada
     * @return      la region_estudioDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editRegion_estudio( Region_estudioDto region_estudioDto ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una región de estudio");
        JsonObject resultado;
        try
        {
            EditRegion_estudioComando comando=Fabrica.crearComandoConEntidad(EditRegion_estudioComando.class,RegionEstudioMapper.mapDtoToEntityUpdate(region_estudioDto.getId(),region_estudioDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una región de estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método recibe una lista de lugares para registrar las regiones de estudio de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio a la que serán agregadas las regiones de estudio
     * @param  listaLugares lista de lugares con los que irá relacionada cada región de estudio
     * @return      la solicitud_estudioDto con la que ese relacionan las regiones de estudio agregadas
     */
    @POST
    @Path( "/addRegionesASolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto addLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaLugares) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega la lista de regiones de un estudio");
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoLugar daoLugar = new DaoLugar();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
            resultado.setId(solicitud_estudio.get_id());
            for (Region_estudioDto lugarAux : listaLugares) {
                Region_estudio region_estudio = new Region_estudio();
                region_estudio.set_estado( "A");
                Lugar lugar = daoLugar.find(lugarAux.getLugarDto().getId(), Lugar.class);
                region_estudio.set_lugar( lugar);
                region_estudio.set_solicitudEstudio(solicitud_estudio);
                Region_estudio resul = dao.insert( region_estudio );
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando la lista de regiones de estudio de una solicitud de estudio");
        }
        logger.debug("Saliendo del método que agrega la lista de regiones de un estudio");
        return  resultado;
    }

    /**
     * Este método retorna las regiones de estudio de una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio de la cual se desea obtener sus regiones de estudio
     * @return      la lista de lugares con los que se relacionan las regiones de estudio de una solicitud de estudio
     */
    @GET
    @Path("/getRegionesDeSolicitud/{id}")
    public List<Lugar> getRegionesDeSolicitud(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta las regiones de estudio de una solicitud");
        List<Lugar> lugares = null;
        try{
            DaoLugar dao = new DaoLugar();
            lugares = dao.getRegionesDeSolicitud(id);
            System.out.println("Regiones:");
            for (Lugar lugar : lugares) {
                System.out.print(lugar.get_id());
                System.out.print(", ");
            }

        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando las regiones de estudio de una solicitud de estudio");
        }
        logger.debug("Saliendo del método que consulta las regiones de estudio de una solicitud");
        return lugares;
    }

    /**
     * Este método actualiza las regiones de estudio de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio a la cual se le actualizarán las regiones de estudio
     * @param  listaLugares lista de lugares con los cuales se actualizarán las regiones de estudio de la solicitud
     * @return      la solicitud_estudioDto a la cual se le actualizaron sus regiones de estudio
     */
    @POST
    @Path( "/updateRegionesDeSolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto updateLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaLugares) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza la lista de regiones de una solicitud de estudio");
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            List<Region_estudio> regionesOld = dao.getRegionesActualizar(id);
            for (Region_estudio regAux : regionesOld) {
                Region_estudio resul = dao.delete (regAux );
            }
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoLugar daoLugar = new DaoLugar();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
            resultado.setId(solicitud_estudio.get_id());
            for (Region_estudioDto lugarAux : listaLugares) {
                Region_estudio region_estudio = new Region_estudio();
                region_estudio.set_estado( "A");
                Lugar lugar = daoLugar.find(lugarAux.getLugarDto().getId(), Lugar.class);
                region_estudio.set_lugar( lugar);
                region_estudio.set_solicitudEstudio(solicitud_estudio);
                Region_estudio resul = dao.insert( region_estudio );
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando la lista de regiones de estudio de una solicitud de estudio");
        }
        logger.debug("Saliendo del método que actualiza la lista de regiones de una solicitud de estudio");
        return  resultado;
    }
}
