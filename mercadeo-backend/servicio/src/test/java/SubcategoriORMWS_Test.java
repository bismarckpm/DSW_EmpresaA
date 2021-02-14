import org.junit.Assert;
import org.junit.Test;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Tipo;

import javax.ws.rs.core.Response;
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
        Response resultado = servicio.addSubcategoria( subcategoriaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Subcategoria subcategoria = (Subcategoria) responseDto.getObjeto();
        Assert.assertNotEquals( subcategoria.get_id(), 0  );
    }

    @Test
    public void showSubcategoriasTest() throws Exception{
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        Response resultado = servicio.showSubcategoria();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Subcategoria> subcategorias = (List<Subcategoria>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",subcategorias.isEmpty());
    }

    @Test
    public void updateSubcategoriaTest() throws Exception
    {
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);
        subcategoriaDto.setNombre( "ShampooModificado" );
        subcategoriaDto.setDescripcion( "DescModificada" );
        subcategoriaDto.setEstado( "I" );
        CategoriaDto categoria = new CategoriaDto( 1);
        subcategoriaDto.setCategoriaDto( categoria );
        Response resultado = servicio.editSubcategoria( subcategoriaDto );
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Subcategoria subcategoria = (Subcategoria) responseDto.getObjeto();
        System.out.println(subcategoria.get_id());
        Assert.assertNotEquals( subcategoria.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de una subcategoria especifica
     *
     */
    @Test
    public void consultarSubcategoriaTest() throws Exception{
        ucab.dsw.servicio.SubcategoriaORMWS servicio = new ucab.dsw.servicio.SubcategoriaORMWS();
        Response resultado = servicio.consultarSubcategoria(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Subcategoria subcategoria = (Subcategoria) responseDto.getObjeto();
        System.out.println(subcategoria.get_id());
        Assert.assertNotEquals(subcategoria, null);
    }
}
