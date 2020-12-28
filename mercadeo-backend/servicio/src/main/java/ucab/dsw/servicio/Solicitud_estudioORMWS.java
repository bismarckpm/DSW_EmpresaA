package ucab.dsw.servicio;

import org.junit.Assert;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path( "/solicitud_estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Solicitud_estudioORMWS {

    @POST
    @Path( "/agregar" )
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
            solicitud_estudio.set_estatus("Solicitado");
            Date date = new Date();
            solicitud_estudio.set_fechaPeticion( date);
            solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getEdadMinimaPoblacion() );
            solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getEdadMaximaPoblacion() );
            solicitud_estudio.set_estado( "A" );
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
    public Solicitud_estudioDto updateSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto )
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            DaoNivel_economico daoNivel = new DaoNivel_economico();
            DaoOcupacion daoOcu = new DaoOcupacion();
            DaoUsuario daoUser = new DaoUsuario();
            DaoProducto daoProd = new DaoProducto();
            Solicitud_estudio solicitud_estudio = dao.find(id, Solicitud_estudio.class);
            solicitud_estudio.set_descripcionSolicitud(solicitud_estudioDto.getDescripcionSolicitud());
            solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getEdadMinimaPoblacion() );
            solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getEdadMaximaPoblacion() );

            solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getConCuantasPersonasVive());
            solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getDisponibilidadEnLinea() );
            solicitud_estudio.set_estatus(solicitud_estudioDto.getEstatus());
            solicitud_estudio.set_estado( solicitud_estudioDto.getEstado() );

            Usuario usuario = daoUser.find (solicitud_estudioDto.getUsuarioDto().getId(), Usuario.class);
            solicitud_estudio.set_usuario( usuario);
            Nivel_economico nivel_economico = daoNivel.find(solicitud_estudioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);
            solicitud_estudio.set_nivelEconomico( nivel_economico);
            Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
            solicitud_estudio.set_ocupacion( ocupacion);
            Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
            solicitud_estudio.set_producto( producto);

            Solicitud_estudio resul = dao.update( solicitud_estudio );


            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @GET
    @Path("/showSolicitudUsuario/{id}")
    public List<Solicitud_estudio> showSolicitud_estudio_usuario(@PathParam("id") long id){
        List<Solicitud_estudio> solicitud_estudios = null;
        try{
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            solicitud_estudios = dao.solicitudesCliente(id);
            System.out.println("Solicitud_estudios:");
            for (Solicitud_estudio solicitud_estudio : solicitud_estudios) {
                System.out.print(solicitud_estudio.get_id());
                System.out.print(", ");
            }

        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return solicitud_estudios;
    }


    @PUT
    @Path( "/inactivar/{id}" )
    public Solicitud_estudioDto inactivarSolicitud_estudio( @PathParam("id") long id , Solicitud_estudioDto solicitud_estudioDto )
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoSolicitud_estudio dao = new DaoSolicitud_estudio();
            DaoNivel_economico daoNivel = new DaoNivel_economico();
            DaoOcupacion daoOcu = new DaoOcupacion();
            DaoUsuario daoUser = new DaoUsuario();
            DaoProducto daoProd = new DaoProducto();
            Solicitud_estudio solicitud_estudio = dao.find(id, Solicitud_estudio.class);
            solicitud_estudio.set_descripcionSolicitud(solicitud_estudioDto.getDescripcionSolicitud());
            solicitud_estudio.set_generoPoblacional( solicitud_estudioDto.getGeneroPoblacional() );
            solicitud_estudio.set_edadMinimaPoblacion( solicitud_estudioDto.getEdadMinimaPoblacion() );
            solicitud_estudio.set_edadMaximaPoblacion( solicitud_estudioDto.getEdadMaximaPoblacion() );

            solicitud_estudio.set_conCuantasPersonasVive( solicitud_estudioDto.getConCuantasPersonasVive());
            solicitud_estudio.set_disponibilidadEnLinea( solicitud_estudioDto.getDisponibilidadEnLinea() );
            solicitud_estudio.set_estado( "I" );
            solicitud_estudio.set_estatus(solicitud_estudioDto.getEstatus());

            Usuario usuario = daoUser.find (solicitud_estudioDto.getUsuarioDto().getId(), Usuario.class);
            solicitud_estudio.set_usuario( usuario);
            Nivel_economico nivel_economico = daoNivel.find(solicitud_estudioDto.getNivelEconomicoDto().getId(), Nivel_economico.class);
            solicitud_estudio.set_nivelEconomico( nivel_economico);
            Ocupacion ocupacion = daoOcu.find(solicitud_estudioDto.getOcupacionDto().getId(), Ocupacion.class);
            solicitud_estudio.set_ocupacion( ocupacion);
            Producto producto = daoProd.find(solicitud_estudioDto.getProductoDto().getId(), Producto.class);
            solicitud_estudio.set_producto( producto);

            Solicitud_estudio resul = dao.update( solicitud_estudio );


            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
