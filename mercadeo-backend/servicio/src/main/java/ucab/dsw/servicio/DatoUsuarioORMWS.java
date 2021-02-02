package ucab.dsw.servicio;


import lombok.extern.java.Log;
import ucab.dsw.entidades.Response.DatoUsuarioResponse;
//import ucab.dsw.Response.IdDatosUsuarioResponse;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Log
@Path( "/dato-usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class DatoUsuarioORMWS {

    private Logger logger = Logger.getLogger(DatoUsuarioORMWS.class.getName());

    private DaoDato_usuario daoDatoUsuario = new DaoDato_usuario();


    /**
     * Este método registra en el sistema la informacion de encuestado de un usuario
     *
     * @param  "Dato_usuarioDto"  informacion del encuestado a ser registrada
     * @return      la Dato_usuarioDto que ha sido registrada en el sistema
     */
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

    /**
     * Este método Edita en el sistema la informacion de encuestado de un usuario
     *
     * @param  "Dato_usuarioDto"  informacion del encuestado a ser editado
     * @param  "id" id del dato_usuario a editado
     * @return      la Dato_usuarioDto que ha sido editado en el sistema
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public Dato_usuarioDto editDato_usuario(@PathParam("id") long id ,Dato_usuarioDto usuarioDto) throws Exception
    {
        Dato_usuarioDto resultado = new Dato_usuarioDto();
        try
        {
            Dato_usuario datoUsuario = daoDatoUsuario.find(id, Dato_usuario.class);

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
            datoUsuario.set_medioComunicacion(usuarioDto.getMedioComunicacion());
            datoUsuario.set_lugar(lugar);
            datoUsuario.set_nivelAcademico(nivelAcademico);
            datoUsuario.set_nivelEconomico(nivelEconomico);
            datoUsuario.set_ocupacion(ocupacion);
            Dato_usuario resul = daoDatoUsuario.update (datoUsuario );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando los datos de un usuario");
        }
        return  resultado;
    }

    /**
     * Este método retorna la lista de datos usuario
     *
     * @return      la lista de usuarios de la bd
     */
    @GET
    @Path("/listar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<DatoUsuarioResponse> getAll() throws Exception {
        try {

            logger.info("Accediendo al servicio que obtienes todos los id de la tabla datos usuario");

            List<Dato_usuario> datoUsuarioList = daoDatoUsuario.findAll(Dato_usuario.class);
            List<DatoUsuarioResponse> datoUsuarioResponseList = new ArrayList<>();

            datoUsuarioList.forEach(i->{
                try {
                    datoUsuarioResponseList.add(setterGetUsuario(i, i.get_id()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

            logger.info("Finalizando al servicio que obtienes todos los id de la tabla datos usuario");

            return datoUsuarioResponseList;

        }catch (Exception e){

            logger.info("Error al servicio que obtienes todos los id de la tabla datos usuario: "+ e.getMessage());

            throw new Exception(e.getMessage());
        }
    }

    /**
     * Este método retorna un dato usuario especifico
     *
     * @param  "id"  id del dato usuario a buscar
     * @return      la Dato_usuarioDto que ha sido encontrada en el sistema
     */
    @GET
    @Path ("/consultar/{id}")
    public Dato_usuario consultarDato_usuario(@PathParam("id") long id) throws Exception {

        try {
            DaoDato_usuario dato_usuarioDao = new DaoDato_usuario();
            return dato_usuarioDao.find(id, Dato_usuario.class);
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los datos de un usuario");
        }
    }


    /**
     * Este método obtiene del sistema un encuestado
     *
     * @param  "datoUsuario"  usuario a ser setteado
     * @param  "id"  id del Dato_usuario a ser setteado
     * @return      el DatoUsuarioResponse que ha sido setteado en el sistema
     */
    private DatoUsuarioResponse setterGetUsuario(Dato_usuario datoUsuario, long id) throws ParseException {

        DatoUsuarioResponse datoUsuarioResponse = new DatoUsuarioResponse(id, datoUsuario.get_cedula(), datoUsuario.get_estado(), datoUsuario.get_primerNombre(),
                datoUsuario.get_segundoNombre(), datoUsuario.get_primerApellido(), datoUsuario.get_segundoApellido(), datoUsuario.get_sexo(),
                datoUsuario.get_fechaNacimiento(), datoUsuario.get_estadoCivil(), datoUsuario.get_disponibilidadEnLinea(), datoUsuario.get_conCuantasPersonasVive(),
                datoUsuario.get_nivelAcademico().get_nivel(), datoUsuario.get_nivelEconomico().get_nivel(), datoUsuario.get_lugar().get_nombre(),
                datoUsuario.get_lugar().get_tipo(), datoUsuario.get_lugar().get_categoriaSocioEconomica(), datoUsuario.get_ocupacion().get_nombre());

        return datoUsuarioResponse;

    }

    /**
     * Este método settea en el sistema un nuevo encuestado
     *
     * @param  "Dato_usuarioDto"  usuario a ser setteado
     * @return      el Dato_usuarioDto que ha sido setteado en el sistema
     */
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
        datoUsuario.set_fechaNacimiento(usuarioDto.getFechaNacimiento());
        datoUsuario.set_estadoCivil(usuarioDto.getEstadoCivil());
        datoUsuario.set_disponibilidadEnLinea(usuarioDto.getDisponibilidadEnLinea());
        datoUsuario.set_conCuantasPersonasVive(usuarioDto.getConCuantasPersonasVive());
        datoUsuario.set_medioComunicacion(usuarioDto.getMedioComunicacion());
        datoUsuario.set_lugar(lugar);
        datoUsuario.set_nivelAcademico(nivelAcademico);
        datoUsuario.set_nivelEconomico(nivelEconomico);
        datoUsuario.set_ocupacion(ocupacion);

        return datoUsuario;
    }


    /**
     * Este los datos del usuario a partir del id de un usuario
     *
     * @param  "id_usuario"  id del usuario
     * @return      Dato_usuario de ese usuario
     */
    @GET
    @Path ("/consultarPorUsuario/{id_usuario}")
    public Dato_usuario consultarDato_usuarioPorUsuario(@PathParam("id_usuario") long id_usuario) throws Exception {

        try {
            DaoDato_usuario dato_usuarioDao = new DaoDato_usuario();
            Dato_usuario datus = dato_usuarioDao.getPorUsuario(id_usuario);
            return datus;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los datos de un usuario");
        }
    }

}
