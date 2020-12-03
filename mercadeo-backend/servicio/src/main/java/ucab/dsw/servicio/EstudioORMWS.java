package ucab.dsw.servicio;

import ucab.dsw.Response.EstudioResponse;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )

public class EstudioORMWS {

    @POST
    @Path( "/addEstudio" )
    public EstudioDto addEstudio(EstudioDto estudioDto )
    {
        EstudioDto resultado = new EstudioDto();
        try
        {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = new Estudio();
            estudio.set_nombre( estudioDto.getNombre() );
            estudio.set_tipoDeInstrumento( estudioDto.getTipoInstrumento() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFinal() );
            estudio.set_estatus( estudioDto.getStatus() );
            estudio.set_estado( estudioDto.getEstado() );
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(estudioDto.getEstudioSolicitudDto().getId());
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getEstudioUsuarioDto().getId());
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

    @GET
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
            estudio.set_tipoDeInstrumento( estudioDto.getTipoInstrumento() );
            estudio.set_fechaInicio( estudioDto.getFechaInicio() );
            estudio.set_fechaFin( estudioDto.getFechaFinal() );
            estudio.set_estatus( estudioDto.getStatus() );
            estudio.set_estado( estudioDto.getEstado() );
            Solicitud_estudio solicitud_estudio = new Solicitud_estudio(estudioDto.getEstudioSolicitudDto().getId());
            estudio.set_solicitudEstudio( solicitud_estudio);
            Usuario usuario = new Usuario(estudioDto.getEstudioUsuarioDto().getId());
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

    @GET
    @Path("/buscar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public EstudioResponse getEstudio(@PathParam("id") long id) throws Exception {

        try {

            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);

            EstudioResponse estudioResponse = new EstudioResponse(estudio.get_id(), estudio.get_nombre(), estudio.get_tipoDeInstrumento(),
                    formatDateToString(estudio.get_fechaInicio()), formatDateToString(estudio.get_fechaFin()),
                    estudio.get_estatus());

            return estudioResponse;

        }catch (Exception e){

            throw new Exception(e.getMessage());

        }

    }

    @GET
    @Path("listar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EstudioResponse> getAllByUser(@PathParam("id") long id) throws Exception {

        try {

            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudioList = dao.findAll(Estudio.class);
            List<EstudioResponse> estudioUpdate = new ArrayList<>();

            if(id == 0) {
                estudioList.stream().filter(i->(i.get_estado().equals("A"))).collect(Collectors.toList()).forEach(i->{
                    try {

                        estudioUpdate.add(new EstudioResponse(i.get_id(), i.get_nombre(), i.get_tipoDeInstrumento(),
                                formatDateToString(i.get_fechaInicio()), formatDateToString(i.get_fechaFin()),
                                i.get_estatus()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                estudioList.stream().filter(i -> (i.get_usuario().get_id() == id && i.get_estado().equals("A"))).collect(Collectors.toList()).forEach(i -> {
                    try {

                        estudioUpdate.add(new EstudioResponse(i.get_id(), i.get_nombre(), i.get_tipoDeInstrumento(),
                                formatDateToString(i.get_fechaInicio()), formatDateToString(i.get_fechaFin()),
                                i.get_estatus()));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
            }
            return estudioUpdate;

        }catch (Exception e){

            throw new Exception(e.getMessage());

        }

    }

    private String formatDateToString(Date date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String dateUpdate = sdf.format(date);

        return dateUpdate;
    }
}
