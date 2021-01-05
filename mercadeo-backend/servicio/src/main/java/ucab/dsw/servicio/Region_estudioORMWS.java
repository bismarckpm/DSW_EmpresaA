package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/region_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Region_estudioORMWS {

    /**
     * Este método registra en el sistema una región de estudio
     *
     * @param  region_estudioDto  región de estudio a ser registrada
     * @return      la region_estudioDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Region_estudioDto addRegion_estudio(Region_estudioDto region_estudioDto )
    {
        Region_estudioDto resultado = new Region_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            Region_estudio region_estudio = new Region_estudio();
            region_estudio.set_estado( "A" );

            Lugar lugar = new Lugar(region_estudioDto.getLugarDto().getId());
            region_estudio.set_lugar( lugar);

            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(region_estudioDto.getSolicitudEstudioDto().getId());
            region_estudio.set_solicitudEstudio( solicitud_estudio);

            Region_estudio resul = dao.insert( region_estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /**
     * Este método consulta una región de estudio específica
     *
     * @param  id  id de la región de estudio a ser consultada
     * @return      la región de estudio completa que se desea consultar
     */
    @GET
    @Path ("/consultar/{id}")
    public Region_estudio consultarRegion_estudio(@PathParam("id") long id){

        DaoRegion_estudio categoriaDao = new DaoRegion_estudio();
        return categoriaDao.find(id, Region_estudio.class);
    }

    /**
     * Este método retorna la lista con todas las regiones de estudio de todas las solicitudes de estudio
     *
     * @return      la lista completa de regiones de estudio registradas
     */
    @GET
    @Path("/buscar")
    public List<Region_estudio> showRegion_estudio()
    {
        List<Region_estudio> categorias = null;
        try {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            categorias = dao.findAll(Region_estudio.class);
            System.out.println("Categorias: ");
            for(Region_estudio region_estudio : categorias) {
                System.out.print(region_estudio.get_id());
                System.out.print(", ");
                System.out.print(region_estudio.get_estado());
                System.out.println();
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return categorias;
    }

    /**
     * Este método actualiza una región de estudio específica
     *
     * @param  region_estudioDto  región de estudio a ser actualizada
     * @return      la region_estudioDto que ha sido actualizada
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Region_estudioDto editRegion_estudio( Region_estudioDto region_estudioDto )
    {
        Region_estudioDto resultado = new Region_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            Region_estudio region_estudio = new Region_estudio(region_estudioDto.getId());
            region_estudio.set_estado (region_estudioDto.getEstado());
            Lugar lugar = new Lugar(region_estudioDto.getLugarDto().getId());
            region_estudio.set_lugar( lugar);
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(region_estudioDto.getSolicitudEstudioDto().getId());
            region_estudio.set_solicitudEstudio( solicitud_estudio);
            Region_estudio resul = dao.update (region_estudio );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /**
     * Este método elimina en el sistema una región de estudio
     *
     * @param  region_estudioDto  región de estudio a ser eliminada
     * @return      la region_estudioDto que ha sido eliminada en el sistema
     */
    @DELETE
    @Path( "/borrar/{id}" )
    public Region_estudioDto deleteRegion_estudio( Region_estudioDto region_estudioDto)
    {
        Region_estudioDto resultado = new Region_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            Region_estudio region_estudio = dao.find(region_estudioDto.getId(), Region_estudio.class);
            Region_estudio resul = dao.delete (region_estudio );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /**
     * Este método recibe una lista de lugares para registrar las regiones de estudio de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio a la que serán agregadas las regiones de estudio
     * @param  listaLugares lista de lugares con los que irá relacionada cada región de estudio
     * @return      la solicitud_estudioDto con la que ese relacionan las regiones de estudio agregadas
     */
    @POST
    @Path( "/addRegionesASolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto addLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaLugares)
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoLugar daoLugar = new DaoLugar();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
            resultado.setId(solicitud_estudio.get_id());
            for (Region_estudioDto lugarAux : listaLugares) {
                Region_estudio region_estudio = new Region_estudio();
                region_estudio.set_estado( "A");
                Lugar lugar = daoLugar.find(lugarAux.getLugarDto().getId(), Lugar.class);
                region_estudio.set_lugar( lugar);
                region_estudio.set_solicitudEstudio(solicitud_estudio);
                Region_estudio resul = dao.insert( region_estudio );
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    /**
     * Este método retorna las regiones de estudio de una solicitud de estudio específica
     *
     * @param  id  id de la solicitud de estudio de la cual se desea obtener sus regiones de estudio
     * @return      la lista de lugares con los que se relacionan las regiones de estudio de una solicitud de estudio
     */
    @GET
    @Path("/getRegionesDeSolicitud/{id}")
    public List<Lugar> getRegionesDeSolicitud(@PathParam("id") long id){
        List<Lugar> lugares = null;
        try{
            DaoLugar dao = new DaoLugar();
            lugares = dao.getRegionesDeSolicitud(id);
            System.out.println("Regiones:");
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

    /**
     * Este método actualiza las regiones de estudio de una solicitud de estudio
     *
     * @param  id  id de la solicitud de estudio a la cual se le actualizarán las regiones de estudio
     * @param  listaLugares lista de lugares con los cuales se actualizarán las regiones de estudio de la solicitud
     * @return      la solicitud_estudioDto a la cual se le actualizaron sus regiones de estudio
     */
    @POST
    @Path( "/updateRegionesDeSolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto updateLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaLugares)
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            List<Region_estudio> regionesOld = dao.getRegionesActualizar(id);
            for (Region_estudio regAux : regionesOld) {
                Region_estudio resul = dao.delete (regAux );
            }
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoLugar daoLugar = new DaoLugar();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
            resultado.setId(solicitud_estudio.get_id());
            for (Region_estudioDto lugarAux : listaLugares) {
                Region_estudio region_estudio = new Region_estudio();
                region_estudio.set_estado( "A");
                Lugar lugar = daoLugar.find(lugarAux.getLugarDto().getId(), Lugar.class);
                region_estudio.set_lugar( lugar);
                region_estudio.set_solicitudEstudio(solicitud_estudio);
                Region_estudio resul = dao.insert( region_estudio );
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
