package ucab.dsw.servicio;

import org.junit.Assert;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoProducto_presentacion_tipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path( "/producto_tipo_presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Producto_presentacion_tipoORMWS {

    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Producto_presentacion_tipoDto addProducto_presentacion_tipo(Producto_presentacion_tipoDto producto_presentacion_tipoDto )
    {
        Producto_presentacion_tipoDto resultado = new Producto_presentacion_tipoDto();
        try
        {
            DaoProducto_presentacion_tipo dao = new DaoProducto_presentacion_tipo();
            
            DaoProducto daoProducto = new DaoProducto();
            DaoTipo daoTipo = new DaoTipo();
            DaoPresentacion daoPresentacion = new DaoPresentacion();

            Producto producto = daoProducto.find(producto_presentacion_tipoDto.getProductoDto().getId(), Producto.class);
            Tipo tipo = daoTipo.find(producto_presentacion_tipoDto.getTipoDto().getId(), Tipo.class);
            Presentacion presentacion = daoPresentacion.find(producto_presentacion_tipoDto.getPresentacionDto().getId(), Presentacion.class);
            Producto_presentacion_tipo producto_presentacion_tipo = new Producto_presentacion_tipo();

            producto_presentacion_tipo.set_estado( "A" );
            producto_presentacion_tipo.set_producto( producto);
            producto_presentacion_tipo.set_tipo(tipo);
            producto_presentacion_tipo.set_presentacion(presentacion);

            Producto_presentacion_tipo resul = dao.insert( producto_presentacion_tipo );
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
    public Producto_presentacion_tipo consultarProducto_presentacion_tipo(@PathParam("id") long id){

        DaoProducto_presentacion_tipo categoriaDao = new DaoProducto_presentacion_tipo();
        return categoriaDao.find(id, Producto_presentacion_tipo.class);
    }

    @GET
    @Path("/buscar")
    public List<Producto_presentacion_tipo> showProducto_presentacion_tipo()
    {
        List<Producto_presentacion_tipo> categorias = null;
        try {
            DaoProducto_presentacion_tipo dao = new DaoProducto_presentacion_tipo();
            categorias = dao.findAll(Producto_presentacion_tipo.class);
            System.out.println("Categorias: ");

            for(Producto_presentacion_tipo producto_presentacion_tipo : categorias) {
                System.out.print(producto_presentacion_tipo.get_id());
                System.out.print(", ");
                System.out.print(producto_presentacion_tipo.get_estado());
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
    public Producto_presentacion_tipoDto editProducto_presentacion_tipo( Producto_presentacion_tipoDto producto_presentacion_tipoDto)
    {
        Producto_presentacion_tipoDto resultado = new Producto_presentacion_tipoDto();
        try
        {
            DaoProducto_presentacion_tipo dao = new DaoProducto_presentacion_tipo();
            Producto_presentacion_tipo producto_presentacion_tipo = new Producto_presentacion_tipo(producto_presentacion_tipoDto.getId());
            producto_presentacion_tipo.set_estado (producto_presentacion_tipoDto.getEstado());

            Producto producto = new Producto(producto_presentacion_tipoDto.getProductoDto().getId());
            producto_presentacion_tipo.set_producto( producto);

            Tipo tipo = new Tipo(producto_presentacion_tipoDto.getTipoDto().getId());
            producto_presentacion_tipo.set_tipo(tipo);

            Presentacion presentacion = new Presentacion(producto_presentacion_tipoDto.getPresentacionDto().getId());
            producto_presentacion_tipo.set_presentacion(presentacion);

            Producto_presentacion_tipo resul = dao.update (producto_presentacion_tipo );
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
    public Producto_presentacion_tipoDto deleteProducto_presentacion_tipo( Producto_presentacion_tipoDto producto_presentacion_tipoDto)
    {
        Producto_presentacion_tipoDto resultado = new Producto_presentacion_tipoDto();
        try
        {
            DaoProducto_presentacion_tipo dao = new DaoProducto_presentacion_tipo();
            Producto_presentacion_tipo producto_presentacion_tipo = dao.find(producto_presentacion_tipoDto.getId(), Producto_presentacion_tipo.class);
            Producto_presentacion_tipo resul = dao.delete (producto_presentacion_tipo );
            resultado.setId(resul.get_id());
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
