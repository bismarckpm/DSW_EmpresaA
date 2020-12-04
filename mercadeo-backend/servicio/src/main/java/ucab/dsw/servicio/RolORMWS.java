package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolORMWS {

    @PUT
    @Path( "/agregar" )
    public RolDto addRol(RolDto rolDto )
    {
        RolDto resultado = new RolDto();
        try
        {
            DaoRol dao = new DaoRol();
            Rol rol = new Rol();
            rol.set_nombre( rolDto.getNombre() );
            rol.set_estado( rolDto.getEstado() );
            rol.set_descripcion( rolDto.getDescripcion() );
            Rol resul = dao.insert( rol );
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
    public List<Rol> showRol()
    {
        List<Rol> rols = null;
        try {
            DaoRol dao = new DaoRol();
            rols = dao.findAll(Rol.class);
            System.out.println("Rols: ");
            for(Rol rol : rols) {
                System.out.print(rol.get_id());
                System.out.print(", ");
                System.out.print(rol.get_nombre());
                System.out.print(", ");
                System.out.print(rol.get_estado());
                System.out.println();
                System.out.print(rol.get_descripcion());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return rols;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public RolDto editRol( RolDto rolDto)
    {
        RolDto resultado = new RolDto();
        try
        {
            DaoRol dao = new DaoRol();
            Rol rol = new Rol(rolDto.getId());
            rol.set_nombre( rolDto.getNombre());
            rol.set_estado (rolDto.getEstado());
            rol.set_descripcion (rolDto.getDescripcion());
            Rol resul = dao.update (rol );
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
    public RolDto deleteRol( RolDto rolDto)
    {
        RolDto resultado = new RolDto();
        try
        {
            DaoRol dao = new DaoRol();
            Rol rol = dao.find(rolDto.getId(), Rol.class);
            Rol resul = dao.delete (rol );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
