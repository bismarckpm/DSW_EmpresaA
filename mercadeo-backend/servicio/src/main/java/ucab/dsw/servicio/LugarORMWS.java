package ucab.dsw.servicio;

import logica.comando.lugar.AddLugarComando;
import logica.comando.lugar.BuscarLugarComando;
import logica.comando.lugar.EditLugarComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import ucab.dsw.Response.ApiRestResponse;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.mappers.LugarMapper;
import ucab.dsw.entidades.Response.ApiRestResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path( "/lugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LugarORMWS {

    /**
     * Este método registra en el sistema un nuevo lugar
     *
     * @param  lugarDto  lugar a ser registrado
     * @return      el lugarDto que ha sido registrado
     */
    @PUT
    @Path( "/addlugar" )
    public Response addLugar(LugarDto lugarDto )
    {
        JsonObject resultado;
        try
        {
            AddLugarComando comando = Fabrica.crearComandoConEntidad(AddLugarComando.class, LugarMapper.mapDtoToEntityInsert(lugarDto));
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
     * Este método actualiza un lugar específico
     *
     * @param  lugarDto  lugar a ser actualizado
     * @param  id  id del lugar a ser actualizado
     * @return      el lugarDto que ha sido actualizado
     */
    @PUT
    @Path( "/updatelugar/{id}" )
    public Response updateLugar( @PathParam("id") long id , LugarDto lugarDto)
    {
        JsonObject resultado;
        try
        {
            EditLugarComando comando=Fabrica.crearComandoConEntidad(EditLugarComando.class,LugarMapper.mapDtoToEntityUpdate(id,lugarDto));
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

    private Logger logger = Logger.getLogger(LugarORMWS.class.getName());

    private DaoLugar daoLugar = new DaoLugar();


    /**
     * Este método retorna la lista con todos los lugares
     *
     * @return      la lista completa de lugares registrados
     */
    @GET
    @Path("/buscar")
    public Response getList() throws Exception {
        JsonObject resul;
        try {
            BuscarLugarComando comando= Fabrica.crear(BuscarLugarComando.class);
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
     * Este método retorna los lugares de tipo Estado
     *
     * @return      una lista de lugares de tipo Estado
     */
    @GET
    @Path("/getEstados")
    public List<Lugar> getEstados() throws Exception{
        List<Lugar> lugares = null;
        try{
            DaoLugar dao = new DaoLugar();
            lugares = dao.getEstados();
            System.out.println("Estados:");
            for (Lugar lugar : lugares) {
                System.out.print(lugar.get_id());
                System.out.print(", ");
            }

        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los lugares de tipo Estado");
        }
        return lugares;
    }


    /**
     * Este método retorna los lugares de tipo Municipio
     *
     * @return      una lista de lugares de tipo Municipio
     */
    @GET
    @Path("/getMunicipios")
    public List<Lugar> getMunicipios() throws Exception{
        List<Lugar> lugares = null;
        try{
            DaoLugar dao = new DaoLugar();
            lugares = dao.getMunicipios();
            System.out.println("Municipios:");
            for (Lugar lugar : lugares) {
                System.out.print(lugar.get_id());
                System.out.print(", ");
            }

        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los lugares de tipo Municipio");
        }
        return lugares;
    }

}
