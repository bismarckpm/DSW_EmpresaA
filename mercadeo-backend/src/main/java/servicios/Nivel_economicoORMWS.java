package servicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import dtos.Nivel_economicoDto;
import daos.DaoNivel_economico;
import entidades.Nivel_economico;
import java.util.List;

@Path( "/nivelEconomico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Nivel_economicoORMWS {

    @PUT
    @Path( "/agregar" )
    public Nivel_economicoDto addNivel_economico(Nivel_economicoDto nivel_economicoDto )
    {
        Nivel_economicoDto resultado = new Nivel_economicoDto();
        try
        {
            DaoNivel_economico dao = new DaoNivel_economico();
            Nivel_economico nivel_economico = new Nivel_economico();
            nivel_economico.set_nivel( nivel_economicoDto.getNivel() );
            nivel_economico.set_estado( nivel_economicoDto.getEstado() );
            Nivel_economico resul = dao.insert( nivel_economico );
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
    public List<Nivel_economico> showNivel_economico()
    {
        List<Nivel_economico> nivel_economicos = null;
        try {
            DaoNivel_economico dao = new DaoNivel_economico();
            nivel_economicos = dao.findAll(Nivel_economico.class);
            System.out.println("Niveles_economicos: ");
            for(Nivel_economico nivel_economico : nivel_economicos) {
                System.out.print(nivel_economico.get_codigo());
                System.out.print(", ");
                System.out.print(nivel_economico.get_nivel());
                System.out.print(", ");
                System.out.print(nivel_economico.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return nivel_economicos;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public Nivel_economicoDto editNivel_economico( Nivel_economicoDto nivel_economicoDto)
    {
        Nivel_economicoDto resultado = new Nivel_economicoDto();
        try
        {
            DaoNivel_economico dao = new DaoNivel_economico();
            Nivel_economico nivel_economico = new Nivel_economico(nivel_economicoDto.getId());
            nivel_economico.set_nivel( nivel_economicoDto.getNivel());
            nivel_economico.set_estado (nivel_economicoDto.getEstado());
            Nivel_economico resul = dao.update (nivel_economico );
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
    public Nivel_economicoDto deleteNivel_economico( Nivel_economicoDto nivel_economicoDto)
    {
        Nivel_economicoDto resultado = new Nivel_economicoDto();
        try
        {
            DaoNivel_economico dao = new DaoNivel_economico();
            Nivel_economico nivel_economico = dao.find(nivel_economicoDto.getId(), Nivel_economico.class);
            Nivel_economico resul = dao.delete (nivel_economico );
            resultado.setId(resul.get_codigo());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
