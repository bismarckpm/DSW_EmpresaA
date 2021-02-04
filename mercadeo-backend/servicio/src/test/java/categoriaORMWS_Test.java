import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Solicitud_estudio;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class categoriaORMWS_Test {

    /**
     * Este test prueba el registro de una nueva categoría
     *
     */
    @Test
    public void addCategoriaTest() throws Exception {
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre( "Categoria1" );
        categoriaDto.setEstado( "A" );
        Response respuesta = servicio.addCategoria( categoriaDto );
        JsonObject responseDto= (JsonObject) respuesta.getEntity();
        Assert.assertNotEquals(0,responseDto.get("categoriaId"));
    }

    /**
     * Este test prueba la obtención de todas las categorías registradas
     *
     */
    @Test
    public void showCategoriaTest() throws Exception
    {
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        Response categorias = servicio.showCategoria();
        JsonObject responseDto= (JsonObject) categorias.getEntity();
        Assert.assertNotNull(responseDto.get("categorias"));
    }

    /**
     * Este test prueba la actualización de una categoría
     *
     */
    @Test
    public void updateCategoriaTest() throws Exception{

        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto(35);
        categoriaDto.setNombre( "Categoria2" );
        categoriaDto.setEstado( "I" );
        Response resultado = servicio.editCategoria (categoriaDto);
        JsonObject responseDto= (JsonObject) resultado.getEntity();
        Assert.assertNotEquals( responseDto.get("categoriaId"), 0);
    }

    /**
     * Este test prueba la obtención de una categoria especifica
     *
     */
    @Test
    public void consultarCategoriaTest() throws Exception{
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        Response resultado = servicio.consultarCategoria(1);
        JsonObject responseDto= (JsonObject) resultado.getEntity();
        Assert.assertNotEquals(resultado, null);
    }
}
