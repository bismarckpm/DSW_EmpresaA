package ucab.dsw.servicio;

import logica.comando.nivel_academico.AddNivel_academicoComando;
import logica.comando.nivel_academico.BuscarNivel_academicoComando;
import logica.comando.nivel_academico.EditNivel_academicoComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.mappers.NivelAcademicoMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/nivelAcademico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Nivel_academicoORMWS {

    /**
     * Este método registra en el sistema un nuevo nivel académico
     *
     * @param  nivel_academicoDto  nivel académico a ser registrado
     * @return      el nivel_academicoDto que ha sido registrado en el sistema
     */
    @PUT
    @Path( "/agregar" )
    public Response addNivel_academico(Nivel_academicoDto nivel_academicoDto )
    {
        JsonObject resultado;
        try
        {
            AddNivel_academicoComando comando = Fabrica.crearComandoConEntity(AddNivel_academicoComando.class, NivelAcademicoMapper.mapDtoToEntityInsert(nivel_academicoDto));
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
     * Este método retorna la lista con todos los niveles académicos
     *
     * @return      la lista completa de niveles académicos registrados
     */
    @GET
    @Path("/buscar")
    public Response showNivel_academico()
    {
        JsonObject resul;
        try {
            BuscarNivel_academicoComando comando= Fabrica.crear(BuscarNivel_academicoComando.class);
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
     * Este método actualiza un nivel académico específico
     *
     * @param  nivel_academicoDto  nivel académico a ser actualizado
     * @return      el nivel_academicoDto que ha sido actualizado
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Response editNivel_academico( Nivel_academicoDto nivel_academicoDto)
    {
        JsonObject resultado;
        try
        {
            EditNivel_academicoComando comando= Fabrica.crearComandoBoth(EditNivel_academicoComando.class,nivel_academicoDto.getId(), NivelAcademicoMapper.mapDtoToEntityUpdate(nivel_academicoDto.getId(),nivel_academicoDto));
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
