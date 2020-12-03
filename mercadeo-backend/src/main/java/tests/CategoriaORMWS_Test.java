package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import dtos.CategoriaDto;
import entidades.Categoria;
import java.util.List;

public class CategoriaORMWS_Test {

    @Test
    public void addCategoriaTest() throws Exception {
        servicios.CategoriaORMWS servicio = new servicios.CategoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setNombre( "Categoria1" );
        categoriaDto.setEstado( "A" );
        CategoriaDto resultado = servicio.addCategoria( categoriaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showCategoriaTest() throws Exception
    {
        servicios.CategoriaORMWS servicio = new servicios.CategoriaORMWS();
        List<Categoria> categorias = servicio.showCategoria();
        Assert.assertFalse("Consulta Realizada con Exito",categorias.isEmpty());
    }

    @Test
    public void updateCategoriaTest() throws Exception{

        servicios.CategoriaORMWS servicio = new servicios.CategoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto(1);
        categoriaDto.setNombre( "Categoria2" );
        categoriaDto.setEstado( "I" );
        CategoriaDto resultado = servicio.editCategoria (categoriaDto);
        Assert.assertNotEquals( resultado.getId(), 0);
    }

    @Test
    public void deleteCategoriaTest() throws Exception{

        servicios.CategoriaORMWS servicio = new servicios.CategoriaORMWS();
        CategoriaDto categoriaDto = new CategoriaDto(1);
        CategoriaDto resultado = servicio.deleteCategoria(categoriaDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }
}
