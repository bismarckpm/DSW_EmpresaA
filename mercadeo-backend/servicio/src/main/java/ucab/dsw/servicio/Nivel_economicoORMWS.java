package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoNivel_economico;
import ucab.dsw.dtos.Nivel_economicoDto;
import ucab.dsw.entidades.Nivel_economico;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/nivelEconomico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Nivel_economicoORMWS {

    /**
     * Este método registra en el sistema un nuevo nivel económico
     *
     * @param  nivel_economicoDto  nivel económico a ser registrado
     * @return      el nivel_economicoDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Nivel_economicoDto addNivel_economico(Nivel_economicoDto nivel_economicoDto ) throws Exception
    {
        Nivel_economicoDto resultado = new Nivel_economicoDto();
        try
        {
            DaoNivel_economico dao = new DaoNivel_economico();
            Nivel_economico nivel_economico = new Nivel_economico();
            nivel_economico.set_nivel( nivel_economicoDto.getNivel() );
            nivel_economico.set_estado( nivel_economicoDto.getEstado() );
            Nivel_economico resul = dao.insert( nivel_economico );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando un nuevo nivel económico");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todas los niveles económicos
     *
     * @return      la lista completa de niveles económicos registrados
     */
    @GET
    @Path("/buscar")
    public List<Nivel_economico> showNivel_economico () throws  Exception
    {
        List<Nivel_economico> nivel_economicos = null;
        try {
            DaoNivel_economico dao = new DaoNivel_economico();
            nivel_economicos = dao.findAll(Nivel_economico.class);
            System.out.println("Niveles_economicos: ");
            for(Nivel_economico nivel_economico : nivel_economicos) {
                System.out.print(nivel_economico.get_id());
                System.out.print(", ");
                System.out.print(nivel_economico.get_nivel());
                System.out.print(", ");
                System.out.print(nivel_economico.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de niveles económicos");
        }
        return nivel_economicos;
    }

    /**
     * Este método actualiza un nivel económico específico
     *
     * @param  nivel_economicoDto  nivel económico a ser actualizado
     * @return      el nivel_economicoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Nivel_economicoDto editNivel_economico( Nivel_economicoDto nivel_economicoDto) throws Exception
    {
        Nivel_economicoDto resultado = new Nivel_economicoDto();
        try
        {
            DaoNivel_economico dao = new DaoNivel_economico();
            Nivel_economico nivel_economico = new Nivel_economico(nivel_economicoDto.getId());
            nivel_economico.set_nivel( nivel_economicoDto.getNivel());
            nivel_economico.set_estado (nivel_economicoDto.getEstado());
            Nivel_economico resul = dao.update (nivel_economico );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando un nivel económico");
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
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
