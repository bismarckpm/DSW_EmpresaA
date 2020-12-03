import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.servicio.DatoUsuarioORMWS;

import java.util.Date;
import java.util.List;

public class DatoUsuario_Test {

    Dato_usuarioDto datoUsuario = new Dato_usuarioDto();

    Dato_usuarioDto datoUsuarioUpdate = new Dato_usuarioDto();

    DatoUsuarioORMWS servicio = new DatoUsuarioORMWS();

    @Before
    public void setup() throws Exception {

        Nivel_economicoDto nivelEconomicoDto = new Nivel_economicoDto(1);
        LugarDto lugarDto = new LugarDto(1);
        Nivel_academicoDto nivelAcademicoDto = new Nivel_academicoDto(1);
        OcupacionDto ocupacionDto = new OcupacionDto(1);
        Date date = new Date();

        datoUsuario.setCedula("V12345678");
        datoUsuario.setPrimerNombre("Pedro");
        datoUsuario.setSegundoNombre("Luis");
        datoUsuario.setPrimerApellido("Lopez");
        datoUsuario.setSegundoApellido("Gonzalez");
        datoUsuario.setSexo("M");
        datoUsuario.setFechaNacimiento(date);
        datoUsuario.setEstadoCivil("soltero");
        datoUsuario.setDisponibilidadEnLinea("A");
        datoUsuario.setConCuantasPersonasVive("2");
        datoUsuario.setLugarDto(lugarDto);
        datoUsuario.setNivelEconomicoDto(nivelEconomicoDto);
        datoUsuario.setNivelAcademicoDto(nivelAcademicoDto);
        datoUsuario.setOcupacionDto(ocupacionDto);

        datoUsuarioUpdate.setId(1);
        datoUsuarioUpdate.setCedula("V12345678");
        datoUsuarioUpdate.setPrimerNombre("Maria");
        datoUsuarioUpdate.setSegundoNombre("Luis");
        datoUsuarioUpdate.setPrimerApellido("Lopez");
        datoUsuarioUpdate.setSegundoApellido("Gonzalez");
        datoUsuarioUpdate.setSexo("F");
        datoUsuarioUpdate.setFechaNacimiento(date);
        datoUsuarioUpdate.setEstadoCivil("soltero");
        datoUsuarioUpdate.setDisponibilidadEnLinea("A");
        datoUsuarioUpdate.setConCuantasPersonasVive("2");
        datoUsuarioUpdate.setLugarDto(lugarDto);
        datoUsuarioUpdate.setNivelEconomicoDto(nivelEconomicoDto);
        datoUsuarioUpdate.setNivelAcademicoDto(nivelAcademicoDto);
        datoUsuarioUpdate.setOcupacionDto(ocupacionDto);


    }

    @Test
    public void createTest() throws Exception {

        Dato_usuario result = servicio.create(datoUsuario);
        Assert.assertEquals(datoUsuario.getCedula(),  result.get_cedula());

    }

    @Test
    public void updateTest() throws Exception {

        Dato_usuario result = servicio.update(datoUsuarioUpdate);
        Assert.assertEquals("Maria", result.get_primerNombre());

    }

    @Test
    public void getOneTest() throws Exception {

        Dato_usuario result = servicio.getOne(datoUsuarioUpdate);
        Assert.assertEquals(1, result.get_id());

    }

    @Test
    public void updateStatusTest() throws Exception {

        Dato_usuario result = servicio.updateStatus(datoUsuarioUpdate);
        Assert.assertEquals("I", result.get_estado());

    }


}
