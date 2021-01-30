import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Marca;
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
    public void showProductosTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        List<Producto> resultado = servicio.showProductos();
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

    /**
     * Este test prueba la obtención de la lista de productos de un cliente específico
     *
     */
    @Test
    public void showProductosClienteTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        List<Producto> resultado = servicio.showProductosCliente(4);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención del producto con el que se relaciona un estudio
     *
     */
    @Test
    public void getProductoEstudioTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        Producto resultado = servicio.getProductoEstudio(1);
        Assert.assertNotEquals(resultado, null);
    }

    /**
     * Este test prueba la obtención de un producto especifico
     *
     */
    @Test
    public void consultarProductoTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        Producto resultado = servicio.consultarProducto(1);
        Assert.assertNotEquals(resultado, null);
    }
}
