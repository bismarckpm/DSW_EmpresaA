package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "producto" )
public class Producto extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "estado" )
    private String _estado;

    @Column( name = "descripcion" )
    private String _descripcion;

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public Marca get_marca() {
        return _marca;
    }

    public void set_marca(Marca _marca) {
        this._marca = _marca;
    }

    public Subcategoria get_subcategoria() {
        return _subcategoria;
    }

    public void set_subcategoria(Subcategoria _subcategoria) {
        this._subcategoria = _subcategoria;
    }

    public Producto(long id )
    {
        super( id );
    }

    public Producto()
    {

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
    @JoinColumn( name = "fk_marca" )
    private Marca _marca;

    @ManyToOne
    @JoinColumn( name = "fk_subcategoria" )
    private Subcategoria _subcategoria;

    @ManyToOne
    @JoinColumn( name = "fk_usuario" )
    private Usuario usuario;
}
