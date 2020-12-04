import ucab.dsw.dtos.Nivel_academicoDto;
import ucab.dsw.entidades.Nivel_academico;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class Nivel_academicoORMWS_Test {

    @Test
    public void addNivel_academicoTest() throws Exception {
        ucab.dsw.servicio.Nivel_academicoORMWS servicio = new ucab.dsw.servicio.Nivel_academicoORMWS();
        Nivel_academicoDto nivel_academicoDto = new Nivel_academicoDto();
        nivel_academicoDto.setNivel( "Nivel_academico1" );
        nivel_academicoDto.setEstado( "A" );
        Nivel_academicoDto resultado = servicio.addNivel_academico( nivel_academicoDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showNivel_academicoTest() throws Exception
    {
        ucab.dsw.servicio.Nivel_academicoORMWS servicio = new ucab.dsw.servicio.Nivel_academicoORMWS();
        List<Nivel_academico> nivel_academicos = servicio.showNivel_academico();
        Assert.assertFalse("Consulta Realizada con Exito",nivel_academicos.isEmpty());
    }

    @Test
    public void updateNivel_academicoTest() throws Exception{

        ucab.dsw.servicio.Nivel_academicoORMWS servicio = new ucab.dsw.servicio.Nivel_academicoORMWS();
        Nivel_academicoDto nivel_academicoDto = new Nivel_academicoDto(1);
        nivel_academicoDto.setNivel( "Nivel_academico2" );
        nivel_academicoDto.setEstado( "I" );
        Nivel_academicoDto resultado = servicio.editNivel_academico (nivel_academicoDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteNivel_academicoTest() throws Exception{

        ucab.dsw.servicio.Nivel_academicoORMWS servicio = new ucab.dsw.servicio.Nivel_academicoORMWS();
        Nivel_academicoDto nivel_academicoDto = new Nivel_academicoDto(1);
        Nivel_academicoDto resultado = servicio.deleteNivel_academico(nivel_academicoDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
