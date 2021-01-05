package ucab.dsw.servicio;

import org.junit.Assert;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.Producto_presentacion_tipoDto;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.Rol_privilegioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Rol;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolORMWS {

    /**
     * Este método registra en el sistema un nuevo rol
     *
     * @param  rolDto  rol a ser registrado
     * @return      el rolDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public RolDto addRol(RolDto rolDto) throws Exception
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
            throw new ucab.dsw.excepciones.CreateException( "Error agregando un nuevo rol");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todos los roles
     *
     * @return      la lista completa de roles registrados
     */
    @GET
    @Path("/buscar")
    public List<Rol> showRol() throws Exception
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

            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de roles");
        }
        return rols;
    }

    /**
     * Este método consulta un rol específico
     *
     * @param  id  id del rol a ser consultado
     * @return      el rol completo que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Rol consultarRol(@PathParam("id") long id) throws Exception{

        try {
            DaoRol rolDao = new DaoRol();
            return rolDao.find(id, Rol.class);
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando un rol");
        }
    }

    /**
     * Este método actualiza un rol específico
     *
     * @param  rolDto  rol a ser actualizado
     * @return      el rolDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public RolDto editRol( RolDto rolDto) throws Exception
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
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando un rol");
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
