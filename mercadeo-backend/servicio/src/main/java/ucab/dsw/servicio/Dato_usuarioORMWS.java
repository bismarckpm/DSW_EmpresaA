package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.entidades.Ocupacion;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/dato_usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class Dato_usuarioORMWS {

    @PUT
    @Path( "/addDatoUsuario" )
    public Dato_usuarioDto addDato_usuario(Dato_usuarioDto dato_usuarioDto )
    {
        Dato_usuarioDto resultado = new Dato_usuarioDto();
        try
        {
            DaoDato_usuario dao = new DaoDato_usuario();
            Dato_usuario dato_usuario = new Dato_usuario();
            dato_usuario.set_cedula( dato_usuarioDto.getCedula() );
            dato_usuario.set_primerNombre( dato_usuarioDto.getPrimerNombre() );
            dato_usuario.set_segundoNombre( dato_usuarioDto.getSegundoNombre() );
            dato_usuario.set_primerApellido( dato_usuarioDto.getPrimerApellido() );
            dato_usuario.set_segundoApellido( dato_usuarioDto.getSegundoApellido() );
            dato_usuario.set_sexo( dato_usuarioDto.getSexo() );
            dato_usuario.set_fechaNacimiento( dato_usuarioDto.getFechaNacimiento() );
            dato_usuario.set_estadoCivil( dato_usuarioDto.getEstadoCivil() );
            dato_usuario.set_disponibilidadEnLinea( dato_usuarioDto.getDisponibilidadEnLinea() );
            dato_usuario.set_conCuantasPersonasVive( dato_usuarioDto.getConCuantasPersonasVive() );
            dato_usuario.set_estado( dato_usuarioDto.getEstado() );
            Nivel_economico nivel_economico = new Nivel_economico(dato_usuarioDto.getNivelEconomicoDto().getId());
            dato_usuario.set_nivelEconomico( nivel_economico);
            Nivel_academico nivel_academico = new Nivel_academico(dato_usuarioDto.getNivelAcademicoDto().getId());
            dato_usuario.set_nivelAcademico( nivel_academico);
            Lugar lugar = new Lugar(dato_usuarioDto.getLugarDto().getId());
            dato_usuario.set_lugar( lugar);
            Ocupacion ocupacion = new Ocupacion(dato_usuarioDto.getOcupacionDto().getId());
            dato_usuario.set_ocupacion( ocupacion);
            Dato_usuario resul = dao.insert( dato_usuario );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteDato_usuario/{id}")
    public Dato_usuarioDto deleteDato_usuario (@PathParam("id") long id){
        Dato_usuarioDto resultado = new Dato_usuarioDto();

        try{
            DaoDato_usuario dao = new DaoDato_usuario();
            Dato_usuario dato_usuario = dao.find(id, Dato_usuario.class);
            if(dato_usuario != null){
                Dato_usuario result = dao.delete(dato_usuario);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showDato_usuario")
    public List<Dato_usuario> showDato_usuarios(){
        List<Dato_usuario> dato_usuarios = null;
        try{
            DaoDato_usuario dao = new DaoDato_usuario();
            dato_usuarios = dao.findAll(Dato_usuario.class);
            System.out.println("Dato_usuarios:");
            for (Dato_usuario dato_usuario : dato_usuarios) {
                System.out.print(dato_usuario.get_id());
                System.out.print(", ");
                System.out.print(dato_usuario.get_cedula());
                System.out.print(", ");
                System.out.print(dato_usuario.get_primerNombre());
                System.out.print(", ");
                System.out.print(dato_usuario.get_segundoNombre());
                System.out.print(", ");
                System.out.print(dato_usuario.get_primerApellido());
                System.out.print(", ");
                System.out.print(dato_usuario.get_segundoApellido());
                System.out.print(", ");
                System.out.print(dato_usuario.get_sexo());
                System.out.print(", ");
                System.out.print(dato_usuario.get_fechaNacimiento());
                System.out.print(", ");
                System.out.print(dato_usuario.get_estadoCivil());
                System.out.print(", ");
                System.out.print(dato_usuario.get_disponibilidadEnLinea());
                System.out.print(", ");
                System.out.print(dato_usuario.get_conCuantasPersonasVive());
                System.out.print(", ");
                System.out.print(dato_usuario.get_estado());
                System.out.print(", ");
                System.out.print(dato_usuario.get_nivelEconomico().get_id());
                System.out.print("");
                System.out.print(dato_usuario.get_nivelAcademico().get_id());
                System.out.print("");
                System.out.print(dato_usuario.get_ocupacion().get_id());
                System.out.print("");
                System.out.print(dato_usuario.get_lugar().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return dato_usuarios;
    }

    @PUT
    @Path( "/updateDato_usuario/{id}" )
    public Dato_usuarioDto updateDato_usuario( @PathParam("id") long id , Dato_usuarioDto dato_usuarioDto)
    {
        Dato_usuarioDto resultado = new Dato_usuarioDto();
        try
        {
            DaoDato_usuario dao = new DaoDato_usuario();
            Dato_usuario dato_usuario = dao.find(id, Dato_usuario.class);
            dato_usuario.set_cedula( dato_usuarioDto.getCedula() );
            dato_usuario.set_primerNombre( dato_usuarioDto.getPrimerNombre() );
            dato_usuario.set_segundoNombre( dato_usuarioDto.getSegundoNombre() );
            dato_usuario.set_primerApellido( dato_usuarioDto.getPrimerApellido() );
            dato_usuario.set_segundoApellido( dato_usuarioDto.getSegundoApellido() );
            dato_usuario.set_sexo( dato_usuarioDto.getSexo() );
            dato_usuario.set_fechaNacimiento( dato_usuarioDto.getFechaNacimiento() );
            dato_usuario.set_estadoCivil( dato_usuarioDto.getEstadoCivil() );
            dato_usuario.set_disponibilidadEnLinea( dato_usuarioDto.getDisponibilidadEnLinea() );
            dato_usuario.set_conCuantasPersonasVive( dato_usuarioDto.getConCuantasPersonasVive() );
            dato_usuario.set_estado( dato_usuarioDto.getEstado() );
            Nivel_economico nivel_economico = new Nivel_economico(dato_usuarioDto.getNivelEconomicoDto().getId());
            dato_usuario.set_nivelEconomico( nivel_economico);
            Nivel_academico nivel_academico = new Nivel_academico(dato_usuarioDto.getNivelAcademicoDto().getId());
            dato_usuario.set_nivelAcademico( nivel_academico);
            Lugar lugar = new Lugar(dato_usuarioDto.getLugarDto().getId());
            dato_usuario.set_lugar( lugar);
            Ocupacion ocupacion = new Ocupacion(dato_usuarioDto.getOcupacionDto().getId());
            dato_usuario.set_ocupacion( ocupacion);
            Dato_usuario resul = dao.update(dato_usuario);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }    
}
