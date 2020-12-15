package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoRol_privilegio;
import ucab.dsw.dtos.Rol_privilegioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Rol_privilegioORMWS {

    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Rol_privilegioDto addRol_privilegio(Rol_privilegioDto rol_privilegioDto, Rol rol )
    {
        Rol_privilegioDto resultado = new Rol_privilegioDto();
        try
        {
            DaoRol_privilegio dao = new DaoRol_privilegio();
            Rol_privilegio rol_privilegio = new Rol_privilegio();
            rol_privilegio.set_estado( "A" );

            Privilegio privilegio = new Privilegio(rol_privilegioDto.getPrivilegioDto().getId());
            rol_privilegio.set_privilegio( privilegio);
            rol_privilegio.set_rol( rol);

            Rol_privilegio resul = dao.insert( rol_privilegio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path ("/consultar/{id}")
    public Rol_privilegio consultarRol_privilegio(@PathParam("id") long id){

        DaoRol_privilegio categoriaDao = new DaoRol_privilegio();
        return categoriaDao.find(id, Rol_privilegio.class);
    }

    @GET
    @Path("/buscar")
    public List<Rol_privilegio> showRol_privilegio()
    {
        List<Rol_privilegio> categorias = null;
        try {
            DaoRol_privilegio dao = new DaoRol_privilegio();
            categorias = dao.findAll(Rol_privilegio.class);
            System.out.println("Categorias: ");
            for(Rol_privilegio rol_privilegio : categorias) {
                System.out.print(rol_privilegio.get_id());
                System.out.print(", ");
                System.out.print(rol_privilegio.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return categorias;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public Rol_privilegioDto editRol_privilegio( Rol_privilegioDto rol_privilegioDto, Rol rol )
    {
        Rol_privilegioDto resultado = new Rol_privilegioDto();
        try
        {
            DaoRol_privilegio dao = new DaoRol_privilegio();
            Rol_privilegio rol_privilegio = new Rol_privilegio(rol_privilegioDto.getId());
            rol_privilegio.set_estado (rol_privilegioDto.getEstado());
            Privilegio privilegio = new Privilegio(rol_privilegioDto.getPrivilegioDto().getId());
            rol_privilegio.set_privilegio( privilegio);
            rol_privilegio.set_rol( rol);
            Rol_privilegio resul = dao.update (rol_privilegio );
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
    public Rol_privilegioDto deleteRol_privilegio( Rol_privilegioDto rol_privilegioDto)
    {
        Rol_privilegioDto resultado = new Rol_privilegioDto();
        try
        {
            DaoRol_privilegio dao = new DaoRol_privilegio();
            Rol_privilegio rol_privilegio = dao.find(rol_privilegioDto.getId(), Rol_privilegio.class);
            Rol_privilegio resul = dao.delete (rol_privilegio );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
