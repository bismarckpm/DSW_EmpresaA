package ucab.dsw.Response.TipoPregunta;

import lombok.Getter;
import lombok.Setter;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Marca;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Subcategoria;

@Getter
@Setter
public class ProductoSolicitudResponse {

    private Producto producto;
    private Marca marca;
    private Subcategoria subcategoria;
    private Categoria categoria;

    public ProductoSolicitudResponse(Producto producto, Marca marca, Subcategoria subcategoria, Categoria categoria) {
        this.producto = producto;
        this.marca = marca;
        this.subcategoria = subcategoria;
        this.categoria = categoria;
    }
}
