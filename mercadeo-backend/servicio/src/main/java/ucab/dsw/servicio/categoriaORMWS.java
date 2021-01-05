package ucab.dsw.servicio;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;

import javax.servlet.http.HttpServletRequest;
import javax.faces.push.Push;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path( "/categoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class categoriaORMWS {

    /**
     * Este método registra en el sistema una nueva categoría
     *
     * @param  categoriaDto  categoría a ser registrada
     * @return      la categoriaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public CategoriaDto addCategoria( CategoriaDto categoriaDto ) throws Exception
    {
        CategoriaDto resultado = new CategoriaDto();
        try
        {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();
            categoria.set_nombre( categoriaDto.getNombre() );
            categoria.set_estado( "A" );
            Categoria resul = dao.insert( categoria );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error agregando una nueva categoría");
        }
        return  resultado;
    }

    /**
     * Este método consulta una categoría específica
     *
     * @param  id  id de la categoría a ser consultada
     * @return      la categoría completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Categoria consultarCategoria(@PathParam("id") long id) throws Exception{

        try {
            DaoCategoria categoriaDao = new DaoCategoria();
            return categoriaDao.find(id, Categoria.class);
        }
        catch (Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando una categoría");
        }
    }

    /**
     * Este método retorna la lista con todas las categorías
     *
     * @return      la lista completa de categorías registradas
     */
    @GET
    @Path("/buscar")
    public List<Categoria> showCategoria() throws Exception
    {
        List<Categoria> categorias = null;
        try {
            DaoCategoria dao = new DaoCategoria();
            categorias = dao.findAll(Categoria.class);
            System.out.println("Categorias: ");
            for(Categoria categoria : categorias) {
                System.out.print(categoria.get_id());
                System.out.print(", ");
                System.out.print(categoria.get_nombre());
                System.out.print(", ");
                System.out.print(categoria.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.GetException( "Error consultando las categorías");
        }
        return categorias;
    }

    /**
     * Este método actualiza una categoría específica
     *
     * @param  categoriaDto  categoría a ser actualizada
     * @return      la categoriaDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public CategoriaDto editCategoria( CategoriaDto categoriaDto) throws Exception
    {
        CategoriaDto resultado = new CategoriaDto();
        try
        {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria(categoriaDto.getId());
            categoria.set_nombre( categoriaDto.getNombre());
            categoria.set_estado (categoriaDto.getEstado());
            Categoria resul = dao.update (categoria );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando una categoría");
        }
        return  resultado;
    }

    @DELETE
    @Path( "/borrar/{id}" )
    public CategoriaDto deleteCategoria( CategoriaDto categoriaDto)
    {
        CategoriaDto resultado = new CategoriaDto();
        try
        {
            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = dao.find(categoriaDto.getId(), Categoria.class);
            Categoria resul = dao.delete (categoria );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

}
