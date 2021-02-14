import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoDato_usuario;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Telefono;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HijoORMWS_Test {
/*
    *//**
     * Este test prueba el registro de una lista de hijos para un usuario
     *
     */
    @Test
    public void addHijoTest() throws Exception
    {
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();

        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);

        HijoDto hijo1 = new HijoDto();
        hijo1.setFechaNacimiento( new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010") );
        hijo1.setGenero( "Masculino" );
        hijo1.setEstado( "A" );
        hijo1.setDatoUsuarioDto( dato_usuario );

        HijoDto hijo2 = new HijoDto();
        hijo2.setFechaNacimiento( new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2015") );
        hijo2.setGenero( "Femenino" );
        hijo2.setEstado( "A" );
        hijo2.setDatoUsuarioDto( dato_usuario );

        HijoDto hijo3 = new HijoDto();
        hijo3.setFechaNacimiento( new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2020") );
        hijo3.setGenero( "Femenino" );
        hijo3.setEstado( "A" );
        hijo3.setDatoUsuarioDto( dato_usuario );

        List<HijoDto> hijos = new ArrayList<HijoDto>();

        hijos.add(hijo1);
        hijos.add(hijo2);
        hijos.add(hijo3);

        Response resultado = servicio.addHijo( hijos );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Hijo> hijop = (List<Hijo>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",hijop.isEmpty());
    }

    /**
     * Este test prueba la obtención de todos los hijos registrados
     *
     */
    @Test
    public void showHijosTest() throws Exception{
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        Response resultado = servicio.showHijos();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Hijo> hijop = (List<Hijo>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",hijop.isEmpty());
    }

    /**
     * Este test prueba la actualización de la lista de hijos de un usuario
     *
     */
    @Test
    public void updateHijoTest() throws Exception
    {
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);

        HijoDto hijo1 = new HijoDto(1);
        hijo1.setFechaNacimiento( new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010") );
        hijo1.setGenero( "Femenino" );
        hijo1.setEstado( "A" );
        hijo1.setDatoUsuarioDto( dato_usuario );

        HijoDto hijo2 = new HijoDto(2);
        hijo2.setFechaNacimiento( new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2015") );
        hijo2.setGenero( "Masculino" );
        hijo2.setEstado( "A" );
        hijo2.setDatoUsuarioDto( dato_usuario );

        HijoDto hijo3 = new HijoDto(3);
        hijo3.setFechaNacimiento( new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2020") );
        hijo3.setGenero( "Masculino" );
        hijo3.setEstado( "A" );
        hijo3.setDatoUsuarioDto( dato_usuario );

        List<HijoDto> hijos = new ArrayList<HijoDto>();

        hijos.add(hijo1);
        hijos.add(hijo2);
        hijos.add(hijo3);

        Response resultado = servicio.updateHijo( hijos );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Hijo> hijop = (List<Hijo>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",hijop.isEmpty());
    }

    /**
     * Este test prueba la obtención de la lista de hijos para un usuario
     *
     */
    @Test
    public void obtenerHijosUsuarioTest() throws Exception{
        ucab.dsw.servicio.HijoORMWS servicio = new ucab.dsw.servicio.HijoORMWS();
        Response resultado = servicio.obtenerHijosUsuario(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Hijo> hijop = (List<Hijo>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",hijop.isEmpty());
    }
}
