package ucab.dsw.servicio;
import ucab.dsw.Response.ApiRestResponse;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.entidades.Tipo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/tipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TipoORMWS {

    private DaoTipo daoTipo = new DaoTipo();

    @POST
    @Path( "/agregar" )
    public TipoDto addTipo(TipoDto tipoDto )
    {
        TipoDto resultado = new TipoDto();
        try
        {
            Tipo tipo = new Tipo();
            tipo.set_nombre( tipoDto.getNombre() );
            tipo.set_descripcion( tipoDto.getDescripcion() );
            tipo.set_estado( tipoDto.getEstado() );
            Tipo resul = daoTipo.insert( tipo );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/buscar")
    public List<Tipo> showTipo()
    {
        List<Tipo> tipos = null;
        try {
            
            tipos = daoTipo.findAll(Tipo.class);
            System.out.println("Tipos: ");
            for(Tipo tipo : tipos) {
                System.out.print(tipo.get_id());
                System.out.print(", ");
                System.out.print(tipo.get_nombre());
                System.out.print(", ");
                System.out.print(tipo.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return tipos;
    }

    @GET
    @Path ("/consultar/{id}")
    public Tipo consultarTipo(@PathParam("id") long id){

        DaoTipo TipoDao = new DaoTipo();
        return TipoDao.find(id, Tipo.class);
    }


    @PUT
    @Path( "/actualizar/{id}" )
    public TipoDto editTipo( TipoDto tipoDto)
    {
        TipoDto resultado = new TipoDto();
        try
        {
            
            Tipo tipo = new Tipo(tipoDto.getId());
            tipo.set_nombre( tipoDto.getNombre());
            tipo.set_descripcion( tipoDto.getDescripcion() );
            tipo.set_estado( tipoDto.getEstado() );
            Tipo resul = daoTipo.update (tipo );
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
    public TipoDto deleteTipo( TipoDto tipoDto)
    {
        TipoDto resultado = new TipoDto();
        try
        {
            
            Tipo tipo = daoTipo.find(tipoDto.getId(), Tipo.class);
            Tipo resul = daoTipo.delete (tipo );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
