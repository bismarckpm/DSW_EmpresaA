package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import ucab.dsw.Response.ApiRestResponse;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path( "/lugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LugarORMWS {

    @PUT
    @Path( "/addlugar" )
    public LugarDto addLugar( LugarDto lugarDto )
    {
        LugarDto resultado = new LugarDto();
        try
        {
            DaoLugar dao = new DaoLugar();
            Lugar lugar = new Lugar();
            lugar.set_nombre( lugarDto.getNombre() );
            lugar.set_tipo( lugarDto.getTipo() );
            lugar.set_categoriaSocioEconomica( lugarDto.getCategoriaSocioEconomica() );
            lugar.set_estado( lugarDto.getEstado() );
            Lugar lugar2 = new Lugar(lugarDto.getLugarDto().getId());
            lugar.set_lugar( lugar2 );
            Lugar resul = dao.insert( lugar );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteLugar/{id}")
    public LugarDto deleteLugar (@PathParam("id") long id){
        LugarDto resultado = new LugarDto();

        try{
            DaoLugar dao = new DaoLugar();
            Lugar lugar = dao.find(id, Lugar.class);
            if(lugar != null){
                Lugar result = dao.delete(lugar);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showLugar")
    public List<Lugar> showLugares(){
        List<Lugar> lugares = null;
        try{
            DaoLugar dao = new DaoLugar();
            lugares = dao.findAll(Lugar.class);
            System.out.println("Lugares:");
            for (Lugar lugar : lugares) {
                System.out.print(lugar.get_id());
                System.out.print(", ");
                System.out.print(lugar.get_nombre());
                System.out.print(", ");
                System.out.print(lugar.get_tipo());
                System.out.print(", ");
                System.out.print(lugar.get_categoriaSocioEconomica());
                System.out.print(", ");
                System.out.print(lugar.get_estado());
                System.out.print(", ");
                if (lugar.get_lugar()!=null){
                    System.out.print(lugar.get_lugar().get_id());
                    System.out.print("");
                }
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return lugares;
    }

    @PUT
    @Path( "/updatelugar/{id}" )
    public LugarDto updateLugar( @PathParam("id") long id , LugarDto lugarDto)
    {
        LugarDto resultado = new LugarDto();
        try
        {
            DaoLugar dao = new DaoLugar();
            Lugar lugar = dao.find(id, Lugar.class);
            lugar.set_nombre( lugarDto.getNombre() );
            lugar.set_tipo( lugarDto.getTipo() );
            lugar.set_categoriaSocioEconomica( lugarDto.getCategoriaSocioEconomica() );
            lugar.set_estado( lugarDto.getEstado() );
            Lugar lugar2 = new Lugar(lugarDto.getLugarDto().getId());
            lugar.set_lugar( lugar2 );
            Lugar resul = dao.update(lugar);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

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

    @GET
    @Path("/getEstados")
    public List<Lugar> getEstados(){
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
            String problem = e.getMessage();
        }
        return lugares;
    }

}
