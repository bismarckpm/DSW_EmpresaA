import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.entidades.Response.DatoUsuarioResponse;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.servicio.DatoUsuarioORMWS;

import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
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

        datoUsuario.setCedula("V12345678");
        datoUsuario.setPrimerNombre("Pedro");
        datoUsuario.setSegundoNombre("Luis");
        datoUsuario.setPrimerApellido("Lopez");
        datoUsuario.setSegundoApellido("Gonzalez");
        datoUsuario.setSexo("M");
        datoUsuario.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("06/04/1994"));
        datoUsuario.setEstadoCivil("soltero");
        datoUsuario.setDisponibilidadEnLinea("A");
        datoUsuario.setConCuantasPersonasVive("2");
        datoUsuario.setMedioComunicacion("PC");
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
        datoUsuarioUpdate.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("06/04/1994"));
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

        Response resultado = servicio.create(datoUsuario);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Dato_usuario dato_usuario = (Dato_usuario) responseDto.getObjeto();
        Assert.assertNotEquals( dato_usuario.get_id(), 0  );

    }

    /**
     * Este test prueba la actualización de un DatoUsuario
     *
     */
    @Test
    public void editDato_usuarioTest() throws Exception{

        ucab.dsw.servicio.DatoUsuarioORMWS servicio = new ucab.dsw.servicio.DatoUsuarioORMWS();
        Dato_usuarioDto dato_usuarioDto = new Dato_usuarioDto(1);

        LugarDto lugar= new LugarDto(1);
        Nivel_academicoDto nivelAcademico = new Nivel_academicoDto(1);
        OcupacionDto ocupacion = new OcupacionDto(1);
        Nivel_economicoDto nivelEconomico = new Nivel_economicoDto(1);

        dato_usuarioDto.setCedula("26901583");
        dato_usuarioDto.setPrimerNombre("Raikjars");
        dato_usuarioDto.setEstado("A");
        dato_usuarioDto.setSegundoNombre("Hans");
        dato_usuarioDto.setPrimerApellido("Africano");
        dato_usuarioDto.setSegundoApellido("Silva");
        dato_usuarioDto.setSexo("Masculino");
        dato_usuarioDto.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2020"));
        dato_usuarioDto.setEstadoCivil("Casado");
        dato_usuarioDto.setDisponibilidadEnLinea("Si");
        dato_usuarioDto.setConCuantasPersonasVive("1");
        dato_usuarioDto.setMedioComunicacion("PC");
        dato_usuarioDto.setLugarDto(lugar);
        dato_usuarioDto.setNivelAcademicoDto(nivelAcademico);
        dato_usuarioDto.setNivelEconomicoDto(nivelEconomico);
        dato_usuarioDto.setOcupacionDto(ocupacion);

        Response resultado = servicio.editDato_usuario(1,dato_usuarioDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Dato_usuario dato_usuario = (Dato_usuario) responseDto.getObjeto();
        Assert.assertNotEquals( dato_usuario.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de la lista de dato usuarios
     *
     */
    @Test
    public void getAllTest() throws Exception{
        ucab.dsw.servicio.DatoUsuarioORMWS servicio = new ucab.dsw.servicio.DatoUsuarioORMWS();
        Response resultado = servicio.getAll();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Dato_usuario> dato_usuarios = (List<Dato_usuario>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",dato_usuarios.isEmpty());
    }

    /**
     * Este test prueba la consulta te un dato_usuario
     *
     */
    @Test
    public void consultarDato_usuarioTest() throws Exception{
        ucab.dsw.servicio.DatoUsuarioORMWS servicio = new ucab.dsw.servicio.DatoUsuarioORMWS();
        Response resultado = servicio.consultarDato_usuario(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Dato_usuario dato_usuario = (Dato_usuario) responseDto.getObjeto();
        Assert.assertNotEquals(dato_usuario, null);
    }

    /**
     * Este test prueba la consulta te un dato_usuario recibiendo el id de un usuario
     *
     */
    @Test
    public void consultarDato_usuarioPorUsuarioTest() throws Exception{
        ucab.dsw.servicio.DatoUsuarioORMWS servicio = new ucab.dsw.servicio.DatoUsuarioORMWS();
        Response resultado = servicio.consultarDato_usuarioPorUsuario(9);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Dato_usuario dato_usuario = (Dato_usuario) responseDto.getObjeto();
        Assert.assertNotEquals(dato_usuario, null);
    }

}
