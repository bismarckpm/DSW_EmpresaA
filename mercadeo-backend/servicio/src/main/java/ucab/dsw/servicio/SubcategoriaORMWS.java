package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubcategoriaORMWS {
    
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public SubcategoriaDto addSubcategoria(SubcategoriaDto subcategoriaDto )
    {
        SubcategoriaDto resultado = new SubcategoriaDto();
        try
        {
            DaoSubcategoria dao = new DaoSubcategoria();
            Subcategoria subcategoria = new Subcategoria();
            subcategoria.set_nombre( subcategoriaDto.getNombre() );
            subcategoria.set_estado( "A" );
            subcategoria.set_descripcion( subcategoriaDto.getDescripcion() );
            Categoria categoria = new Categoria(subcategoriaDto.getCategoriaDto().getId());
            subcategoria.set_categoria( categoria);
            Subcategoria resul = dao.insert( subcategoria );
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
    public Subcategoria consultarSubcategoria(@PathParam("id") long id){

        DaoSubcategoria categoriaDao = new DaoSubcategoria();
        return categoriaDao.find(id, Subcategoria.class);
    }

    @GET
    @Path("/buscar")
    public List<Subcategoria> showSubcategoria()
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
            String problema = ex.getMessage();
        }
        return categorias;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public SubcategoriaDto editSubcategoria( SubcategoriaDto subcategoriaDto)
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
            String problema = ex.getMessage();
        }
        return  resultado;
    }

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
