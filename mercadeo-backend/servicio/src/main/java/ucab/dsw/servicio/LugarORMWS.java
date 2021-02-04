package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import ucab.dsw.entidades.Response.ApiRestResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public LugarDto addLugar( LugarDto lugarDto ) throws Exception
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
            throw new ucab.dsw.excepciones.CreateException( "Error agregando un nuevo lugar");
        }
        return  resultado;
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
    public LugarDto updateLugar( @PathParam("id") long id , LugarDto lugarDto) throws Exception
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
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando un lugar");
        }
        return  resultado;
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
