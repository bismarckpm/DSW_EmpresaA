import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.entidades.Solicitud_estudio;
import ucab.dsw.entidades.Subcategoria;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;

public class Solicitud_estudioORMWS {
/*
    *//**
     * Este test prueba el registro de una nueva solicitud de estudio
     *
     */
    @Test
    public void addSolicitud_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Solicitud_estudioDto solicitud_estudioDto = new Solicitud_estudioDto();
        solicitud_estudioDto.setDescripcionSolicitud( "Descripcion" );
        solicitud_estudioDto.setGeneroPoblacional( "Masculino" );
        solicitud_estudioDto.setFechaPeticion(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2020"));
        solicitud_estudioDto.setEdadMinimaPoblacion("10");
        solicitud_estudioDto.setEdadMaximaPoblacion("20");
        solicitud_estudioDto.setConCuantasPersonasVive("4");
        solicitud_estudioDto.setDisponibilidadEnLinea("Si");
        solicitud_estudioDto.setEstatus("En Espera");

        Nivel_economicoDto nivel_economico = new Nivel_economicoDto( 1);
        solicitud_estudioDto.setNivelEconomicoDto( nivel_economico );

        OcupacionDto ocupacion = new OcupacionDto( 1);
        solicitud_estudioDto.setOcupacionDto( ocupacion );

        UsuarioDto usuario = new UsuarioDto( 2);
        solicitud_estudioDto.setUsuarioDto( usuario );

        ProductoDto producto = new ProductoDto( 1);
        solicitud_estudioDto.setProductoDto( producto );

        Response resultado = servicio.addSolicitud_estudio( solicitud_estudioDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Solicitud_estudio solicitud_estudio = (Solicitud_estudio) responseDto.getObjeto();
        Assert.assertNotEquals( solicitud_estudio.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las solicitudes de estudio
     *
     */
    @Test
    public void showSolicitud_estudiosTest() throws Exception{
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Response resultado = servicio.showSolicitud_estudios();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Solicitud_estudio> solicitud_estudios = (List<Solicitud_estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",solicitud_estudios.isEmpty());
    }

    /**
     * Este test prueba la actualización de una solicitud de estudio
     *
     */
    @Test
    public void updateSolicitud_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Solicitud_estudioDto solicitud_estudioDto = new Solicitud_estudioDto();
        solicitud_estudioDto.setDescripcionSolicitud( "Descripcion" );
        solicitud_estudioDto.setGeneroPoblacional( "Masculino" );
        solicitud_estudioDto.setFechaPeticion(new SimpleDateFormat("dd/MM/yyyy").parse("05/12/2021"));
        solicitud_estudioDto.setEdadMinimaPoblacion("15");
        solicitud_estudioDto.setEdadMaximaPoblacion("25");
        solicitud_estudioDto.setConCuantasPersonasVive("2");
        solicitud_estudioDto.setDisponibilidadEnLinea("Si");
        solicitud_estudioDto.setEstatus("En Espera");
        solicitud_estudioDto.setEstatus( "Finalizado" );
        solicitud_estudioDto.setEstado( "I" );

        Nivel_economicoDto nivel_economico = new Nivel_economicoDto( 1);
        solicitud_estudioDto.setNivelEconomicoDto( nivel_economico );

        OcupacionDto ocupacion = new OcupacionDto( 1);
        solicitud_estudioDto.setOcupacionDto( ocupacion );

        UsuarioDto usuario = new UsuarioDto( 2);
        solicitud_estudioDto.setUsuarioDto( usuario );

        ProductoDto producto = new ProductoDto( 1);
        solicitud_estudioDto.setProductoDto( producto );
        Response resultado = servicio.updateSolicitud_estudio( 1, solicitud_estudioDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Solicitud_estudio solicitud_estudio = (Solicitud_estudio) responseDto.getObjeto();
        System.out.println(solicitud_estudio.get_id());
        Assert.assertNotEquals( solicitud_estudio.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de las solicitudes de estudio de un cliente
     *
     */
    @Test
    public void showSolicitudesClienteTest() throws Exception{
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Response resultado = servicio.showSolicitud_estudio_usuario(6);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Solicitud_estudio> solicitud_estudios = (List<Solicitud_estudio>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",solicitud_estudios.isEmpty());
    }

    /**
     * Este test prueba la obtención de una solicitud_estudio especifica
     *
     */
    @Test
    public void consultarSolicitud_estudioTest() throws Exception{
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Response resultado = servicio.consultarSolicitud_estudio(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Solicitud_estudio solicitud_estudio = (Solicitud_estudio) responseDto.getObjeto();
        System.out.println(solicitud_estudio.get_id());
        Assert.assertNotEquals(solicitud_estudio, null);
    }

    /**
     * Este test prueba la inactivación de una solicitud de estudio
     *
     */
    @Test
    public void inactivarSolicitud_estudioTest() throws Exception{
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Solicitud_estudioDto solicitud = new Solicitud_estudioDto();
        solicitud.setId(1);
        solicitud.setEstado("I");
        Response resultado = servicio.inactivarSolicitud_estudio(1, solicitud);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Solicitud_estudio solicitud_estudio = (Solicitud_estudio) responseDto.getObjeto();
        System.out.println(solicitud_estudio.get_id());
        Assert.assertNotEquals( solicitud_estudio.get_id(), 0);
    }
}
