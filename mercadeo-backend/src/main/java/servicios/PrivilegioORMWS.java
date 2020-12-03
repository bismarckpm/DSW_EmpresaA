package servicios;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import dtos.PrivilegioDto;
import daos.DaoPrivilegio;
import entidades.Privilegio;

import java.util.List;

@Path( "/privilegio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PrivilegioORMWS {

    @PUT
    @Path( "/agregar" )
    public PrivilegioDto addPrivilegio(PrivilegioDto privilegioDto )
    {
        PrivilegioDto resultado = new PrivilegioDto();
        try
        {
            DaoPrivilegio dao = new DaoPrivilegio();
            Privilegio privilegio = new Privilegio();
            privilegio.set_tipoAccion( privilegioDto.getTipoAccion() );
            privilegio.set_estado( privilegioDto.getEstado() );
            privilegio.set_descripcion( privilegioDto.getDescripcion() );
            Privilegio resul = dao.insert( privilegio );
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
    public List<Privilegio> showPrivilegio()
    {
        List<Privilegio> privilegios = null;
        try {
            DaoPrivilegio dao = new DaoPrivilegio();
            privilegios = dao.findAll(Privilegio.class);
            System.out.println("Privilegios: ");
            for(Privilegio privilegio : privilegios) {
                System.out.print(privilegio.get_codigo());
                System.out.print(", ");
                System.out.print(privilegio.get_tipoAccion());
                System.out.print(", ");
                System.out.print(privilegio.get_estado());
                System.out.println();
                System.out.print(privilegio.get_descripcion());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return privilegios;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public PrivilegioDto editPrivilegio( PrivilegioDto privilegioDto)
    {
        PrivilegioDto resultado = new PrivilegioDto();
        try
        {
            DaoPrivilegio dao = new DaoPrivilegio();
            Privilegio privilegio = new Privilegio(privilegioDto.getId());
            privilegio.set_tipoAccion( privilegioDto.getTipoAccion());
            privilegio.set_estado (privilegioDto.getEstado());
            privilegio.set_descripcion (privilegioDto.getDescripcion());
            Privilegio resul = dao.update (privilegio );
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
    public PrivilegioDto deletePrivilegio( PrivilegioDto privilegioDto)
    {
        PrivilegioDto resultado = new PrivilegioDto();
        try
        {
            DaoPrivilegio dao = new DaoPrivilegio();
            Privilegio privilegio = dao.find(privilegioDto.getId(), Privilegio.class);
            Privilegio resul = dao.delete (privilegio );
            resultado.setId(resul.get_codigo());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
