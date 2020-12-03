package servicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import dtos.OcupacionDto;
import daos.DaoOcupacion;
import entidades.Ocupacion;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionORMWS {

    @PUT
    @Path( "/agregar" )
    public OcupacionDto addOcupacion(OcupacionDto ocupacionDto )
    {
        OcupacionDto resultado = new OcupacionDto();
        try
        {
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = new Ocupacion();
            ocupacion.set_nombre( ocupacionDto.getNombre() );
            ocupacion.set_estado( ocupacionDto.getEstado() );
            Ocupacion resul = dao.insert( ocupacion );
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
    public List<Ocupacion> showOcupacion()
    {
        List<Ocupacion> ocupacions = null;
        try {
            DaoOcupacion dao = new DaoOcupacion();
            ocupacions = dao.findAll(Ocupacion.class);
            System.out.println("Ocupaciones: ");
            for(Ocupacion ocupacion : ocupacions) {
                System.out.print(ocupacion.get_codigo());
                System.out.print(", ");
                System.out.print(ocupacion.get_nombre());
                System.out.print(", ");
                System.out.print(ocupacion.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return ocupacions;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public OcupacionDto editOcupacion( OcupacionDto ocupacionDto)
    {
        OcupacionDto resultado = new OcupacionDto();
        try
        {
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = new Ocupacion(ocupacionDto.getId());
            ocupacion.set_nombre( ocupacionDto.getNombre());
            ocupacion.set_estado (ocupacionDto.getEstado());
            Ocupacion resul = dao.update (ocupacion );
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
    public OcupacionDto deleteOcupacion( OcupacionDto ocupacionDto)
    {
        OcupacionDto resultado = new OcupacionDto();
        try
        {
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = dao.find(ocupacionDto.getId(), Ocupacion.class);
            Ocupacion resul = dao.delete (ocupacion );
            resultado.setId(resul.get_codigo());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
