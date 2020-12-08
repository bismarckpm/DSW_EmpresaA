package ucab.dsw.servicio;


import lombok.extern.java.Log;
import ucab.dsw.Response.DatoUsuarioResponse;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Log
@Path( "/dato-usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class DatoUsuarioORMWS {

    private Logger logger = Logger.getLogger(DatoUsuarioORMWS.class.getName());

    private DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();

    @POST
    @Path("/crear")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public DatoUsuarioResponse create(Dato_usuarioDto datoUsuarioDto) throws Exception {

        try {

            logger.info("Accediendo al servicio que crea un encuestado");

            Dato_usuario datoUsuario = setterCreateUsuario(datoUsuarioDto);

            logger.info("Finalizando el servicio que crea un encuestado");

            Dato_usuario result = daoDatoUsuario.insert(datoUsuario);

            DatoUsuarioResponse datoUsuarioResponse = setterGetUsuario(result, result.get_id());

            return datoUsuarioResponse;

        }catch (Exception e){

            logger.info("Error al crear un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    @POST
    @Path("/obtener")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public DatoUsuarioResponse getOne(Dato_usuarioDto datoUsuarioDto) throws Exception {
        try {

            logger.info("Accediendo al servicio que obtiene un encuestado");

            Dato_usuario datoUsuario = daoDatoUsuario.find(datoUsuarioDto.getId(), Dato_usuario.class);

            DatoUsuarioResponse datoUsuarioResponse = setterGetUsuario(datoUsuario, datoUsuarioDto.getId());

            logger.info("Finalizando al servicio que obtiene un encuestado");

            return datoUsuarioResponse;

        }catch (Exception e){

            logger.info("Error al servicio que obtiene un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }


    public Boolean updateStatus(Dato_usuarioDto datoUsuarioDto) throws Exception {
        try {

            logger.info("Accediendo al servicio que elimina logicamente un encuestado");

            Dato_usuario datoUsuario = daoDatoUsuario.find(datoUsuarioDto.getId(), Dato_usuario.class);
            datoUsuario.set_estado("I");
            Dato_usuario datoUsuarioUpdate = daoDatoUsuario.update(datoUsuario);

            Boolean result = (datoUsuarioUpdate.get_estado().equals("I")) ?  true :false;

            logger.info("Finalizando al servicio que elimina logicamente un encuestado");

            return result;

        }catch (Exception e){

            logger.info("Error al servicio que obtiene un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    private DatoUsuarioResponse setterGetUsuario(Dato_usuario datoUsuario, long id){

        DatoUsuarioResponse datoUsuarioResponse = new DatoUsuarioResponse(id, datoUsuario.get_cedula(), datoUsuario.get_estado(), datoUsuario.get_primerNombre(),
                datoUsuario.get_segundoNombre(), datoUsuario.get_primerApellido(), datoUsuario.get_segundoApellido(), datoUsuario.get_sexo(),
                datoUsuario.get_fechaNacimiento(), datoUsuario.get_estadoCivil(), datoUsuario.get_disponibilidadEnLinea(), datoUsuario.get_conCuantasPersonasVive(),
                datoUsuario.get_nivelAcademico().get_nivel(), datoUsuario.get_nivelEconomico().get_nivel(), datoUsuario.get_lugar().get_nombre(),
                datoUsuario.get_lugar().get_tipo(), datoUsuario.get_lugar().get_categoriaSocioEconomica(), datoUsuario.get_ocupacion().get_nombre());

        return datoUsuarioResponse;

    }

    private Dato_usuario setterCreateUsuario(Dato_usuarioDto usuarioDto) throws ParseException {

        Dato_usuario datoUsuario = new Dato_usuario();

        Lugar lugar= new Lugar(usuarioDto.getLugarDto().getId());
        Nivel_academico nivelAcademico = new Nivel_academico(usuarioDto.getNivelAcademicoDto().getId());
        Ocupacion ocupacion = new Ocupacion(usuarioDto.getOcupacionDto().getId());
        Nivel_economico nivelEconomico = new Nivel_economico(usuarioDto.getNivelEconomicoDto().getId());


        datoUsuario.set_cedula(usuarioDto.getCedula());
        datoUsuario.set_primerNombre(usuarioDto.getPrimerNombre());
        datoUsuario.set_estado("A");
        datoUsuario.set_segundoNombre(usuarioDto.getSegundoNombre());
        datoUsuario.set_primerApellido(usuarioDto.getPrimerApellido());
        datoUsuario.set_segundoApellido(usuarioDto.getSegundoApellido());
        datoUsuario.set_sexo(usuarioDto.getSexo());
        datoUsuario.set_fechaNacimiento(formatDateStringToDate(usuarioDto.getFechaNacimiento()));
        datoUsuario.set_estadoCivil(usuarioDto.getEstadoCivil());
        datoUsuario.set_disponibilidadEnLinea(usuarioDto.getDisponibilidadEnLinea());
        datoUsuario.set_conCuantasPersonasVive(usuarioDto.getConCuantasPersonasVive());
        datoUsuario.set_lugar(lugar);
        datoUsuario.set_nivelAcademico(nivelAcademico);
        datoUsuario.set_nivelEconomico(nivelEconomico);
        datoUsuario.set_ocupacion(ocupacion);

        return datoUsuario;
    }


    private Date formatDateStringToDate(String dateFormat) throws ParseException {
        DateFormat date = new SimpleDateFormat("dd-MM-yyyy");

        Date dateUpdate = date.parse(dateFormat);

        return dateUpdate;
    }

}
