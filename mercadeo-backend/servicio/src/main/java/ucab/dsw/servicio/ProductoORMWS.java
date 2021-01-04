package ucab.dsw.servicio;

import org.junit.Assert;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/producto" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoORMWS {

    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public ProductoDto addProducto(ProductoDto productoDto )
    {
        ProductoDto resultado = new ProductoDto();
        try
        {
            DaoProducto dao = new DaoProducto();
            DaoMarca daoMarca = new DaoMarca();
            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            DaoUsuario daoUsuario = new DaoUsuario();

            Producto producto = new Producto();
            producto.set_nombre(productoDto.getNombre());
            producto.set_descripcion( productoDto.getDescripcion() );
            producto.set_estado( productoDto.getEstado() );
            Marca marca = daoMarca.find(productoDto.getMarcaDto().getId(), Marca.class);
            Subcategoria subcategoria = daoSubcategoria.find(productoDto.getSubcategoriaDto().getId(), Subcategoria.class);
            Usuario usuario = daoUsuario.find(productoDto.getUsuarioDto().getId(), Usuario.class);

            producto.set_usuario(usuario);
            producto.set_marca( marca);
            producto.set_subcategoria( subcategoria);
            Producto resul = dao.insert( producto );

            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteProducto/{id}")
    public ProductoDto deleteProducto (@PathParam("id") long id){
        ProductoDto resultado = new ProductoDto();

        try{
            DaoProducto dao = new DaoProducto();
            Producto producto = dao.find(id, Producto.class);
            if(producto != null){
                Producto result = dao.delete(producto);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path ("/consultar/{id}")
    public Producto consultarProducto(@PathParam("id") long id){

        DaoProducto productoDao = new DaoProducto();
        return productoDao.find(id, Producto.class);
    }

    @GET
    @Path("/buscar")
    public List<Producto> showProductos(){
        List<Producto> productos = null;
        try{
            DaoProducto dao = new DaoProducto();
            productos = dao.findAll(Producto.class);
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
                System.out.print(producto.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return productos;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public ProductoDto updateProducto( @PathParam("id") long id , ProductoDto productoDto )
    {
        ProductoDto resultado = new ProductoDto();
        try
        {
            DaoProducto dao = new DaoProducto();
            Producto producto = dao.find(id, Producto.class);
            producto.set_nombre( productoDto.getNombre() );
            producto.set_descripcion( productoDto.getDescripcion() );
            producto.set_estado( productoDto.getEstado() );

//            Marca marca = new Marca(productoDto.getMarcaDto().getId());
//            producto.set_marca( marca);
//
//            Subcategoria subcategoria = new Subcategoria(productoDto.getSubcategoriaDto().getId());
//            producto.set_subcategoria( subcategoria);
//
//            Usuario usuario = new Usuario(productoDto.getUsuarioDto().getId());
//            producto.set_usuario( usuario);

            DaoMarca daoMarca = new DaoMarca();
            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            DaoUsuario daoUsuario = new DaoUsuario();

            Marca marca = daoMarca.find(productoDto.getMarcaDto().getId(), Marca.class);
            Subcategoria subcategoria = daoSubcategoria.find(productoDto.getSubcategoriaDto().getId(), Subcategoria.class);
            Usuario usuario = daoUsuario.find(productoDto.getUsuarioDto().getId(), Usuario.class);
            producto.set_usuario(usuario);
            producto.set_marca( marca);
            producto.set_subcategoria( subcategoria);

            Producto resul = dao.update(producto);

            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista de productos de un cliente específico
     *
     * @param  id  id del cliente del cual se desea obtener sus productos
     * @return      la lista de productos pertenecientes a un cliente específico
     */
    @GET
    @Path("/productosCliente/{id}")
    public List<Producto> showProductosCliente(@PathParam("id") long id ){
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
                System.out.print(producto.get_subcategoria().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
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
    public Producto getProductoEstudio(@PathParam("id") long id){

        DaoEstudio dao = new DaoEstudio();
        Estudio estudio = dao.find(id, Estudio.class);
        Producto producto = estudio.get_solicitudEstudio().get_producto();
        System.out.println(producto.get_id());
        System.out.println(producto.get_descripcion());
        return producto;
    }

}
