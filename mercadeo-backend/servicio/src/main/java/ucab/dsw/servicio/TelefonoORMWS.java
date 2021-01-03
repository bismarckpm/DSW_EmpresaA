package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Telefono;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Log
@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoORMWS {

    private DaoTelefono daoTelefono = new DaoTelefono();

    private Logger logger = Logger.getLogger(TelefonoORMWS.class.getName());

    @POST
    @Path( "/addTelefono" )
    public TelefonoDto addTelefono(List<TelefonoDto> telefonos )
    {
        TelefonoDto resultado = new TelefonoDto();
        try
        {
            DaoTelefono dao = new DaoTelefono();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (TelefonoDto telefonoDto : telefonos) {
                Telefono telefono = new Telefono();
                telefono.set_numero(telefonoDto.getNumero());
                telefono.set_estado(telefonoDto.getEstado());
                Dato_usuario dato_usuario = daoDatoUsuario.find(telefonoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
                telefono.set_datoUsuario(dato_usuario);
                Telefono resul = dao.insert(telefono);
                resultado.setId(resul.get_id());
            }

        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteTelefono/{id}")
    public TelefonoDto deleteTelefono (@PathParam("id") long id){
        TelefonoDto resultado = new TelefonoDto();

        try{
            DaoTelefono dao = new DaoTelefono();
            Telefono telefono = dao.find(id, Telefono.class);
            if(telefono != null){
                Telefono result = dao.delete(telefono);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showTelefono")
    public List<Telefono> showTelefonos(){
        List<Telefono> telefonos = null;
        try{
            DaoTelefono dao = new DaoTelefono();
            telefonos = dao.findAll(Telefono.class);
            System.out.println("Telefonos:");
            for (Telefono telefono : telefonos) {
                System.out.print(telefono.get_id());
                System.out.print(", ");
                System.out.print(telefono.get_numero());
                System.out.print(", ");
                System.out.print(telefono.get_estado());
                System.out.print(", ");
                System.out.print(telefono.get_datoUsuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return telefonos;
    }

    @PUT
    @Path( "/updateTelefono/{id}" )
    public TelefonoDto updateTelefono( @PathParam("id") long id , List<TelefonoDto> telefonos)
    {
        TelefonoDto resultado = new TelefonoDto();
        try
        {
            DaoTelefono dao = new DaoTelefono();
            DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();
            for (TelefonoDto telefonoDto : telefonos) {
                Telefono telefono = dao.find(id, Telefono.class);
                telefono.set_numero(telefonoDto.getNumero());
                telefono.set_estado(telefonoDto.getEstado());
                Dato_usuario dato_usuario = daoDatoUsuario.find(telefonoDto.getDatoUsuarioDto().getId(), Dato_usuario.class);
                telefono.set_datoUsuario(dato_usuario);
                Telefono resul = dao.update(telefono);
                resultado.setId(resul.get_id());
            }
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }


}
