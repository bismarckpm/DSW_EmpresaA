package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoRegion_estudio;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Region_estudioDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Region_estudio;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class Region_estudioORMWS {

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

    @GET
    @Path ("/consultar/{id}")
    public Region_estudio consultarRegion_estudio(@PathParam("id") long id){

        DaoRegion_estudio categoriaDao = new DaoRegion_estudio();
        return categoriaDao.find(id, Region_estudio.class);
    }

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

    @POST
    @Path( "/addRegionesASolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto addLista_regiones(@PathParam("id") long id, List<Region_estudioDto> listaRegiones)
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoRegion_estudio dao = new DaoRegion_estudio();
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoLugar daoLugar = new DaoLugar();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
            resultado.setId(solicitud_estudio.get_id());
            for (Region_estudioDto region_estudioAux : listaRegiones) {
                Region_estudio region_estudio = new Region_estudio();
                region_estudio.set_estado( "A");
                Lugar lugar = daoLugar.find(region_estudioAux.getLugarDto().getId(), Lugar.class);
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
