package ucab.dsw.servicio;

import org.junit.Assert;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoTipo;
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
    public ProductoDto addProducto(ProductoDto productoDto )
    {
        ProductoDto resultado = new ProductoDto();
        try
        {
            DaoProducto dao = new DaoProducto();
            Producto producto = new Producto();
            producto.set_nombre(productoDto.getNombre());
            producto.set_descripcion( productoDto.getDescripcion() );
            producto.set_estado( productoDto.getEstado() );
            Marca marca = new Marca(productoDto.getMarcaDto().getId());
            producto.set_marca( marca);
            Subcategoria subcategoria = new Subcategoria(productoDto.getSubcategoriaDto().getId());
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
            Marca marca = new Marca(productoDto.getMarcaDto().getId());
            producto.set_marca( marca);
            Subcategoria subcategoria = new Subcategoria(productoDto.getSubcategoriaDto().getId());
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
}
