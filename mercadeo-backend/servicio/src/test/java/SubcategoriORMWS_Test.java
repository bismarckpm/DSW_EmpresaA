import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Tipo;

import java.util.List;

public class SubcategoriORMWS_Test {
    
    @Test
    public void addSubcategoriaTest() throws Exception
    {
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
        subcategoriaDto.setNombre( "Nombre1" );
        subcategoriaDto.setDescripcion( "Descripcion1" );
        subcategoriaDto.setEstado( "A" );
        CategoriaDto categoria = new CategoriaDto( 1);
        subcategoriaDto.setCategoriaDto( categoria );
        SubcategoriaDto resultado = servicio.addSubcategoria( subcategoriaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showSubcategoriasTest() throws Exception{
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        List<Subcategoria> resultado = servicio.showSubcategoria();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateSubcategoriaTest() throws Exception
    {
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto();
        subcategoriaDto.setNombre( "ShampooModificado" );
        subcategoriaDto.setDescripcion( "DescModificada" );
        subcategoriaDto.setEstado( "I" );
        CategoriaDto categoria = new CategoriaDto( 1);
        subcategoriaDto.setCategoriaDto( categoria );
        SubcategoriaDto resultado = servicio.editSubcategoria( subcategoriaDto );
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    /**
     * Este test prueba la obtención de una subcategoria especifica
     *
     */
    @Test
    public void consultarSubcategoriaTest() throws Exception{
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        Subcategoria resultado = servicio.consultarSubcategoria(1);
        Assert.assertNotEquals(resultado, null);
    }
}
