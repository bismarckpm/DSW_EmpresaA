package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoProducto_tipo;
import ucab.dsw.dtos.Producto_tipoDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Producto_tipo;
import ucab.dsw.entidades.Tipo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Producto_tipoORMWS {

    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Producto_tipoDto addProducto_tipo(Producto_tipoDto producto_tipoDto, Producto producto )
    {
        Producto_tipoDto resultado = new Producto_tipoDto();
        try
        {
            DaoProducto_tipo dao = new DaoProducto_tipo();
            Producto_tipo producto_tipo = new Producto_tipo();
            producto_tipo.set_estado( "A" );

            producto_tipo.set_producto( producto);
            Tipo tipo = new Tipo(producto_tipoDto.getTipoDto().getId());
            producto_tipo.set_tipo(tipo);

            Producto_tipo resul = dao.insert( producto_tipo );
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
    public Producto_tipo consultarProducto_tipo(@PathParam("id") long id){

        DaoProducto_tipo categoriaDao = new DaoProducto_tipo();
        return categoriaDao.find(id, Producto_tipo.class);
    }

    @GET
    @Path("/buscar")
    public List<Producto_tipo> showProducto_tipo()
    {
        List<Producto_tipo> categorias = null;
        try {
            DaoProducto_tipo dao = new DaoProducto_tipo();
            categorias = dao.findAll(Producto_tipo.class);
            System.out.println("Categorias: ");
            for(Producto_tipo producto_tipo : categorias) {
                System.out.print(producto_tipo.get_id());
                System.out.print(", ");
                System.out.print(producto_tipo.get_estado());
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
    public Producto_tipoDto editProducto_tipo( Producto_tipoDto producto_tipoDto, Producto producto)
    {
        Producto_tipoDto resultado = new Producto_tipoDto();
        try
        {
            DaoProducto_tipo dao = new DaoProducto_tipo();
            Producto_tipo producto_tipo = new Producto_tipo(producto_tipoDto.getId());
            producto_tipo.set_estado (producto_tipoDto.getEstado());
            producto_tipo.set_producto( producto);
            Tipo tipo = new Tipo(producto_tipoDto.getTipoDto().getId());
            producto_tipo.set_tipo(tipo);
            Producto_tipo resul = dao.update (producto_tipo );
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
    public Producto_tipoDto deleteProducto_tipo( Producto_tipoDto producto_tipoDto)
    {
        Producto_tipoDto resultado = new Producto_tipoDto();
        try
        {
            DaoProducto_tipo dao = new DaoProducto_tipo();
            Producto_tipo producto_tipo = dao.find(producto_tipoDto.getId(), Producto_tipo.class);
            Producto_tipo resul = dao.delete (producto_tipo );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
