import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Usuario;

import javax.ws.rs.core.Response;
import java.util.List;

public class ProductoORMWS_Test {

    /**
     * Este test prueba el registro de un producto
     *
     */
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
        UsuarioDto usuario = new UsuarioDto( 2);
        productoDto.setUsuarioDto( usuario );
        Response resultado = servicio.addProducto( productoDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto producto = (Producto) responseDto.getObjeto();
        Assert.assertNotEquals( producto.get_id(), 0  );
    }

    /**
     * Este test prueba la consulta de todos los productos
     *
     */
    @Test
    public void showProductosTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        Response resultado = servicio.showProductos();
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Producto> productos = (List<Producto>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",productos.isEmpty());
    }


    /**
     * Este test prueba la actualización de un producto
     *
     */
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
        UsuarioDto usuario = new UsuarioDto( 2);
        productoDto.setUsuarioDto( usuario );

        Response resultado = servicio.updateProducto( 1, productoDto);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto producto = (Producto) responseDto.getObjeto();
        System.out.println(producto.get_id());
        Assert.assertNotEquals( producto.get_id(), 0);
    }

    /**
     * Este test prueba la obtención de la lista de productos de un cliente específico
     *
     */
    @Test
    public void showProductosClienteTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        Response resultado= servicio.showProductosCliente(6);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        List<Producto> productos = (List<Producto>) responseDto.getObjeto();
        Assert.assertFalse("Consulta Realizada con Exito",productos.isEmpty());
    }

    /**
     * Este test prueba la obtención del producto con el que se relaciona un estudio
     *
     */
    @Test
    public void getProductoEstudioTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        Response resultado = servicio.getProductoEstudio(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto producto = (Producto) responseDto.getObjeto();
        System.out.println(producto.get_id());
        Assert.assertNotEquals(producto, null);
    }

    /**
     * Este test prueba la obtención de un producto especifico
     *
     */
    @Test
    public void consultarProductoTest() throws Exception{
        ucab.dsw.servicio.ProductoORMWS servicio = new ucab.dsw.servicio.ProductoORMWS();
        Response resultado = servicio.consultarProducto(1);
        ResponseDto responseDto= (ResponseDto) resultado.getEntity();
        Producto producto = (Producto) responseDto.getObjeto();
        System.out.println(producto.get_id());
        Assert.assertNotEquals(producto, null);
    }
}
