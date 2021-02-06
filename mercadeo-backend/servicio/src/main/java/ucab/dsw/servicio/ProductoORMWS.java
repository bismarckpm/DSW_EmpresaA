package ucab.dsw.servicio;

import logica.comando.producto.AddProductoComando;
import logica.comando.producto.BuscarProductoComando;
import logica.comando.producto.ConsultarProductoComando;
import logica.comando.producto.EditProductoComando;
import logica.fabrica.Fabrica;
import org.junit.Assert;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.mappers.ProductoMapper;

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
    public Response addProducto(ProductoDto productoDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            AddProductoComando comando = Fabrica.crearComandoConEntity(AddProductoComando.class, ProductoMapper.mapDtoToEntityInsert(productoDto));
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
     * Este método consulta una producto específico
     *
     * @param  id  id del producto a ser consultado
     * @return      el producto completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarProducto(@PathParam("id") long id) throws Exception{
        JsonObject resultado;
        try {
            ConsultarProductoComando comando=Fabrica.crearComandoConId(ConsultarProductoComando.class,id);
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
     * Este método retorna la lista con todos los proudctos registrados
     *
     * @return      la lista completa de productos registrados
     */
    @GET
    @Path("/buscar")
    public Response showProductos() throws  Exception{
        JsonObject resul;
        try {
            BuscarProductoComando comando= Fabrica.crear(BuscarProductoComando.class);
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
     * Este método actualiza un producto específico
     *
     * @param  id  id del producto que se va a actualizar
     * @param  productoDto  producto a ser actualizado
     * @return      el productoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response updateProducto( @PathParam("id") long id , ProductoDto productoDto ) throws Exception
    {
        JsonObject resultado;
        try
        {
            EditProductoComando comando= Fabrica.crearComandoBoth(EditProductoComando.class,productoDto.getId(), ProductoMapper.mapDtoToEntityUpdate(productoDto.getId(),productoDto));
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
     * Este método retorna la lista de productos de un cliente específico
     *
     * @param  id  id del cliente del cual se desea obtener sus productos
     * @return      la lista de productos pertenecientes a un cliente específico
     */
    @GET
    @Path("/productosCliente/{id}")
    public List<Producto> showProductosCliente(@PathParam("id") long id ) throws Exception{
        List<Producto> productos = null;
        try{
            DaoProducto dao = new DaoProducto();
            DaoUsuario daoU = new DaoUsuario();
            Usuario usuario = daoU.find(id, Usuario.class);
            productos = dao.getProductosCliente(usuario);
            System.out.println("Productos:");
            for (Producto producto : productos) {
                System.out.print(producto.get_id());
                System.out.print(", ");
                System.out.print(producto.get_nombre());
                System.out.print(", ");
                System.out.print(producto.get_descripcion());
                System.out.print(", ");
                System.out.print(producto.get_estado());
                System.out.print(", ");
                System.out.print(producto.get_marca().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los proudctos de un cliente");
        }
        return productos;
    }

    /**
     * Este método retorna el producto con el que se relaciona un estudio específico
     *
     * @param  id  id del estudio del cual se desea obtener su producto
     * @return      el producto con el que se relaciona un estudio específico
     */
    @GET
    @Path ("/getProductoEstudio/{id}")
    public Producto getProductoEstudio(@PathParam("id") long id) throws Exception{

        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            Producto producto = estudio.get_solicitudEstudio().get_producto();
            System.out.println(producto.get_id());
            System.out.println(producto.get_descripcion());
            return producto;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando el producto asignado a un estudio");
        }
    }

}
