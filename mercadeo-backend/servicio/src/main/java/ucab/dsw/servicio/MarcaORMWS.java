package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Tipo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MarcaORMWS {

    /**
     * Este método registra en el sistema una nueva marca
     *
     * @param  marcaDto  marca a ser registrada
     * @return      la marcaDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    public MarcaDto addMarca(MarcaDto marcaDto )
    {
        MarcaDto resultado = new MarcaDto();
        try
        {
            DaoMarca dao = new DaoMarca();
            Marca marca = new Marca();
            marca.set_nombre( marcaDto.getNombre() );
            marca.set_estado( marcaDto.getEstado() );
            Marca resul = dao.insert( marca );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista con todas las marcas
     *
     * @return      la lista completa de marcas registradas
     */
    @GET
    @Path("/buscar")
    public List<Marca> showMarca()
    {
        List<Marca> marcas = null;
        try {
            DaoMarca dao = new DaoMarca();
            marcas = dao.findAll(Marca.class);
            System.out.println("Marcas: ");
            for(Marca marca : marcas) {
                System.out.print(marca.get_id());
                System.out.print(", ");
                System.out.print(marca.get_nombre());
                System.out.print(", ");
                System.out.print(marca.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return marcas;
    }

    /**
     * Este método consulta una marca específica
     *
     * @param  id  id de la marca a ser consultada
     * @return      la marca completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Marca consultarMarca(@PathParam("id") long id){

        DaoMarca marcaDao = new DaoMarca();
        return marcaDao.find(id, Marca.class);
    }

    /**
     * Este método actualiza una marca específica
     *
     * @param  marcaDto  marca a ser actualizada
     * @return     la marcaDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public MarcaDto editMarca( MarcaDto marcaDto)
    {
        MarcaDto resultado = new MarcaDto();
        try
        {
            DaoMarca dao = new DaoMarca();
            Marca marca = new Marca(marcaDto.getId());
            marca.set_nombre( marcaDto.getNombre());
            marca.set_estado (marcaDto.getEstado());
            Marca resul = dao.update (marca );
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
    public MarcaDto deleteMarca( MarcaDto marcaDto)
    {
        MarcaDto resultado = new MarcaDto();
        try
        {
            DaoMarca dao = new DaoMarca();
            Marca marca = dao.find(marcaDto.getId(), Marca.class);
            Marca resul = dao.delete (marca );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
