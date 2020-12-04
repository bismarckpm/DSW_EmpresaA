package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.entidades.Nivel_academico;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/nivelAcademico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Nivel_academicoORMWS {

    @PUT
    @Path( "/agregar" )
    public Nivel_academicoDto addNivel_academico(Nivel_academicoDto nivel_academicoDto )
    {
        Nivel_academicoDto resultado = new Nivel_academicoDto();
        try
        {
            DaoNivel_academico dao = new DaoNivel_academico();
            Nivel_academico nivel_academico = new Nivel_academico();
            nivel_academico.set_nivel( nivel_academicoDto.getNivel() );
            nivel_academico.set_estado( nivel_academicoDto.getEstado() );
            Nivel_academico resul = dao.insert( nivel_academico );
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
    public List<Nivel_academico> showNivel_academico()
    {
        List<Nivel_academico> nivel_academicos = null;
        try {
            DaoNivel_academico dao = new DaoNivel_academico();
            nivel_academicos = dao.findAll(Nivel_academico.class);
            System.out.println("Niveles_academicos: ");
            for(Nivel_academico nivel_academico : nivel_academicos) {
                System.out.print(nivel_academico.get_id());
                System.out.print(", ");
                System.out.print(nivel_academico.get_nivel());
                System.out.print(", ");
                System.out.print(nivel_academico.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return nivel_academicos;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public Nivel_academicoDto editNivel_academico( Nivel_academicoDto nivel_academicoDto)
    {
        Nivel_academicoDto resultado = new Nivel_academicoDto();
        try
        {
            DaoNivel_academico dao = new DaoNivel_academico();
            Nivel_academico nivel_academico = new Nivel_academico(nivel_academicoDto.getId());
            nivel_academico.set_nivel( nivel_academicoDto.getNivel());
            nivel_academico.set_estado (nivel_academicoDto.getEstado());
            Nivel_academico resul = dao.update (nivel_academico );
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
    public Nivel_academicoDto deleteNivel_academico( Nivel_academicoDto nivel_academicoDto)
    {
        Nivel_academicoDto resultado = new Nivel_academicoDto();
        try
        {
            DaoNivel_academico dao = new DaoNivel_academico();
            Nivel_academico nivel_academico = dao.find(nivel_academicoDto.getId(), Nivel_academico.class);
            Nivel_academico resul = dao.delete (nivel_academico );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
