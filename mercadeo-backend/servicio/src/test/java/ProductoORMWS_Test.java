import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Usuario;

import java.util.List;

public class ProductoORMWS_Test {

    @Test
    public void addProductoTest() throws Exception
    {
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre( "Shampoooo" );
        productoDto.setDescripcion( "DescInicial" );
        productoDto.setEstado( "A" );
        MarcaDto marca = new MarcaDto( 1);
        productoDto.setMarcaDto( marca );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 1);
        productoDto.setSubcategoriaDto( subcategoria );
        UsuarioDto usuario = new UsuarioDto( 1);
        productoDto.setUsuarioDto( usuario );
        ProductoDto resultado = servicio.addProducto( productoDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void deleteProductoTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        ProductoDto resultado = servicio.deleteProducto(1);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }

    @Test
    public void showProductosTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        List<Producto> resultado = servicio.showProductos();
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void showProductosClienteTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(2);
        List<Producto> resultado = servicio.showProductosCliente(usuarioDto);
        Assert.assertNotEquals(resultado, null);
    }

    @Test
    public void updateProductoTest() throws Exception
    {
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        ProductoDto productoDto = new ProductoDto();
        productoDto.setNombre( "ShampooModificado" );
        productoDto.setDescripcion( "DescModificada" );
        productoDto.setEstado( "I" );
        MarcaDto marca = new MarcaDto( 2);
        productoDto.setMarcaDto( marca );
        SubcategoriaDto subcategoria = new SubcategoriaDto( 2);
        productoDto.setSubcategoriaDto( subcategoria );
        UsuarioDto usuario = new UsuarioDto( 1);
        productoDto.setUsuarioDto( usuario );

        ProductoDto resultado = servicio.updateProducto( 1, productoDto);
        Assert.assertNotEquals( resultado.getId(), 0  );
    }
}
