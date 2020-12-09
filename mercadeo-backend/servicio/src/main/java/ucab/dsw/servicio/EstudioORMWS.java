package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class EstudioORMWS {

    @PUT
    @Path( "/addEstudio" )
    public EstudioDto addEstudio(EstudioDto estudioDto )
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = new Estudio();
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_tipoDeInstrumento( estudioDto.getTipoDeInstrumento() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFin() );
            estudio.set_estatus( estudioDto.getEstatus() );
            estudio.set_estado( estudioDto.getEstado() );
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(estudioDto.getSolicitudEstudioDto().getId());
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
            estudio.set_usuario( usuario);
            Estudio resul = dao.insert( estudio );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteEstudio/{id}")
    public EstudioDto deleteEstudio (@PathParam("id") long id){
        EstudioDto resultado = new EstudioDto();

        try{
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            if(estudio != null){
                Estudio result = dao.delete(estudio);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showEstudio")
    public List<Estudio> showEstudios(){
        List<Estudio> estudios = null;
        try{
            DaoEstudio dao = new DaoEstudio();
            estudios = dao.findAll(Estudio.class);
            System.out.println("Estudios:");
            for (Estudio estudio : estudios) {
                System.out.print(estudio.get_id());
                System.out.print(", ");
                System.out.print(estudio.get_nombre());
                System.out.print(", ");
                System.out.print(estudio.get_tipoDeInstrumento());
                System.out.print(", ");
                System.out.print(estudio.get_fechaInicio());
                System.out.print(", ");
                System.out.print(estudio.get_fechaFin());
                System.out.print(", ");
                System.out.print(estudio.get_estatus());
                System.out.print(", ");
                System.out.print(estudio.get_estado());
                System.out.print(", ");
                System.out.print(estudio.get_solicitudEstudio().get_id());
                System.out.print("");
                System.out.print(estudio.get_usuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return estudios;
    }

    @PUT
    @Path( "/updateEstudio/{id}" )
    public EstudioDto updateEstudio( @PathParam("id") long id , EstudioDto estudioDto)
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_tipoDeInstrumento( estudioDto.getTipoDeInstrumento() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFin() );
            estudio.set_estatus( estudioDto.getEstatus() );
            estudio.set_estado( estudioDto.getEstado() );
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(estudioDto.getSolicitudEstudioDto().getId());
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getUsuarioDto().getId());
            estudio.set_usuario( usuario);
            Estudio resul = dao.update(estudio);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }
}
