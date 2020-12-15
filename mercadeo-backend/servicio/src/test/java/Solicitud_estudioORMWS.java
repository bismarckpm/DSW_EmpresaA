import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Nivel_economico;
import ucab.dsw.entidades.Solicitud_estudio;

import java.text.SimpleDateFormat;
import java.util.List;

public class Solicitud_estudioORMWS {
    
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
        solicitud_estudioDto.setCantidadHijos("1");
        solicitud_estudioDto.setGeneroHijos("masculino");
        solicitud_estudioDto.setEdadMinimaHijos("1");
        solicitud_estudioDto.setEdadMaximaHijos("4");
        solicitud_estudioDto.setConCuantasPersonasVive("4");
        solicitud_estudioDto.setDisponibilidadEnLinea("Si");
        solicitud_estudioDto.setEstado( "A" );

        Nivel_economicoDto nivel_economico = new Nivel_economicoDto( 1);
        solicitud_estudioDto.setNivelEconomicoDto( nivel_economico );

        OcupacionDto ocupacion = new OcupacionDto( 1);
        solicitud_estudioDto.setOcupacionDto( ocupacion );

        UsuarioDto usuario = new UsuarioDto( 1);
        solicitud_estudioDto.setUsuarioDto( usuario );

        ProductoDto producto = new ProductoDto( 1);
        solicitud_estudioDto.setProductoDto( producto );

        Solicitud_estudioDto resultado = servicio.addSolicitud_estudio( solicitud_estudioDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteSolicitud_estudioTest() throws Exception{
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Solicitud_estudioDto resultado = servicio.deleteSolicitud_estudio(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showSolicitud_estudiosTest() throws Exception{
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        List<Solicitud_estudio> resultado = servicio.showSolicitud_estudios();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateSolicitud_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Solicitud_estudioDto solicitud_estudioDto = new Solicitud_estudioDto();
        solicitud_estudioDto.setDescripcionSolicitud( "Descripcion" );
        solicitud_estudioDto.setGeneroPoblacional( "Masculino" );
        solicitud_estudioDto.setFechaPeticion(new SimpleDateFormat("dd/MM/yyyy").parse("05/12/2020"));
        solicitud_estudioDto.setEdadMinimaPoblacion("15");
        solicitud_estudioDto.setEdadMaximaPoblacion("25");
        solicitud_estudioDto.setCantidadHijos("2");
        solicitud_estudioDto.setGeneroHijos("masculino");
        solicitud_estudioDto.setEdadMinimaHijos("1");
        solicitud_estudioDto.setEdadMaximaHijos("2");
        solicitud_estudioDto.setConCuantasPersonasVive("2");
        solicitud_estudioDto.setDisponibilidadEnLinea("Si");
        solicitud_estudioDto.setEstado( "I" );

        Nivel_economicoDto nivel_economico = new Nivel_economicoDto( 1);
        solicitud_estudioDto.setNivelEconomicoDto( nivel_economico );

        OcupacionDto ocupacion = new OcupacionDto( 1);
        solicitud_estudioDto.setOcupacionDto( ocupacion );

        UsuarioDto usuario = new UsuarioDto( 1);
        solicitud_estudioDto.setUsuarioDto( usuario );

        ProductoDto producto = new ProductoDto( 1);
        solicitud_estudioDto.setProductoDto( producto );
        Solicitud_estudioDto resultado = servicio.updateSolicitud_estudio( 1, solicitud_estudioDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
