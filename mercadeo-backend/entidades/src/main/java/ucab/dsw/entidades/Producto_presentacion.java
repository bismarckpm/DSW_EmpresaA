package ucab.dsw.entidades;
import javax.persistence.*;

@Entity
@Table( name = "producto_presentacion" )
public class Producto_presentacion extends EntidadBase {

    @Column(name = "estado")
    private String _estado;

    @ManyToOne
    @JoinColumn(name = "fk_producto")
    private Producto _producto;

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    private Presentacion _presentacion;

    public Producto_presentacion(long id) {
        super(id);
    }

    public Producto_presentacion() {

    }

    @Override
    public String get_estado() {
        return _estado;
    }

    @Override
    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    public Producto get_producto() {
        return _producto;
    }

    public void set_producto(Producto _producto) {
        this._producto = _producto;
    }

    public Presentacion get_presentacion() {
        return _presentacion;
    }

    public void set_presentacion(Presentacion _presentacion) {
        this._presentacion = _presentacion;
    }
}


