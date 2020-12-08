package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.Response.TelefonoResponse;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Telefono;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Log
@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoORMWS {

    private DaoTelefono daoTelefono = new DaoTelefono();

    private Logger logger = Logger.getLogger(TelefonoORMWS.class.getName());

    @POST
    @Path("/crear")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public TelefonoResponse create(TelefonoDto telefonoDto) throws Exception {
            try {
                logger.info("Accediendo servicio de creacion de telefono ");

                Telefono telefono = setterTelefono(telefonoDto);

                Telefono telefonoCreate = daoTelefono.insert(telefono);

                logger.info("Finalizando servicio de creacion de telefono ");

                return setteGetTelefonoResponse(telefonoCreate);


            }catch (Exception e){

                logger.info("Ocurrio un error en el servicio de creacion de telefono " + e.getMessage());

                throw new Exception(e);

            }
    }

    @GET
    @Path("/listar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<TelefonoResponse> getList(@PathParam("id") long idUsuario) throws Exception {
        try {
            logger.info("Accediendo servicio que lista un conjunto telefono de una persona ");

            List<Telefono> telefonoList = daoTelefono.findAll(Telefono.class);
            List<TelefonoResponse> telefonoListUpdate = new ArrayList<>();

            telefonoList.stream().filter(i->(i.get_datoUsuario().get_id() == idUsuario && i.get_estado().equals("A")))
                            .collect(Collectors.toList()).forEach(i->{

                     telefonoListUpdate.add(setteGetTelefonoResponse(i));
            });

            logger.info("Finalizando servicio que lista un conjunto telefono de una persona");

            return telefonoListUpdate;


        }catch (Exception e){

            logger.info("Ocurrio un error en el servicio de creacion de telefono " + e.getMessage());

            throw new Exception(e);

        }
    }

    @PUT
    @Path("/modificar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public TelefonoResponse update(TelefonoDto telefonoDto) throws Exception {

        try {
            logger.info("Accediendo servicio que modifica un telefono ");

            Telefono telefono = daoTelefono.find(telefonoDto.getId(), Telefono.class);
            telefono.set_numero(telefonoDto.getNumero());

            Telefono telefonoUpdate = daoTelefono.update(telefono);

            TelefonoResponse telefonoResponse = setteGetTelefonoResponse(telefonoUpdate);


            logger.info("Finalizando servicio que modifica un telefono ");

            return telefonoResponse;


        }catch (Exception e){

            logger.info("Ocurrio un error en el servicio de creacion de telefono " + e.getMessage());

            throw new Exception(e);

        }
    }

    @DELETE
    @Path("/eliminar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public TelefonoResponse delete(@PathParam("id") long id) throws Exception {

        try {
            logger.info("Accediendo servicio que elimina logicamente un telefono ");

            Telefono telefono = daoTelefono.find(id, Telefono.class);
            telefono.set_estado("I");

            Telefono telefonoUpdate = daoTelefono.update(telefono);;

            TelefonoResponse telefonoResponse = setteGetTelefonoResponse(telefonoUpdate);

            logger.info("Finalizando servicio que elimina logicamente un telefono ");

            return telefonoResponse;


        }catch (Exception e){

            logger.info("Ocurrio un error en el servicio que elimina logicamente un telefono " + e.getMessage());

            throw new Exception(e);

        }
    }

    private TelefonoResponse setteGetTelefonoResponse(Telefono telefono){

        TelefonoResponse telefonoResponse = new TelefonoResponse(telefono.get_numero(), telefono.get_estado(), telefono.get_id(), telefono.get_datoUsuario().get_id());

        return telefonoResponse;
    }

    private Telefono setterTelefono(TelefonoDto telefonoDto){

        Telefono telefono = new Telefono();
        Dato_usuario datoUsuario = new Dato_usuario(telefonoDto.getDatoUsuarioDto().getId());

        telefono.set_estado("A");
        telefono.set_numero(telefonoDto.getNumero());
        telefono.set_datoUsuario(datoUsuario);

        return telefono;
    }
}
