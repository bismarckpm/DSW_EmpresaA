package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PoblacionDto;
import ucab.dsw.entidades.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/poblacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PoblacionORMWS {

    /**
     * Este método registra en el sistema nueva informacion de producto y presentacion a un producto
     *
     * @param  "PoblacionDto"  Poblacion a ser registrada
     * @return      la PoblacionDto que ha sido registrada en el sistema
     */
    @POST
    @Path( "/agregar" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public PoblacionDto addPoblacion(PoblacionDto poblacionDto ) throws Exception
    {
        PoblacionDto resultado = new PoblacionDto();
        try
        {
            DaoPoblacion dao = new DaoPoblacion();

            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEstudio daoEstudio = new DaoEstudio();

            Usuario usuario = daoUsuario.find(poblacionDto.getUsuario().getId(), Usuario.class);
            Estudio estudio = daoEstudio.find(poblacionDto.getEstudio().getId(), Estudio.class);
            Poblacion poblacion = new Poblacion();

            poblacion.set_estado( "A" );
            poblacion.set_usuario( usuario);
            poblacion.set_estudio(estudio);

            Poblacion resul = dao.insert( poblacion );
            resultado.setId( resul.get_id() );

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.CreateException( "Error asignando Poblacion");
        }
        return  resultado;
    }

    /**
     * Este método edita en el sistema nueva informacion de Poblacion
     *
     * @param  "PoblacionDto"  Poblacion a ser editada
     * @return      la PoblacionDto que ha sido editado en el sistema
     */
    @PUT
    @Path( "/actualizar/{id}" )
    public PoblacionDto editPoblacion( PoblacionDto poblacionDto) throws  Exception
    {
        PoblacionDto resultado = new PoblacionDto();
        try
        {
            DaoPoblacion dao = new DaoPoblacion();
            Poblacion poblacion = dao.find(poblacionDto.getId(),Poblacion.class);

            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEstudio daoEstudio = new DaoEstudio();

            Usuario usuario = daoUsuario.find(poblacionDto.getUsuario().getId(), Usuario.class);
            Estudio estudio = daoEstudio.find(poblacionDto.getEstudio().getId(), Estudio.class);

            poblacion.set_estado( poblacionDto.getEstado() );
            poblacion.set_usuario( usuario);
            poblacion.set_estudio(estudio);

            Poblacion resul = dao.update (poblacion );
            resultado.setId(resul.get_id());

        }
        catch ( Exception ex )
        {
            throw new ucab.dsw.excepciones.UpdateException( "Error actualizando la Poblacion");
        }
        return  resultado;
    }

    @POST
    @Path( "/PoblacionRecomendada/{idSolicitud}/{idEstudio}" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public PoblacionDto addPoblacionRecomendada(@PathParam("idSolicitud") long idSolicitud,@PathParam("idEstudio") long idEstudio ) throws Exception{
        PoblacionDto resultado = new PoblacionDto();
        try{
            DaoSolicitud_estudio daoSolicitud_estudio = new DaoSolicitud_estudio();
            DaoPoblacion daoPoblacion =new DaoPoblacion();
            DaoEstudio daoEstudio =new DaoEstudio();

            Estudio estudio = daoEstudio.find(idEstudio, Estudio.class);
            List<Usuario> listaPoblacion = daoSolicitud_estudio.listarPoblacionEstudio(idSolicitud);

            for (Usuario user : listaPoblacion){
                Poblacion poblacion = new Poblacion();
                poblacion.set_estado("A");
                poblacion.set_estudio(estudio);
                poblacion.set_usuario(user);
                Poblacion resul = daoPoblacion.update (poblacion );
                resultado.setId(resul.get_id());
            }

        }catch( Exception ex ){
            throw new ucab.dsw.excepciones.GetException( "Error consultando Poblacion");
        }

        return resultado;
    }

    /**
     * Este método retorna los estudios a los que ha respondido un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/poblacionEstudio/{idEstudio}")
    public List<Poblacion> obtenerPoblacionEstudio(@PathParam("idEstudio") long id) throws Exception{

        try {
            DaoPoblacion dao = new DaoPoblacion();
            List<Poblacion> poblacion = dao.listarPoblacionEstudio(id);

            return poblacion;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios respondidos por un encuestado");
        }

    }

    /**
     * Este método retorna los estudios a los que ha respondido un encuestado
     *
     * @param  id  id del encuestado del cual se desea obtener sus estudios respondidos
     * @return      una lista de estudios a los que ya ha respondido un encuestado
     */
    @GET
    @Path ("/poblacionGeneral/{idEstudio}")
    public List<Usuario> obtenerPoblacion(@PathParam("idEstudio") long id) throws Exception{

        try {
            DaoPoblacion dao = new DaoPoblacion();
            List<Usuario> poblacion = dao.listarPoblacionGeneral(id);

            return poblacion;
        }
        catch(Exception e){
            throw new ucab.dsw.excepciones.GetException( "Error consultando los estudios respondidos por un encuestado");
        }

    }


}
