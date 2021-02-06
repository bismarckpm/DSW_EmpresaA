package ucab.dsw.servicio;
import logica.comando.categoria.AddCategoriaComando;
import logica.comando.categoria.BuscarCategoriaComando;
import logica.comando.categoria.ConsultarCategoriaComando;
import logica.comando.categoria.EditCategoriaComando;
import logica.fabrica.Fabrica;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.mappers.CategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.faces.push.Push;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response addCategoria( CategoriaDto categoriaDto )
    {
        JsonObject resultado;
        try
        {
            AddCategoriaComando comando = Fabrica.crearComandoConEntity(AddCategoriaComando.class, CategoriaMapper.mapDtoToEntityInsert(categoriaDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método consulta una categoría específica
     *
     * @param  id  id de la categoría a ser consultada
     * @return      la categoría completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarCategoria(@PathParam("id") long id){
        JsonObject resultado;
        try {
            ConsultarCategoriaComando comando=Fabrica.crearComandoConId(ConsultarCategoriaComando.class,id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }

    /**
     * Este método retorna la lista con todas las categorías
     *
     * @return      la lista completa de categorías registradas
     */
    @GET
    @Path("/buscar")
    public Response showCategoria()
    {
        JsonObject resul;
        try {
            BuscarCategoriaComando comando= Fabrica.crear(BuscarCategoriaComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
            resul= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resul).build();
        }
    }

    /**
     * Este método actualiza una categoría específica
     *
     * @param  categoriaDto  categoría a ser actualizada
     * @return      la categoriaDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editCategoria( CategoriaDto categoriaDto)
    {
        JsonObject resultado;
        try
        {
            EditCategoriaComando comando=Fabrica.crearComandoBoth(EditCategoriaComando.class,categoriaDto.getId(),CategoriaMapper.mapDtoToEntityUpdate(categoriaDto.getId(),categoriaDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }
        catch (Exception ex){
            ex.printStackTrace();
            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }

    }

}
