package entidades;

import javax.persistence.*;

@Entity
@Table( name = "subcategoria" )
public class Subcategoria extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "descripcion" )
    private String _descripcion;

    @Column( name = "estado" )
    private String _estado;

    public Subcategoria( long codigo )
    {
        super( codigo );
    }

    public Subcategoria()
    {

    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public Categoria get_categoria() {
        return _categoria;
    }

    public void set_categoria(Categoria _categoria) {
        this._categoria = _categoria;
    }

    public String get_nombre()
    {
        return _nombre;
    }

    public void set_nombre( String _nombre )
    {
        this._nombre = _nombre;
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
    @JoinColumn( name = "fk_categoria" )
    private Categoria _categoria;
}
