import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Dato_usuario;

import java.util.List;

public class Dato_usuario_Test {

    @Test
    public void addDato_usuarioTest() throws Exception
    {
        ucab.dsw.servicio.Dato_usuarioORMWS servicio = new ucab.dsw.servicio.Dato_usuarioORMWS();
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto();
        java.util.Date fecha = new java.util.Date("21/11/2020");
        dato_usuarioDto.setCedula( "CedulaEj" );
        dato_usuarioDto.setPrimerNombre( "PrimNom" );
        dato_usuarioDto.setSegundoNombre( "SegNom" );
        dato_usuarioDto.setPrimerApellido( "PrimApe" );
        dato_usuarioDto.setSegundoApellido( "SegApe" );
        dato_usuarioDto.setSexo( "M" );
        dato_usuarioDto.setFechaNacimiento( fecha );
        dato_usuarioDto.setEstadoCivil( "Soltero" );
        dato_usuarioDto.setDisponibilidadEnLinea( "Total" );
        dato_usuarioDto.setConCuantasPersonasVive( "15" );
        dato_usuarioDto.setEstado( "A" );
        Nivel_economicoDto nivel_economico = new Nivel_economicoDto( 1);
        dato_usuarioDto.setNivelEconomicoDto( nivel_economico );
        Nivel_academicoDto nivel_academico = new Nivel_academicoDto( 1);
        dato_usuarioDto.setNivelAcademicoDto( nivel_academico );
        OcupacionDto ocupacion = new OcupacionDto( 1);
        dato_usuarioDto.setOcupacionDto( ocupacion );
        LugarDto lugar = new LugarDto( 1);
        dato_usuarioDto.setLugarDto( lugar );
        Dato_usuarioDto resultado = servicio.addDato_usuario( dato_usuarioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteDato_usuarioTest() throws Exception{
        ucab.dsw.servicio.Dato_usuarioORMWS servicio = new ucab.dsw.servicio.Dato_usuarioORMWS();
        Dato_usuarioDto resultado = servicio.deleteDato_usuario(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showDato_usuarioTest() throws Exception{
        ucab.dsw.servicio.Dato_usuarioORMWS servicio = new ucab.dsw.servicio.Dato_usuarioORMWS();
        List<Dato_usuario> resultado = servicio.showDato_usuarios();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateDato_usuarioTest() throws Exception
    {
        ucab.dsw.servicio.Dato_usuarioORMWS servicio = new ucab.dsw.servicio.Dato_usuarioORMWS();
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto();
        java.util.Date fecha = new java.util.Date("21/12/2020");
        dato_usuarioDto.setCedula( "CedulaEjcambiado" );
        dato_usuarioDto.setPrimerNombre( "PrimNomcambiado" );
        dato_usuarioDto.setSegundoNombre( "SegNomcambiado" );
        dato_usuarioDto.setPrimerApellido( "PrimApecambiado" );
        dato_usuarioDto.setSegundoApellido( "SegApecambiado" );
        dato_usuarioDto.setSexo( "F" );
        dato_usuarioDto.setFechaNacimiento( fecha );
        dato_usuarioDto.setEstadoCivil( "Casado" );
        dato_usuarioDto.setDisponibilidadEnLinea( "Ninguna" );
        dato_usuarioDto.setConCuantasPersonasVive( "16" );
        dato_usuarioDto.setEstado( "I" );
        Nivel_economicoDto nivel_economico = new Nivel_economicoDto( 2);
        dato_usuarioDto.setNivelEconomicoDto( nivel_economico );
        Nivel_academicoDto nivel_academico = new Nivel_academicoDto( 2);
        dato_usuarioDto.setNivelAcademicoDto( nivel_academico );
        OcupacionDto ocupacion = new OcupacionDto( 2);
        dato_usuarioDto.setOcupacionDto( ocupacion );
        LugarDto lugar = new LugarDto( 2);
        dato_usuarioDto.setLugarDto( lugar );
        Dato_usuarioDto resultado = servicio.updateDato_usuario( 1, dato_usuarioDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
