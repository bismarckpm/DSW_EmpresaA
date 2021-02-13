package ucab.dsw.servicio;

import logica.comando.producto_presentacion_tipo.AddProducto_presentacion_tipoComando;
import logica.comando.producto_presentacion_tipo.BuscarProducto_presentacion_tipoComando;
import logica.comando.producto_presentacion_tipo.ConsultarProducto_presentacion_tipoComando;
import logica.comando.producto_presentacion_tipo.EditProducto_presentacion_tipoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.ProductoPresentacionTipoMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path( "/producto_tipo_presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Producto_presentacion_tipoORMWS {

    private static Logger logger = LoggerFactory.getLogger(Producto_presentacion_tipoORMWS.class);


    /**
     * Este método registra en el sistema nueva informacion de producto y presentacion a un producto
     *
     * @param  "Producto_presentacion_tipoDto"  Producto_presentacion_tipo a ser registrada
     * @return      la Producto_presentacion_tipoDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addProducto_presentacion_tipo(Producto_presentacion_tipoDto producto_presentacion_tipoDto ) throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un producto_presentación_tipo");
        JsonObject resultado;
        try
        {
            AddProducto_presentacion_tipoComando comando = Fabrica.crearComandoConEntidad(AddProducto_presentacion_tipoComando.class, ProductoPresentacionTipoMapper.mapDtoToEntityInsert(producto_presentacion_tipoDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un producto_presentación_tipo");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método lista en el sistema informacion de producto y presentacion de un producto especifico
     *
     * @param  "id"  id del producto
     * @return      la lista de Producto_presentacion_tipo que ha sido retornada
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarProducto_presentacion_tipo(@PathParam("id") long id) throws Exception{
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un producto_presentacion_tipo");

        JsonObject resultado;
        try {
            ConsultarProducto_presentacion_tipoComando comando=Fabrica.crearComandoConId(ConsultarProducto_presentacion_tipoComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un producto_presentacion_tipo");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método lista en el sistema toda la  informacion de producto y presentacion
     *
     * @return      la lista de Producto_presentacion_tipo que ha sido retornada
     */
    @GET
    @Path("/buscar")
    public Response showProducto_presentacion_tipo() throws Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los producto_presentacion_tipo");
        JsonObject resul;
        try {
            BuscarProducto_presentacion_tipoComando comando= Fabrica.crear(BuscarProducto_presentacion_tipoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los producto_presentacion_tipo");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resul = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método edita en el sistema nueva informacion de producto y presentacion a un producto
     *
     * @param  "Producto_presentacion_tipoDto"  Producto_presentacion_tipo a ser editada
     * @return      la Producto_presentacion_tipoDto que ha sido editado en el sistema
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editProducto_presentacion_tipo( Producto_presentacion_tipoDto producto_presentacion_tipoDto) throws  Exception
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un proudcto_presentacion_tipo");
        JsonObject resultado;
        try
        {
            EditProducto_presentacion_tipoComando comando=Fabrica.crearComandoConEntidad(EditProducto_presentacion_tipoComando.class, ProductoPresentacionTipoMapper.mapDtoToEntityUpdate(producto_presentacion_tipoDto.getId(),producto_presentacion_tipoDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un proudcto_presentacion_tipo");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch(CustomException ex){
            logger.error("Código de error: " + ex.getCodigo()+  ", Mensaje de error: " + ex.getMensaje());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado",ex.getCodigo())
                    .add("objeto","")
                    .add("mensaje",ex.getMensaje()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
        catch (Exception ex){
            logger.error("Código de error: 100"+  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado","100")
                    .add("objeto","")
                    .add("mensaje",ex.getMessage()).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }
}
