package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.SolicitarEstudioResponse;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.Solicitud_estudioDto;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Log
@Path( "/solicitar-estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SolicitudEstudioORMWS {

    private Logger logger = Logger.getLogger(SolicitudEstudioORMWS.class.getName());

    @GET
    @Path("/listar/{id}")
    public List<SolicitarEstudioResponse> getAllByIdUser(@PathParam("id") long id) throws Exception {

        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        List<SolicitarEstudioResponse> solicitudEstudioListUpdate = new ArrayList<>();

        try {
                List<Solicitud_estudio> solicitudEstudioList = daoSolicitud_estudio.findAll(Solicitud_estudio.class);

                solicitudEstudioList.stream().filter(i->(i.get_usuario().get_id() == id)).collect(Collectors.toList()).forEach(i->{
                    try {


                        //solicitudEstudioListUpdate.add(setterSolicitarEstudio(i));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                });

                return solicitudEstudioListUpdate;
        }catch (Exception e){

            throw new Exception(e);

        }
    }

    @GET
    @Path("/listar")
    public List<SolicitarEstudioResponse> getAll() throws Exception {

        DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
        List<SolicitarEstudioResponse> solicitudEstudioListUpdate = new ArrayList<>();

        try {
            List<Solicitud_estudio> solicitudEstudioList = daoSolicitud_estudio.findAll(Solicitud_estudio.class);

            solicitudEstudioList.stream().forEach(i->{
                try {


                    //solicitudEstudioListUpdate.add(setterSolicitarEstudio(i));

                } catch (Exception e) {

                    e.printStackTrace();

                }
            });

            return solicitudEstudioListUpdate;
        }catch (Exception e){

            throw new Exception(e);

        }
    }

   /* private SolicitarEstudioResponse setterSolicitarEstudio(Solicitud_estudio solicitud_estudio) throws ParseException {
        return new SolicitarEstudioResponse(solicitud_estudio.get_id(), solicitud_estudio.get_descripcionSolicitud(), solicitud_estudio.get_generoPoblacional(),
                formatDateToString(solicitud_estudio.get_fechaPeticion()),
                solicitud_estudio.get_estado(),  solicitud_estudio.get_conCuantasPersonasVive(), solicitud_estudio.get_disponibilidadEnLinea(),
                solicitud_estudio.get_producto(), solicitud_estudio.get_usuario(), solicitud_estudio.get_ocupacion(),
                solicitud_estudio.get_nivelEconomico());
    }*/

    private String formatDateToString(Date date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        String dateUpdate = sdf.format(date);

        return dateUpdate;
    }
}
