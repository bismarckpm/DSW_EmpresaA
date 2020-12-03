import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import java.util.List;

public class categoriaORMWS_Test {

    @Test
    public void addCategoriaTest() throws Exception {
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre( "Categoria1" );
        categoriaDto.setEstado( "A" );
        CategoriaDto resultado = servicio.addCategoria( categoriaDto );
        System.out.println(resultado.getId());
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showCategoriaTest() throws Exception
    {
        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        List<Categoria> categorias = servicio.showCategoria();
        Assert.assertFalse("Consulta Realizada con Exito",categorias.isEmpty());
    }

    @Test
    public void updateCategoriaTest() throws Exception{

        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto(1);
        categoriaDto.setNombre( "Categoria2" );
        categoriaDto.setEstado( "I" );
        CategoriaDto resultado = servicio.editCategoria (categoriaDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteCategoriaTest() throws Exception{

        ucab.dsw.servicio.categoriaORMWS servicio = new ucab.dsw.servicio.categoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto(1);
        CategoriaDto resultado = servicio.deleteCategoria(categoriaDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }


}
