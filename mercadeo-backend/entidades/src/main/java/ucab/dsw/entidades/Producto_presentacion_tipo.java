package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "producto_presentacion_tipo" )
public class Producto_presentacion_tipo extends EntidadBase {

    @Column(name = "estado")
    private String _estado;

    @ManyToOne
    @JoinColumn(name = "fk_producto")
    private Producto _producto;

    @ManyToOne
    @JoinColumn(name = "fk_presentacion")
    private Presentacion _presentacion;

    @ManyToOne
    @JoinColumn(name = "fk_tipo")
    private Tipo _tipo;

    public Producto_presentacion_tipo(long id) {
        super(id);
    }

    public Producto_presentacion_tipo() {

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

    public Tipo get_tipo() {
        return _tipo;
    }

    public void set_tipo(Tipo _tipo) {
        this._tipo = _tipo;
    }
}
