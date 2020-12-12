package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoProducto_presentacion;
import ucab.dsw.dtos.Producto_presentacionDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Producto_presentacionORMWS {

    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Producto_presentacionDto addProducto_presentacion(Producto_presentacionDto producto_presentacionDto, Producto producto )
    {
        Producto_presentacionDto resultado = new Producto_presentacionDto();
        try
        {
            DaoProducto_presentacion dao = new DaoProducto_presentacion();
            Producto_presentacion producto_presentacion = new Producto_presentacion();
            producto_presentacion.set_estado( "A" );

            producto_presentacion.set_producto( producto);
            Presentacion presentacion = new Presentacion(producto_presentacionDto.getPresentacionDto().getId());
            producto_presentacion.set_presentacion(presentacion);

            Producto_presentacion resul = dao.insert( producto_presentacion );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path ("/consultar/{id}")
    public Producto_presentacion consultarProducto_presentacion(@PathParam("id") long id){

        DaoProducto_presentacion categoriaDao = new DaoProducto_presentacion();
        return categoriaDao.find(id, Producto_presentacion.class);
    }

    @GET
    @Path("/buscar")
    public List<Producto_presentacion> showProducto_presentacion()
    {
        List<Producto_presentacion> categorias = null;
        try {
            DaoProducto_presentacion dao = new DaoProducto_presentacion();
            categorias = dao.findAll(Producto_presentacion.class);
            System.out.println("Categorias: ");
            for(Producto_presentacion producto_presentacion : categorias) {
                System.out.print(producto_presentacion.get_id());
                System.out.print(", ");
                System.out.print(producto_presentacion.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return categorias;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public Producto_presentacionDto editProducto_presentacion( Producto_presentacionDto producto_presentacionDto, Producto producto)
    {
        Producto_presentacionDto resultado = new Producto_presentacionDto();
        try
        {
            DaoProducto_presentacion dao = new DaoProducto_presentacion();
            Producto_presentacion producto_presentacion = new Producto_presentacion(producto_presentacionDto.getId());
            producto_presentacion.set_estado (producto_presentacionDto.getEstado());
            producto_presentacion.set_producto( producto);
            Presentacion presentacion = new Presentacion(producto_presentacionDto.getPresentacionDto().getId());
            producto_presentacion.set_presentacion(presentacion);
            Producto_presentacion resul = dao.update (producto_presentacion );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path( "/borrar/{id}" )
    public Producto_presentacionDto deleteProducto_presentacion( Producto_presentacionDto producto_presentacionDto)
    {
        Producto_presentacionDto resultado = new Producto_presentacionDto();
        try
        {
            DaoProducto_presentacion dao = new DaoProducto_presentacion();
            Producto_presentacion producto_presentacion = dao.find(producto_presentacionDto.getId(), Producto_presentacion.class);
            Producto_presentacion resul = dao.delete (producto_presentacion );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
