package ucab.dsw.servicio;

import logica.comando.pregunta_estudio.ObtenerEnunciadoPreguntaComando;
import logica.comando.producto.*;
import logica.fabrica.Fabrica;
import org.junit.Assert;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.CustomException;
import ucab.dsw.mappers.ProductoMapper;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/producto" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoORMWS {

    private static Logger logger = LoggerFactory.getLogger(ProductoORMWS.class);

    /**
     * Este método registra en el sistema un nuevo prodcuto de un cliente
     *
     * @param  productoDto  el producto a ser registrado
     * @return      el productoDto que ha sido registrado en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addProducto(ProductoDto productoDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que agrega un producto");
        JsonObject resultado;
        try
        {
            AddProductoComando comando = Fabrica.crearComandoConEntidad(AddProductoComando.class, ProductoMapper.mapDtoToEntityInsert(productoDto));
            comando.execute();
            logger.debug("Saliendo del método que agrega un producto");
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
     * Este método consulta una producto específico
     *
     * @param  id  id del producto a ser consultado
     * @return      el producto completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarProducto(@PathParam("id") long id){
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta un producto");
        JsonObject resultado;
        try {
            ConsultarProductoComando comando=Fabrica.crearComandoConId(ConsultarProductoComando.class,id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un producto");
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
     * Este método retorna la lista con todos los proudctos registrados
     *
     * @return      la lista completa de productos registrados
     */
    @GET
    @Path("/buscar")
    public Response showProductos() {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta todos los productos");
        JsonObject resul;
        try {
            BuscarProductoComando comando= Fabrica.crear(BuscarProductoComando.class);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los productos");
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
     * Este método actualiza un producto específico
     *
     * @param  id  id del producto que se va a actualizar
     * @param  productoDto  producto a ser actualizado
     * @return      el productoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response updateProducto( @PathParam("id") long id , ProductoDto productoDto )
    {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que actualiza un producto");
        JsonObject resultado;
        try
        {
            EditProductoComando comando= Fabrica.crearComandoConEntidad(EditProductoComando.class, ProductoMapper.mapDtoToEntityUpdate(id,productoDto));
            comando.execute();
            logger.debug("Saliendo del método que actualiza un producto");
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
     * Este método retorna la lista de productos de un cliente específico
     *
     * @param  id  id del cliente del cual se desea obtener sus productos
     * @return      la lista de productos pertenecientes a un cliente específico
     */
    @GET
    @Path("/productosCliente/{id}")
    public Response showProductosCliente(@PathParam("id") long id ) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta los productos de un cliente");
        JsonObject resultado;
        try{
            ObtenerProductosClienteComando comando= Fabrica.crearComandoConId(ObtenerProductosClienteComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta los productos de un cliente");
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
     * Este método retorna el producto con el que se relaciona un estudio específico
     *
     * @param  id  id del estudio del cual se desea obtener su producto
     * @return      el producto con el que se relaciona un estudio específico
     */
    @GET
    @Path ("/getProductoEstudio/{id}")
    public Response getProductoEstudio(@PathParam("id") long id) {
        BasicConfigurator.configure();
        logger.debug("Entrando al método que consulta el producto con el que se relaciona un estudio");
        JsonObject resultado;
        try {
            ObtenerProductoEstudioComando comando= Fabrica.crearComandoConId(ObtenerProductoEstudioComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta el producto con el que se relaciona un estudio");
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
