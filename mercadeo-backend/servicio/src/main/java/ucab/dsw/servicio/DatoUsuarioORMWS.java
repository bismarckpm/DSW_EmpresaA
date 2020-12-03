package ucab.dsw.servicio;


import lombok.extern.java.Log;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
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
    public Dato_usuario create(Dato_usuarioDto datoUsuarioDto) throws Exception {

        try {

            logger.info("Accediendo al servicio que crea un encuestado");

            Dato_usuario datoUsuario = setterCreateUsuario(datoUsuarioDto);

            logger.info("Finalizando el servicio que crea un encuestado");

            return daoDatoUsuario.insert(datoUsuario);

        }catch (Exception e){

            logger.info("Error al crear un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    @PUT
    @Path("/actualizar")
    public Dato_usuario update(Dato_usuarioDto datoUsuarioDto) throws Exception {
        try {

            logger.info("Accediendo al servicio que actualiza datos de un encuestado");

            Dato_usuario datoUsuario = setterUpdateUsuario(datoUsuarioDto);

            Dato_usuario datoUsuarioUpdate = daoDatoUsuario.update(datoUsuario);

            logger.info("Finalizando al servicio que actualiza datos de un encuestado");

            return datoUsuarioUpdate;

        }catch (Exception e){

            logger.info("Error al servicio que  actualiza datos de un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    @POST
    @Path("/obtener")
    public Dato_usuario getOne(Dato_usuarioDto datoUsuarioDto) throws Exception {
        try {

            logger.info("Accediendo al servicio que obtiene un encuestado");

            Dato_usuario datoUsuario = daoDatoUsuario.find(datoUsuarioDto.getId(), Dato_usuario.class);


            logger.info("Finalizando al servicio que obtiene un encuestado");

            return datoUsuario;

        }catch (Exception e){

            logger.info("Error al servicio que obtiene un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    @PUT
    @Path("/eliminar")
    public Dato_usuario updateStatus(Dato_usuarioDto datoUsuarioDto) throws Exception {
        try {

            logger.info("Accediendo al servicio que elimina logicamente un encuestado");

            Dato_usuario datoUsuario = daoDatoUsuario.find(datoUsuarioDto.getId(), Dato_usuario.class);
            datoUsuario.set_estado("I");
            Dato_usuario datoUsuarioUpdate = daoDatoUsuario.update(datoUsuario);


            logger.info("Finalizando al servicio que elimina logicamente un encuestado");

            return datoUsuarioUpdate;

        }catch (Exception e){

            logger.info("Error al servicio que obtiene un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }


    public Dato_usuario eliminar(Dato_usuarioDto datoUsuarioDto) throws Exception {
        try {

            logger.info("Accediendo al servicio que elimina un encuestado");

            Dato_usuario datoUsuario = daoDatoUsuario.find(datoUsuarioDto.getId(), Dato_usuario.class);

            Dato_usuario result = daoDatoUsuario.delete(datoUsuario);

            logger.info("Finalizando el servicio que elimina un encuestado");

            return result;

        }catch (Exception e){

            logger.info("Error al eliminar un encuestado: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    private Dato_usuario setterCreateUsuario(Dato_usuarioDto usuarioDto){

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
        datoUsuario.set_fechaNacimiento(usuarioDto.getFechaNacimiento());
        datoUsuario.set_estadoCivil(usuarioDto.getEstadoCivil());
        datoUsuario.set_disponibilidadEnLinea(usuarioDto.getDisponibilidadEnLinea());
        datoUsuario.set_conCuantasPersonasVive(usuarioDto.getConCuantasPersonasVive());
        datoUsuario.set_lugar(lugar);
        datoUsuario.set_nivelAcademico(nivelAcademico);
        datoUsuario.set_nivelEconomico(nivelEconomico);
        datoUsuario.set_ocupacion(ocupacion);

        return datoUsuario;
    }

    private Dato_usuario setterUpdateUsuario(Dato_usuarioDto usuarioDto){

        Dato_usuario datoUsuario = new Dato_usuario(usuarioDto.getId());

        Lugar lugar= new Lugar(usuarioDto.getLugarDto().getId());
        Nivel_academico nivelAcademico = new Nivel_academico(usuarioDto.getNivelAcademicoDto().getId());
        Ocupacion ocupacion = new Ocupacion(usuarioDto.getOcupacionDto().getId());
        Nivel_economico nivelEconomico = new Nivel_economico(usuarioDto.getNivelEconomicoDto().getId());


        datoUsuario.set_cedula(usuarioDto.getCedula());
        datoUsuario.set_primerNombre(usuarioDto.getPrimerNombre());
        datoUsuario.set_segundoNombre(usuarioDto.getSegundoNombre());
        datoUsuario.set_primerApellido(usuarioDto.getPrimerApellido());
        datoUsuario.set_segundoApellido(usuarioDto.getSegundoApellido());
        datoUsuario.set_sexo(usuarioDto.getSexo());
        datoUsuario.set_estado("A");
        datoUsuario.set_fechaNacimiento(usuarioDto.getFechaNacimiento());
        datoUsuario.set_estadoCivil(usuarioDto.getEstadoCivil());
        datoUsuario.set_disponibilidadEnLinea(usuarioDto.getDisponibilidadEnLinea());
        datoUsuario.set_conCuantasPersonasVive(usuarioDto.getConCuantasPersonasVive());
        datoUsuario.set_lugar(lugar);
        datoUsuario.set_nivelAcademico(nivelAcademico);
        datoUsuario.set_nivelEconomico(nivelEconomico);
        datoUsuario.set_ocupacion(ocupacion);

        return datoUsuario;
    }

}
