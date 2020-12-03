import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.servicio.LugarORMWS;

public class LugarORMWS_Test {

    @Test
    public void getListTest() throws Exception {
        LugarORMWS servicio = new LugarORMWS();
        Assert.assertNotEquals("Lista de lugares encontrados",0 , servicio.getList().size());
    }
}
