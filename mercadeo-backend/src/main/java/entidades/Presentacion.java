package entidades;

import javax.persistence.*;

@Entity
@Table( name = "presentacion" )
public class Presentacion extends EntidadBase{

    @Column( name = "titulo" )
    private String _titulo;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "caracteristicas" )
    private String _caracteristicas;

    public Presentacion( long codigo )
    {
        super( codigo );
    }

    public Presentacion()
    {

    }

    public String get_titulo() {
        return _titulo;
    }

    public void set_titulo(String _titulo) {
        this._titulo = _titulo;
    }

    public String get_caracteristicas() {
        return _caracteristicas;
    }

    public void set_caracteristicas(String _caracteristicas) {
        this._caracteristicas = _caracteristicas;
    }

    public Producto get_producto() {
        return _producto;
    }

    public void set_producto(Producto _producto) {
        this._producto = _producto;
    }

    @Override
    public String get_estado()
    {
        return _estado;
    }

    @Override
    public void set_estado( String _estado )
    {
        this._estado = _estado;
    }

    @ManyToOne
    @JoinColumn( name = "fk_producto" )
    private Producto _producto;
}
