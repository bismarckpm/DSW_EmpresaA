import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
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
    public void addCategoriaTest() {
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre( "Categoria1" );
        categoriaDto.setEstado( "A" );
        Response resultado = servicio.addCategoria( categoriaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Categoria categoria = (Categoria) responseDto.getObjeto();
        Assert.assertNotEquals( categoria.get_id(), 0  );
    }

    /**
     * Este test prueba la obtención de todas las categorías registradas
     *
     */
    @Test
    public void showCategoriaTest() throws Exception
    {
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        Response resultado = servicio.showCategoria();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Categoria> categorias = (List<Categoria>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",categorias.isEmpty());
    }

    /**
     * Este test prueba la actualización de una categoría
     *
     */
    @Test
    public void updateCategoriaTest() throws Exception{

        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto(42);
        categoriaDto.setNombre( "Categoria2" );
        categoriaDto.setEstado( "I" );
        Response resultado = servicio.editCategoria (categoriaDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Categoria categoria = (Categoria) responseDto.getObjeto();
        System.out.println(categoria.get_id());
        Assert.assertNotEquals( categoria.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de una categoria especifica
     *
     */
    @Test
    public void consultarCategoriaTest() throws Exception{
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        Response resultado = servicio.consultarCategoria(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Categoria categoria = (Categoria) responseDto.getObjeto();
        System.out.println(categoria.get_id());
        Assert.assertNotEquals(categoria, null);
    }
}
