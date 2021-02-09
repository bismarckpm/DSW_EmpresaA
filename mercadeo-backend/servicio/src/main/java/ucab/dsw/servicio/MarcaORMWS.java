package ucab.dsw.servicio;

import logica.comando.marca.AddMarcaComando;
import logica.comando.marca.BuscarMarcaComando;
import logica.comando.marca.ConsultarMarcaComando;
import logica.comando.marca.EditMarcaComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MarcaORMWS {

    private static Logger logger = LoggerFactory.getLogger(MarcaORMWS.class);

    /**
     * Este método registra en el sistema una nueva marca
     *
     * @param  marcaDto  marca a ser registrada
     * @return      la marcaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    public Response addMarca(MarcaDto marcaDto )
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega una marca");
        JsonObject resultado;
        try
        {
            AddMarcaComando comando = Fabrica.crearComandoConEntidad(AddMarcaComando.class, MarcaMapper.mapDtoToEntityInsert(marcaDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega una marca");
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
     * Este método retorna la lista con todas las marcas
     *
     * @return      la lista completa de marcas registradas
     */
    @GET
    @Path("/buscar")
    public Response showMarca()
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todas las marcas");
        JsonObject resul;
        try {
            BuscarMarcaComando comando= Fabrica.crear(BuscarMarcaComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todas las marcas");
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
     * Este método consulta una marca específica
     *
     * @param  id  id de la marca a ser consultada
     * @return      la marca completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarMarca(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta una marca");
        JsonObject resultado;
        try {
            ConsultarMarcaComando comando=Fabrica.crearComandoConId(ConsultarMarcaComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta una marca");
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
     * Este método actualiza una marca específica
     *
     * @param  marcaDto  marca a ser actualizada
     * @return     la marcaDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editMarca( MarcaDto marcaDto)
    {

        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza una marca");
        JsonObject resultado;
        try
        {
            EditMarcaComando comando=Fabrica.crearComandoConEntidad(EditMarcaComando.class, MarcaMapper.mapDtoToEntityUpdate(marcaDto.getId(),marcaDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza una marca");
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
}
