import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Solicitud_estudio;

import java.util.List;

public class Solicitud_estudioORMWS {
    
   /* @Test
    public void addSolicitud_estudioTest() throws Exception
    {
        ucab.dsw.servicio.Solicitud_estudioORMWS servicio = new ucab.dsw.servicio.Solicitud_estudioORMWS();
        Solicitud_estudioDto solicitud_estudioDto = new Solicitud_estudioDto();
        solicitud_estudioDto.setDescripcionSolicitud( "Descripcion" );
        solicitud_estudioDto.setGeneroPoblacional( "Masculino" );

        solicitud_estudioDto.setEstado( "A" );
        MarcaDto marca = new MarcaDto( 1);
        solicitud_estudioDto.setMarcaDto( marca );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 1);
        solicitud_estudioDto.setSubcategoriaDto( subcategoria );

        List<Solicitud_estudio_presentacionDto> presentacionesDto= null;
        List<Solicitud_estudio_tipoDto> tiposDto= null;

        Solicitud_estudioDto resultado = servicio.addSolicitud_estudio( solicitud_estudioDto, presentacionesDto, tiposDto);
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
        solicitud_estudioDto.setNombre( "ShampooModificado" );
        solicitud_estudioDto.setDescripcion( "DescModificada" );
        solicitud_estudioDto.setEstado( "I" );
        MarcaDto marca = new MarcaDto( 2);
        solicitud_estudioDto.setMarcaDto( marca );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 2);
        solicitud_estudioDto.setSubcategoriaDto( subcategoria );

        List<Solicitud_estudio_presentacionDto> presentacionesDto= null;
        List<Solicitud_estudio_tipoDto> tiposDto= null;

        Solicitud_estudioDto resultado = servicio.updateSolicitud_estudio( 1, solicitud_estudioDto, presentacionesDto, tiposDto );
        //Assert.assertNotEquals( resultado.getId(), 0  );
    }*/
}
