package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.Medio_comunicacionDto;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/medio_comunicacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Medio_comunicacionORMWS {

    @PUT
    @Path( "/addMedio_comunicacion" )
    public Medio_comunicacionDto addMedio_comunicacion(Medio_comunicacionDto medio_comunicacionDto  )
    {
        Medio_comunicacionDto resultado = new Medio_comunicacionDto();
        try
        {
            DaoMedio_comunicacion dao = new DaoMedio_comunicacion();
            Medio_comunicacion medio_comunicacion = new Medio_comunicacion();
            medio_comunicacion.set_nombre( medio_comunicacionDto.getNombre() );
            medio_comunicacion.set_estado( medio_comunicacionDto.getEstado() );
            Dato_usuario dato_usuario = new Dato_usuario(medio_comunicacionDto.getDatoUsuarioDto().getId());
            medio_comunicacion.set_datoUsuario( dato_usuario);
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(medio_comunicacionDto.getSolicitudEstudioDto().getId());

            medio_comunicacion.set_solicitudEstudio( solicitud_estudio);
            Medio_comunicacion resul = dao.insert( medio_comunicacion );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteMedio_comunicacion/{id}")
    public Medio_comunicacionDto deleteMedio_comunicacion (@PathParam("id") long id){
        Medio_comunicacionDto resultado = new Medio_comunicacionDto();

        try{
            DaoMedio_comunicacion dao = new DaoMedio_comunicacion();
            Medio_comunicacion medio_comunicacion = dao.find(id, Medio_comunicacion.class);
            if(medio_comunicacion != null){
                Medio_comunicacion result = dao.delete(medio_comunicacion);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showMedio_comunicacion")
    public List<Medio_comunicacion> showMedio_comunicacions(){
        List<Medio_comunicacion> medio_comunicacions = null;
        try{
            DaoMedio_comunicacion dao = new DaoMedio_comunicacion();
            medio_comunicacions = dao.findAll(Medio_comunicacion.class);
            System.out.println("Medio_comunicacions:");
            for (Medio_comunicacion medio_comunicacion : medio_comunicacions) {
                System.out.print(medio_comunicacion.get_id());
                System.out.print(", ");
                System.out.print(medio_comunicacion.get_nombre());
                System.out.print(", ");
                System.out.print(medio_comunicacion.get_estado());
                System.out.print(", ");
                System.out.print(medio_comunicacion.get_datoUsuario().get_id());
                System.out.print("");
                System.out.print(medio_comunicacion.get_solicitudEstudio().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return medio_comunicacions;
    }

    @PUT
    @Path( "/updateMedio_comunicacion/{id}" )
    public Medio_comunicacionDto updateMedio_comunicacion( @PathParam("id") long id , Medio_comunicacionDto medio_comunicacionDto)
    {
        Medio_comunicacionDto resultado = new Medio_comunicacionDto();
        try
        {
            DaoMedio_comunicacion dao = new DaoMedio_comunicacion();
            Medio_comunicacion medio_comunicacion = dao.find(id, Medio_comunicacion.class);
            medio_comunicacion.set_nombre( medio_comunicacionDto.getNombre() );
            medio_comunicacion.set_estado( medio_comunicacionDto.getEstado() );
            Dato_usuario dato_usuario = new Dato_usuario(medio_comunicacionDto.getDatoUsuarioDto().getId());
            medio_comunicacion.set_datoUsuario( dato_usuario);
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(medio_comunicacionDto.getSolicitudEstudioDto().getId());
            medio_comunicacion.set_solicitudEstudio( solicitud_estudio);
            Medio_comunicacion resul = dao.update(medio_comunicacion);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @POST
    @Path( "/addMediosASolicitud/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Solicitud_estudioDto addLista_medios(@PathParam("id") long id, List<Medio_comunicacionDto> listaMedios)
    {
        Solicitud_estudioDto resultado = new Solicitud_estudioDto();
        try
        {
            DaoMedio_comunicacion dao = new DaoMedio_comunicacion();
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoDato_usuario daoDato_usuario = new DaoDato_usuario();
            Solicitud_estudio solicitud_estudio = daoSolicitud_estudio.find(id, Solicitud_estudio.class);
            resultado.setId(solicitud_estudio.get_id());
            for (Medio_comunicacionDto medio_comunicacionAux : listaMedios) {
                Medio_comunicacion medio_comunicacion = new Medio_comunicacion();
                medio_comunicacion.set_estado( "A");
                medio_comunicacion.set_nombre(medio_comunicacionAux.getNombre());
                Dato_usuario dato_usuario= daoDato_usuario.find(medio_comunicacionAux.getDatoUsuarioDto().getId(), Dato_usuario.class);
                medio_comunicacion.set_datoUsuario( dato_usuario);
                medio_comunicacion.set_solicitudEstudio(solicitud_estudio);
                Medio_comunicacion resul = dao.insert( medio_comunicacion );
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
