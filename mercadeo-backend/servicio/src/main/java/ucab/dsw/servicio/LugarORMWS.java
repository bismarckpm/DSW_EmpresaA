package ucab.dsw.servicio;

import ucab.dsw.Response.ApiRestResponse;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.entidades.Lugar;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path( "/lugares" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LugarORMWS {

    private Logger logger = Logger.getLogger(LugarORMWS.class.getName());

    private DaoLugar daoLugar = new DaoLugar();

    @GET
    @Path("/buscar")
    public List<Lugar> getList() throws Exception {

       try {
           logger.info("Accediendo al servicio que busca todos los lugares");

           List<Lugar> lugarList = daoLugar.findAll(Lugar.class);

           logger.info("Accediendo al servicio que busca todos los lugares");

           return lugarList;

       }catch (Exception e){

           logger.info("Error al buscar una lista de lugares: "+ e.getMessage());

           throw new Exception(e);

       }
    }

}
