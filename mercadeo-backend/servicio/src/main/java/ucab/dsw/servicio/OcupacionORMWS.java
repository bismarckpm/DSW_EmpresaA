package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionORMWS {

    /**
     * Este método registra en el sistema una nueva ocupación
     *
     * @param  ocupacionDto ocupación a ser registrada
     * @return      la ocupacionDto que ha sido registrada en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public OcupacionDto addOcupacion(OcupacionDto ocupacionDto ) throws Exception
    {
        OcupacionDto resultado = new OcupacionDto();
        try
        {
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = new Ocupacion();
            ocupacion.set_nombre( ocupacionDto.getNombre() );
            ocupacion.set_estado( ocupacionDto.getEstado() );
            Ocupacion resul = dao.insert( ocupacion );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando una nueva ocupación");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todas las ocupaciones
     *
     * @return      la lista completa de ocupaciones registradas
     */
    @GET
    @Path("/buscar")
    public List<Ocupacion> showOcupacion() throws  Exception
    {
        List<Ocupacion> ocupacions = null;
        try {
            DaoOcupacion dao = new DaoOcupacion();
            ocupacions = dao.findAll(Ocupacion.class);
            System.out.println("Ocupaciones: ");
            for(Ocupacion ocupacion : ocupacions) {
                System.out.print(ocupacion.get_id());
                System.out.print(", ");
                System.out.print(ocupacion.get_nombre());
                System.out.print(", ");
                System.out.print(ocupacion.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de ocupaciones");
        }
        return ocupacions;
    }

    /**
     * Este método actualiza una ocupación específica
     *
     * @param  ocupacionDto  ocupación a ser actualizada
     * @return      la ocupacionDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public OcupacionDto editOcupacion( OcupacionDto ocupacionDto) throws Exception
    {
        OcupacionDto resultado = new OcupacionDto();
        try
        {
            DaoOcupacion dao = new DaoOcupacion();
            Ocupacion ocupacion = new Ocupacion(ocupacionDto.getId());
            ocupacion.set_nombre( ocupacionDto.getNombre());
            ocupacion.set_estado (ocupacionDto.getEstado());
            Ocupacion resul = dao.update (ocupacion );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando una ocupación");
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
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
