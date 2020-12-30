import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.accesodatos.DaoSolicitud_estudio;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Dato_usuario;
import ucab.dsw.entidades.Medio_comunicacion;
import ucab.dsw.entidades.Solicitud_estudio;

import java.util.ArrayList;
import java.util.List;

public class Medio_comunicacionORMWS_Test {

    @Test
    public void addMedio_comunicacionTest() throws Exception
    {
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);

        Medio_comunicacionDto medio1 = new Medio_comunicacionDto();
        medio1.setNombre( "Teléfono" );
        medio1.setEstado( "A" );
        medio1.setDatoUsuarioDto( dato_usuario );

        Medio_comunicacionDto medio2 = new Medio_comunicacionDto();
        medio2.setNombre( "Table" );
        medio2.setEstado( "A" );
        medio2.setDatoUsuarioDto( dato_usuario );

        Medio_comunicacionDto medio3 = new Medio_comunicacionDto();
        medio3.setNombre( "PC" );
        medio3.setEstado( "A" );
        medio3.setDatoUsuarioDto( dato_usuario );

        List<Medio_comunicacionDto> medios = new ArrayList<Medio_comunicacionDto>();

        medios.add(medio1);
        medios.add(medio2);
        medios.add(medio3);


        Medio_comunicacionDto resultado = servicio.addMedio_comunicacion( medios );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteMedio_comunicacionTest() throws Exception{
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        Medio_comunicacionDto resultado = servicio.deleteMedio_comunicacion(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showMedio_comunicacionsTest() throws Exception{
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        List<Medio_comunicacion> resultado = servicio.showMedio_comunicacions();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateMedio_comunicacionTest() throws Exception
    {
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);
        Solicitud_estudioDto solicitud_estudio = new Solicitud_estudioDto( 1);

        Medio_comunicacionDto medio1 = new Medio_comunicacionDto(1);
        medio1.setNombre( "PC" );
        medio1.setEstado( "A" );
        medio1.setDatoUsuarioDto( dato_usuario );

        Medio_comunicacionDto medio2 = new Medio_comunicacionDto(2);
        medio2.setNombre( "Telefono" );
        medio2.setEstado( "A" );
        medio2.setDatoUsuarioDto( dato_usuario );

        Medio_comunicacionDto medio3 = new Medio_comunicacionDto(3);
        medio3.setNombre( "Laptop" );
        medio3.setEstado( "A" );
        medio3.setDatoUsuarioDto( dato_usuario );

        List<Medio_comunicacionDto> medios = new ArrayList<Medio_comunicacionDto>();

        medios.add(medio1);
        medios.add(medio2);
        medios.add(medio3);

        Medio_comunicacionDto resultado = servicio.updateMedio_comunicacion( 1, medios );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void addLista_mediosTest() throws Exception
    {
        ucab.dsw.servicio.Medio_comunicacionORMWS servicio = new ucab.dsw.servicio.Medio_comunicacionORMWS();
        List<Medio_comunicacionDto> listaMedios = new ArrayList<>();
        Medio_comunicacionDto medio_comunicacionDto = new Medio_comunicacionDto();
        medio_comunicacionDto.setNombre( "Primer medio" );
        medio_comunicacionDto.setEstado( "A" );
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto(1);
        medio_comunicacionDto.setDatoUsuarioDto(dato_usuarioDto);
        listaMedios.add(medio_comunicacionDto);

        Medio_comunicacionDto medio_comunicacionDto2 = new Medio_comunicacionDto();
        medio_comunicacionDto2.setNombre( "Segundo medio" );
        medio_comunicacionDto2.setEstado( "A" );
        medio_comunicacionDto2.setDatoUsuarioDto(dato_usuarioDto);
        listaMedios.add(medio_comunicacionDto2);

        Medio_comunicacionDto medio_comunicacionDto3 = new Medio_comunicacionDto();
        medio_comunicacionDto3.setNombre( "Tercer medio" );
        medio_comunicacionDto3.setEstado( "A" );
        medio_comunicacionDto3.setDatoUsuarioDto(dato_usuarioDto);
        listaMedios.add(medio_comunicacionDto3);
        Solicitud_estudioDto resultado = servicio.addLista_medios( 1, listaMedios );
        Assert.assertNotNull( resultado);
    }
}
