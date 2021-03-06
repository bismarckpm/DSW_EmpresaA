import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Response.ListaEncuestasE;
import ucab.dsw.dtos.Dato_usuarioDto;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Telefono;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class TelefonoORMWS_Test {

    /**
     * Este test prueba el registro de una lista de teléfonos de un usuario
     *
     */
    @Test
    public void addTelefonoTest() throws Exception
    {
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();

        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);

        TelefonoDto telefono1 = new TelefonoDto();
        telefono1.setNumero( "04241136422" );
        telefono1.setEstado( "A" );
        telefono1.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono2 = new TelefonoDto();
        telefono2.setNumero( "04241133574" );
        telefono2.setEstado( "A" );
        telefono2.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono3 = new TelefonoDto();
        telefono3.setNumero( "04242636422" );
        telefono3.setEstado( "A" );
        telefono3.setDatoUsuarioDto( dato_usuario );

        List<TelefonoDto> telefonos = new ArrayList<TelefonoDto>();

        telefonos.add(telefono1);
        telefonos.add(telefono2);
        telefonos.add(telefono3);

        Response resultado = servicio.addTelefono( telefonos );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Telefono> telefonoss = (List<Telefono>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",telefonoss.isEmpty());
    }

    /**
     * Este test prueba la consulta de todos los teléfonos
     *
     */
    @Test
    public void showTelefonosTest() throws Exception{
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();
        Response resultado = servicio.showTelefonos();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Telefono> telefonoss = (List<Telefono>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",telefonoss.isEmpty());
    }

    /**
     * Este test prueba la actualización de la lista de teléfonos de un usuario
     *
     */
    @Test
    public void updateTelefonoTest() throws Exception
    {
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();
        Dato_usuarioDto dato_usuario = new Dato_usuarioDto( 1);

        TelefonoDto telefono1 = new TelefonoDto(1);
        telefono1.setNumero( "04241136422" );
        telefono1.setEstado( "A" );
        telefono1.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono2 = new TelefonoDto(2);
        telefono2.setNumero( "04241133574" );
        telefono2.setEstado( "A" );
        telefono2.setDatoUsuarioDto( dato_usuario );

        TelefonoDto telefono3 = new TelefonoDto(3);
        telefono3.setNumero( "04242636422" );
        telefono3.setEstado( "A" );
        telefono3.setDatoUsuarioDto( dato_usuario );

        List<TelefonoDto> telefonos = new ArrayList<TelefonoDto>();

        telefonos.add(telefono1);
        telefonos.add(telefono2);
        telefonos.add(telefono3);

        Response resultado = servicio.updateTelefono( telefonos );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Telefono> telefonoss = (List<Telefono>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",telefonoss.isEmpty());
    }

    /**
     * Este test prueba la obtención de la lista de telefonos para un usuario
     *
     */
    @Test
    public void obtenerTelefonosUsuarioTest() throws Exception{
        ucab.dsw.servicio.TelefonoORMWS servicio = new ucab.dsw.servicio.TelefonoORMWS();
        Response resultado = servicio.obtenerTelefonosUsuario(10);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Telefono> telefonoss = (List<Telefono>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",telefonoss.isEmpty());
    }
    
}
