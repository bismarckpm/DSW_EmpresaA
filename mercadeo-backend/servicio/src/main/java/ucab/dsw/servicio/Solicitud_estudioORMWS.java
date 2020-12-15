package ucab.dsw.servicio;


import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/solicitud_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Solicitud_estudioORMWS {

    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto addSolicitud_estudio(Solicitud_estudioDto solicitud_estudioDto)
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            DaoNivel_economico daoNivel = new DaoNivel_economico();
            DaoOcupacion daoOcu = new DaoOcupacion();
            DaoUsuario daoUser = new DaoUsuario();
            DaoProducto daoProd = new DaoProducto();
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio();
            solicitud_estudio.set_descripcionSolicitud( solicitud_estudioDto.getDescripcionSolicitud() );
            solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_fechaPeticion( solicitud_estudioDto.getFechaPeticion() );
            solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getEdadMinimaPoblacion() );
            solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getEdadMaximaPoblacion() );
            solicitud_estudio.set_estado( "A" );
            solicitud_estudio.set_cantidadHijos( solicitud_estudioDto.getCantidadHijos());
            solicitud_estudio.set_generoHijos( solicitud_estudioDto.getGeneroHijos() );
            solicitud_estudio.set_edadMinimaHijos( solicitud_estudioDto.getEdadMinimaHijos());
            solicitud_estudio.set_edadMaximaHijos( solicitud_estudioDto.getEdadMaximaHijos() );
            solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getConCuantasPersonasVive() );
            solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getDisponibilidadEnLinea() );
            Usuario usuario = daoUser.find (solicitud_estudioDto.getUsuarioDto().getId(), Usuario.class);
            solicitud_estudio.set_usuario( usuario);
            Nivel_economico nivel_economico = daoNivel.find(solicitud_estudioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);
            solicitud_estudio.set_nivelEconomico( nivel_economico);
            Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
            solicitud_estudio.set_ocupacion( ocupacion);
            Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
            solicitud_estudio.set_producto( producto);
            Solicitud_estudio resul = dao.insert( solicitud_estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
    
    @GET
    @Path("/show")
    public List<Solicitud_estudio> showSolicitud_estudios(){
        List<Solicitud_estudio> solicitud_estudios = null;
        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            solicitud_estudios = dao.findAll(Solicitud_estudio.class);
            System.out.println("Solicitud_estudios:");
            for (Solicitud_estudio solicitud_estudio : solicitud_estudios) {
                System.out.print(solicitud_estudio.get_id());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_descripcionSolicitud());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_generoPoblacional());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_fechaPeticion());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_edadMinimaPoblacion());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_edadMaximaPoblacion());
                System.out.print(", ");
                System.out.print(solicitud_estudio.get_estado());
                System.out.print(", ");
                if (solicitud_estudio.get_cantidadHijos() != null){
                    System.out.print(solicitud_estudio.get_cantidadHijos());
                    System.out.print(", ");
                }
                if (solicitud_estudio.get_generoHijos() != null){
                    System.out.print(solicitud_estudio.get_generoHijos());
                    System.out.print(", ");
                }
                if (solicitud_estudio.get_edadMinimaHijos() != null){
                    System.out.print(solicitud_estudio.get_edadMinimaHijos());
                    System.out.print(", ");
                }
                if (solicitud_estudio.get_edadMaximaHijos() != null){
                    System.out.print(solicitud_estudio.get_edadMaximaHijos());
                    System.out.print(", ");
                }
                if (solicitud_estudio.get_conCuantasPersonasVive() != null){
                    System.out.print(solicitud_estudio.get_conCuantasPersonasVive());
                    System.out.print(", ");
                }
                if (solicitud_estudio.get_disponibilidadEnLinea() != null){
                    System.out.print(solicitud_estudio.get_disponibilidadEnLinea());
                    System.out.print(", ");
                }
                System.out.print(solicitud_estudio.get_nivelEconomico().get_id());
                System.out.print("");
                System.out.print(solicitud_estudio.get_ocupacion().get_id());
                System.out.print("");
                System.out.print(solicitud_estudio.get_usuario().get_id());
                System.out.print("");
                System.out.print(solicitud_estudio.get_producto().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return solicitud_estudios;
    }
}
