package servicios;

import daos.DaoMarca;
import dtos.MarcaDto;
import entidades.Marca;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MarcaORMWS {

    @PUT
    @Path( "/agregar" )
    public MarcaDto addMarca(MarcaDto marcaDto )
    {
        MarcaDto resultado = new MarcaDto();
        try
        {
            DaoMarca dao = new DaoMarca();
            Marca marca = new Marca();
            marca.set_nombre( marcaDto.getNombre() );
            marca.set_estado( marcaDto.getEstado() );
            Marca resul = dao.insert( marca );
            resultado.setId( resul.get_codigo() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/buscar")
    public List<Marca> showMarca()
    {
        List<Marca> marcas = null;
        try {
            DaoMarca dao = new DaoMarca();
            marcas = dao.findAll(Marca.class);
            System.out.println("Marcas: ");
            for(Marca marca : marcas) {
                System.out.print(marca.get_codigo());
                System.out.print(", ");
                System.out.print(marca.get_nombre());
                System.out.print(", ");
                System.out.print(marca.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return marcas;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public MarcaDto editMarca( MarcaDto marcaDto)
    {
        MarcaDto resultado = new MarcaDto();
        try
        {
            DaoMarca dao = new DaoMarca();
            Marca marca = new Marca(marcaDto.getId());
            marca.set_nombre( marcaDto.getNombre());
            marca.set_estado (marcaDto.getEstado());
            Marca resul = dao.update (marca );
            resultado.setId(resul.get_codigo());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path( "/borrar/{id}" )
    public MarcaDto deleteMarca( MarcaDto marcaDto)
    {
        MarcaDto resultado = new MarcaDto();
        try
        {
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(marcaDto.getId(), Marca.class);
            Marca resul = dao.delete (marca );
            resultado.setId(resul.get_codigo());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
