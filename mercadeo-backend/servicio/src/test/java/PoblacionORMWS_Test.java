import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Poblacion;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Usuario;

import javax.ws.rs.core.Response;
import java.util.List;

public class PoblacionORMWS_Test {
/*
    *//**
     * Este test prueba el registro de una nueva población
     *
     */
    @Test
    public void addPoblacionTest() throws Exception {
        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();
        UsuarioDto usuarioDto = new UsuarioDto(8);
        EstudioDto estudioDto = new EstudioDto(1);

        PoblacionDto poblacionDto = new PoblacionDto();
        poblacionDto.setEstado( "A" );
        poblacionDto.setEstudio(estudioDto);
        poblacionDto.setUsuario(usuarioDto);
        Response resultado = servicio.addPoblacion( poblacionDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Poblacion poblacion = (Poblacion) responseDto.getObjeto();
        Assert.assertNotEquals( poblacion.get_id(), 0  );
    }

    /**
     * Este test prueba la actualización de una población de estudio
     *
     */
    @Test
    public void editPoblacionTest() throws Exception{

        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();
        PoblacionDto poblacionDto = new PoblacionDto(3);

        UsuarioDto usuarioDto = new UsuarioDto(8);
        EstudioDto estudioDto = new EstudioDto(2);

        poblacionDto.setEstado( "I" );
        poblacionDto.setEstudio(estudioDto);
        poblacionDto.setUsuario(usuarioDto);

        Response resultado = servicio.editPoblacion (poblacionDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Poblacion poblacion = (Poblacion) responseDto.getObjeto();
        System.out.println(poblacion.get_id());
        Assert.assertNotEquals( poblacion.get_id(), 0);
    }

    /**
     * Este test prueba el registro de la población recomendada de un estudio
     *
     */
    @Test
    public void addPoblacionRecomendadaTest() throws Exception{

        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();

        Response resultado = servicio.addPoblacionRecomendada (2, 2);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Poblacion poblacion = (Poblacion) responseDto.getObjeto();
        System.out.println(poblacion.get_id());
        Assert.assertNotEquals( poblacion.get_usuario().get_id(), 0);
    }

    /**
     * Este test prueba la consulta de la población de un estudio
     *
     */
    @Test
    public void obtenerPoblacionEstudioTest() throws Exception{

        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();
        EstudioDto estudioDto = new EstudioDto(1);

        Response resultado = servicio.obtenerPoblacionEstudio (estudioDto.getId());
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Poblacion> poblacion = (List<Poblacion>) responseDto.getObjeto();
        for (Poblacion pob: poblacion)
            System.out.println(pob.get_id() + " "+ pob.get_usuario().get_nombreUsuario());
        Assert.assertFalse("Consulta Realizada con Exito",poblacion.isEmpty());
    }

    /**
     * Este test prueba la consulta de la población
     *
     */
    @Test
    public void obtenerPoblacionTest() throws Exception{

        ucab.dsw.servicio.PoblacionORMWS servicio = new ucab.dsw.servicio.PoblacionORMWS();
        EstudioDto estudioDto = new EstudioDto(1);

        Response resultado = servicio.obtenerPoblacion (estudioDto.getId());
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Usuario> poblacion = (List<Usuario>) responseDto.getObjeto();
        for (Usuario pob: poblacion)
            System.out.println(pob.get_id() + " "+ pob.get_nombreUsuario());
        Assert.assertFalse("Consulta Realizada con Exito",poblacion.isEmpty());
    }

}
