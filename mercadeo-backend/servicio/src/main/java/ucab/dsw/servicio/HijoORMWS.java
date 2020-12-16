package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HijoORMWS {

    @PUT
    @Path( "/addHijo" )
    public HijoDto addHijo(HijoDto hijoDto )
    {
        HijoDto resultado = new HijoDto();
        try
        {
            DaoHijo dao = new DaoHijo();
            Hijo hijo = new Hijo();
            hijo.set_fechaNacimiento( formatDateStringToDate(hijoDto.getFechaNacimiento()));
            hijo.set_genero( hijoDto.getGenero() );
            hijo.set_estado( hijoDto.getEstado() );
            Dato_usuario dato_usuario = new Dato_usuario(hijoDto.getDatoUsuarioDto().getId());
            hijo.set_datoUsuario( dato_usuario);
            Hijo resul = dao.insert( hijo );
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    @DELETE
    @Path ("/deleteHijo/{id}")
    public HijoDto deleteHijo (@PathParam("id") long id){
        HijoDto resultado = new HijoDto();

        try{
            DaoHijo dao = new DaoHijo();
            Hijo hijo = dao.find(id, Hijo.class);
            if(hijo != null){
                Hijo result = dao.delete(hijo);
                resultado.setId(result.get_id());
            }
        }
        catch (Exception e){
            String problem = e.getMessage();
        }
        return resultado;
    }

    @GET
    @Path("/showHijo")
    public List<Hijo> showHijos(){
        List<Hijo> hijos = null;
        try{
            DaoHijo dao = new DaoHijo();
            hijos = dao.findAll(Hijo.class);
            System.out.println("Hijos:");
            for (Hijo hijo : hijos) {
                System.out.print(hijo.get_id());
                System.out.print(", ");
                System.out.print(hijo.get_fechaNacimiento());
                System.out.print(", ");
                System.out.print(hijo.get_genero());
                System.out.print(", ");
                System.out.print(hijo.get_estado());
                System.out.print(", ");
                System.out.print(hijo.get_datoUsuario().get_id());
                System.out.print("");
                System.out.println();
            }
        }
        catch(Exception e){
            String problem = e.getMessage();
        }
        return hijos;
    }

    @PUT
    @Path( "/updateHijo/{id}" )
    public HijoDto updateHijo( @PathParam("id") long id , HijoDto hijoDto)
    {
        HijoDto resultado = new HijoDto();
        try
        {
            DaoHijo dao = new DaoHijo();
            Hijo hijo = dao.find(id, Hijo.class);
            hijo.set_fechaNacimiento( formatDateStringToDate(hijoDto.getFechaNacimiento()));
            hijo.set_genero( hijoDto.getGenero() );
            hijo.set_estado( hijoDto.getEstado() );
            Dato_usuario dato_usuario = new Dato_usuario(hijoDto.getDatoUsuarioDto().getId());
            hijo.set_datoUsuario( dato_usuario);
            Hijo resul = dao.update(hijo);
            resultado.setId( resul.get_id() );
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }

    private Date formatDateStringToDate(String dateFormat) throws ParseException {
        DateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        Date dateUpdate = date.parse(dateFormat);

        return dateUpdate;
    }
}
