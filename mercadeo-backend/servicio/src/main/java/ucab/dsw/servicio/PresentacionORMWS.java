package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentacionORMWS {

    @PUT
    @Path( "/addPresentacion" )
    public PresentacionDto addPresentacion(PresentacionDto presentacionDto )
    {
        PresentacionDto resultado = new PresentacionDto();
        try
        {
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = new Presentacion();
            presentacion.set_titulo( presentacionDto.getTitulo() );
            presentacion.set_caracteristicas( presentacionDto.getCaracteristicas() );
            presentacion.set_estado( "A" );
            Producto producto = new Producto(presentacionDto.getProductoDto().getId());
            presentacion.set_producto( producto);
            Presentacion resul = dao.insert( presentacion );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deletePresentacion/{id}")
    public PresentacionDto deletePresentacion (@PathParam("id") long id){
        PresentacionDto resultado = new PresentacionDto();

        try{
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find(id, Presentacion.class);
            if(presentacion != null){
                Presentacion result = dao.delete(presentacion);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showPresentacion")
    public List<Presentacion> showPresentaciones(){
        List<Presentacion> presentacions = null;
        try{
            DaoPresentacion dao = new DaoPresentacion();
            presentacions = dao.findAll(Presentacion.class);
            System.out.println("Presentaciones:");
            for (Presentacion presentacion : presentacions) {
                System.out.print(presentacion.get_id());
                System.out.print(", ");
                System.out.print(presentacion.get_titulo());
                System.out.print(", ");
                System.out.print(presentacion.get_caracteristicas());
                System.out.print(", ");
                System.out.print(presentacion.get_estado());
                System.out.print(", ");
                System.out.print(presentacion.get_producto().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return presentacions;
    }

    @GET
    @Path ("/consultar/{id}")
    public Presentacion consultarSubcategoria(@PathParam("id") long id){

        DaoPresentacion presentacionDao = new DaoPresentacion();
        return presentacionDao.find(id, Presentacion.class);
    }

    @PUT
    @Path( "/updatePresentacion/{id}" )
    public PresentacionDto updatePresentacion( @PathParam("id") long id , PresentacionDto presentacionDto)
    {
        PresentacionDto resultado = new PresentacionDto();
        try
        {
            DaoPresentacion dao = new DaoPresentacion();
            Presentacion presentacion = dao.find(id, Presentacion.class);
            presentacion.set_titulo( presentacionDto.getTitulo() );
            presentacion.set_caracteristicas( presentacionDto.getCaracteristicas() );
            presentacion.set_estado( "A" );
            Producto producto = new Producto(presentacionDto.getProductoDto().getId());
            presentacion.set_producto( producto);
            Presentacion resul = dao.update(presentacion);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
