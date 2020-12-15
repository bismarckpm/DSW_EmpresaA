package ucab.dsw.servicio;

import org.junit.Assert;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/solicitud_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Solicitud_estudioORMWS {
    
    @POST
    @Path( "/agregar" )
    public Solicitud_estudioDto addSolicitud_estudio(Solicitud_estudioDto solicitud_estudioDto, List<Region_estudioDto> region_estudiosDto , List<Medio_comunicacionDto> medio_comunicacionsDto )
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            //Dao
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            //insert Solicitud_estudio
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio();
            solicitud_estudio.set_descripcionSolicitud(solicitud_estudioDto.getDescripcionSolicitud());
            solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_cantidadHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_generoHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMinimaHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMaximaHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_estado( solicitud_estudioDto.getEstado() );

            Nivel_economico nivel_economico = new Nivel_economico(solicitud_estudioDto.getNivelEconomicoDto().getId());
            solicitud_estudio.set_nivelEconomico( nivel_economico);

            Ocupacion ocupacion = new Ocupacion(solicitud_estudioDto.getOcupacionDto().getId());
            solicitud_estudio.set_ocupacion( ocupacion);

            //usuario tomado del sistema
            //solicitud_estudio.set_subcategoria( subcategoria);

            Producto producto = new Producto(solicitud_estudioDto.getProductoDto().getId());
            solicitud_estudio.set_producto( producto);

            Solicitud_estudio resul = dao.insert( solicitud_estudio );

            //Insert Region estudio
            for (Region_estudioDto region_estudioDto : region_estudiosDto) {
                Region_estudioORMWS servicio = new Region_estudioORMWS();
                Region_estudioDto resultado1 = servicio.addRegion_estudio( region_estudioDto, solicitud_estudio );
                Assert.assertNotEquals( resultado1.getId(), 0  );
            }

            //Insert Medio comunicacion
            for (Medio_comunicacionDto medio_comunicacionDto : medio_comunicacionsDto) {
                Medio_comunicacionORMWS servicio = new Medio_comunicacionORMWS();
                Medio_comunicacionDto resultado1 = servicio.addMedio_comunicacion( medio_comunicacionDto, solicitud_estudio);
                Assert.assertNotEquals( resultado1.getId(), 0  );
            }

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
    public Solicitud_estudio consultarSolicitud_estudio(@PathParam("id") long id){

        DaoSolicitud_estudio solicitud_estudioDao = new DaoSolicitud_estudio();
        return solicitud_estudioDao.find(id, Solicitud_estudio.class);
    }

    @DELETE
    @Path ("/deleteSolicitud_estudio/{id}")
    public Solicitud_estudioDto deleteSolicitud_estudio (@PathParam("id") long id){
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();

        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            Solicitud_estudio solicitud_estudio = dao.find(id, Solicitud_estudio.class);
            if(solicitud_estudio != null){
                Solicitud_estudio result = dao.delete(solicitud_estudio);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/buscar")
    public List<Solicitud_estudio> showSolicitud_estudios(){
        List<Solicitud_estudio> solicitud_estudios = null;
        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            solicitud_estudios = dao.findAll(Solicitud_estudio.class);
            System.out.println("Solicitud_estudios:");
            for (Solicitud_estudio solicitud_estudio : solicitud_estudios) {
                System.out.print(solicitud_estudio.get_id());
                System.out.print(", ");
                /*System.out.print(solicitud_estudio.get_nombre());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_descripcion());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_estado());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_marca().get_id());
                System.out.print("");
                System.out.print(solicitud_estudio.get_subcategoria().get_id());*/
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return solicitud_estudios;
    }

    @PUT
    @Path( "/actualizar/{id}" )
    public Solicitud_estudioDto updateSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto, List<Region_estudioDto> region_estudiosDto , List<Medio_comunicacionDto> medio_comunicacionsDto )
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            Solicitud_estudio solicitud_estudio = dao.find(id, Solicitud_estudio.class);
            solicitud_estudio.set_descripcionSolicitud(solicitud_estudioDto.getDescripcionSolicitud());
            solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_cantidadHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_generoHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMinimaHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMaximaHijos( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_estado( solicitud_estudioDto.getEstado() );

            Nivel_economico nivel_economico = new Nivel_economico(solicitud_estudioDto.getNivelEconomicoDto().getId());
            solicitud_estudio.set_nivelEconomico( nivel_economico);

            Ocupacion ocupacion = new Ocupacion(solicitud_estudioDto.getOcupacionDto().getId());
            solicitud_estudio.set_ocupacion( ocupacion);

            //usuario tomado del sistema
            //solicitud_estudio.set_subcategoria( subcategoria);

            Producto producto = new Producto(solicitud_estudioDto.getProductoDto().getId());
            solicitud_estudio.set_producto( producto);

            Solicitud_estudio resul = dao.insert( solicitud_estudio );

            //Insert presentaciones
            for (Region_estudioDto region_estudioDto : region_estudiosDto) {
                Region_estudioORMWS servicio = new Region_estudioORMWS();
                Region_estudioDto resultado1 = servicio.addRegion_estudio( region_estudioDto, solicitud_estudio );
                Assert.assertNotEquals( resultado1.getId(), 0  );
            }

            //Insert Tipo
            for (Medio_comunicacionDto medio_comunicacionDto : medio_comunicacionsDto) {
                Medio_comunicacionORMWS servicio = new Medio_comunicacionORMWS();
                Medio_comunicacionDto resultado1 = servicio.addMedio_comunicacion( medio_comunicacionDto, solicitud_estudio);
                Assert.assertNotEquals( resultado1.getId(), 0  );
            }

            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
