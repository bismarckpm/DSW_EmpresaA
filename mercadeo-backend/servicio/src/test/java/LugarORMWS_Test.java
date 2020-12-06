import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;
import java.util.List;

public class LugarORMWS_Test {

    @Test
    public void addLugarTest() throws Exception
    {
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        LugarDto lugarDto = new LugarDto();
        lugarDto.setNombre( "Ejemplo nombre4" );
        lugarDto.setEstado( "A" );
        lugarDto.setCategoriaSocioEconomica("CatSocEc44");
        lugarDto.setTipo("Municipio");
        LugarDto lugar = new LugarDto( 1);
        lugarDto.setLugarDto( lugar );
        LugarDto resultado = servicio.addLugar( lugarDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteLugarTest() throws Exception{
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        LugarDto resultado = servicio.deleteLugar(4);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showLugarTest() throws Exception{
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        List<Lugar> resultado = servicio.showLugares();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateLugarTest() throws Exception
    {
        ucab.dsw.servicio.LugarORMWS servicio = new ucab.dsw.servicio.LugarORMWS();
        LugarDto lugarDto = new LugarDto();
        lugarDto.setNombre( "Nuevo nombre" );
        lugarDto.setEstado( "I" );
        lugarDto.setCategoriaSocioEconomica( "Nueva categ" );
        lugarDto.setTipo( "Pais" );
        LugarDto lugar = new LugarDto( 2);
        lugarDto.setLugarDto( lugar );
        LugarDto resultado = servicio.updateLugar( 4, lugarDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
