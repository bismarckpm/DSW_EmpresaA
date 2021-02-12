package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubcategoriaORMWS {

    /**
     * Este método registra en el sistema una nueva subcategoría
     *
     * @param  subcategoriaDto  subcategoría a ser registrada
     * @return      la subcategoriaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public SubcategoriaDto addSubcategoria(SubcategoriaDto subcategoriaDto ) throws Exception
    {
        SubcategoriaDto resultado = new SubcategoriaDto();
        try
        {
            DaoSubcategoria dao = new DaoSubcategoria();
            Subcategoria subcategoria = new Subcategoria();
            DaoCategoria daocat = new DaoCategoria();
            subcategoria.set_nombre( subcategoriaDto.getNombre() );
            subcategoria.set_estado( "A" );
            subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
            Categoria categoria = daocat.find (subcategoriaDto.getCategoriaDto().getId(), Categoria.class);
            subcategoria.set_categoria( categoria);
            Subcategoria resul = dao.insert( subcategoria );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando una nueva subcategoría");
        }
        return  resultado;
    }

    /**
     * Este método consulta una subcategoría específica
     *
     * @param  id  id de la subcategoría a ser consultada
     * @return      la subcategoría completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Subcategoria consultarSubcategoria(@PathParam("id") long id) throws Exception{

        try {
            DaoSubcategoria categoriaDao = new DaoSubcategoria();
            return categoriaDao.find(id, Subcategoria.class);
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando una subcategoría");
        }
    }

    /**
     * Este método retorna la lista con todas las subcategorías
     *
     * @return      la lista completa de subcategorías registradas
     */
    @GET
    @Path("/buscar")
    public List<Subcategoria> showSubcategoria() throws Exception
    {
        List<Subcategoria> categorias = null;
        try {
            DaoSubcategoria dao = new DaoSubcategoria();
            categorias = dao.findAll(Subcategoria.class);
            System.out.println("Categorias: ");
            for(Subcategoria subcategoria : categorias) {
                System.out.print(subcategoria.get_id());
                System.out.print(", ");
                System.out.print(subcategoria.get_nombre());
                System.out.print(", ");
                System.out.print(subcategoria.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.GetException( "Error consultando la lista de subcategorías");
        }
        return categorias;
    }

    /**
     * Este método actualiza una subcategoría específica
     *
     * @param  subcategoriaDto  subcategoría a ser actualizada
     * @return      la subcategoriaDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public SubcategoriaDto editSubcategoria( SubcategoriaDto subcategoriaDto) throws Exception
    {
        SubcategoriaDto resultado = new SubcategoriaDto();
        try
        {
            DaoSubcategoria dao = new DaoSubcategoria();
            Subcategoria subcategoria = new Subcategoria(subcategoriaDto.getId());
            subcategoria.set_nombre( subcategoriaDto.getNombre());
            subcategoria.set_estado (subcategoriaDto.getEstado());
            subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
            Categoria categoria = new Categoria(subcategoriaDto.getCategoriaDto().getId());
            subcategoria.set_categoria( categoria);
            Subcategoria resul = dao.update (subcategoria );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando una subcategoría");
        }
        return  resultado;
    }

    /**
     * Este método elimina en el sistema una nueva subcategoría
     *
     * @param  subcategoriaDto  subcategoría a ser eliminada
     * @return      la subcategoriaDto que ha sido eliminada en el sistema
     */
    @DELETE
    @Path( "/borrar/{id}" )
    public SubcategoriaDto deleteSubcategoria( SubcategoriaDto subcategoriaDto)
    {
        SubcategoriaDto resultado = new SubcategoriaDto();
        try
        {
            DaoSubcategoria dao = new DaoSubcategoria();
            Subcategoria subcategoria = dao.find(subcategoriaDto.getId(), Subcategoria.class);
            Subcategoria resul = dao.delete (subcategoria );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }


}
