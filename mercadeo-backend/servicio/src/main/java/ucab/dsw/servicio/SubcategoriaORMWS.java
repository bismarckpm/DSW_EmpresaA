package ucab.dsw.servicio;

import logica.comando.subcategoria.AddSubcategoriaComando;
import logica.comando.subcategoria.BuscarSubcategoriaComando;
import logica.comando.subcategoria.ConsultarSubcategoriaComando;
import logica.comando.subcategoria.EditSubcategoriaComando;
import logica.fabrica.Fabrica;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.mappers.CategoriaMapper;
import ucab.dsw.mappers.SubcategoriaMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response addSubcategoria(SubcategoriaDto subcategoriaDto )
    {
        JsonObject resultado;
        try
        {
            AddSubcategoriaComando comando = Fabrica.crearComandoConEntity(AddSubcategoriaComando.class, SubcategoriaMapper.mapDtoToEntityUpdate(subcategoriaDto.getId(),subcategoriaDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        }
        catch (PersistenceException | DatabaseException ex){

            ex.printStackTrace();

            resultado= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje_soporte",ex.getMessage())
                    .add("mensaje","La categoria ya existe").build();

            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();

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
     * Este método consulta una subcategoría específica
     *
     * @param  id  id de la subcategoría a ser consultada
     * @return      la subcategoría completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Response consultarSubcategoria(@PathParam("id") long id){

        JsonObject resultado;
        try {
            ConsultarSubcategoriaComando comando=Fabrica.crearComandoConId(ConsultarSubcategoriaComando.class,id);
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
     * Este método retorna la lista con todas las subcategorías
     *
     * @return      la lista completa de subcategorías registradas
     */
    @GET
    @Path("/buscar")
    public Response showSubcategoria()
    {
        JsonObject resul;
        try {
            BuscarSubcategoriaComando comando= Fabrica.crear(BuscarSubcategoriaComando.class);
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
     * Este método actualiza una subcategoría específica
     *
     * @param  subcategoriaDto  subcategoría a ser actualizada
     * @return      la subcategoriaDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editSubcategoria( SubcategoriaDto subcategoriaDto) {
        JsonObject resultado;
        try {
            EditSubcategoriaComando comando = Fabrica.crearComandoBoth(EditSubcategoriaComando.class, subcategoriaDto.getId(),SubcategoriaMapper.mapDtoToEntityInsert(subcategoriaDto));
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PersistenceException | DatabaseException ex) {

            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado", "error")
                    .add("mensaje_soporte", ex.getMessage())
                    .add("mensaje", "La categoria ya existe").build();

            return Response.status(Response.Status.BAD_REQUEST).entity(resultado).build();

        } catch (Exception ex) {
            ex.printStackTrace();
            resultado = Json.createObjectBuilder()
                    .add("estado", "error")
                    .add("mensaje_soporte", ex.getMessage())
                    .add("mensaje", "Ha ocurrido un error con el servidor").build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resultado).build();
        }
    }
}
