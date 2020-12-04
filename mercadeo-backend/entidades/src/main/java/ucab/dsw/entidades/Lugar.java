package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "lugar" )
public class Lugar extends EntidadBase{

    @Column( name = "nombre" )
    private String _nombre;

    @Column( name = "tipo" )
    private String _tipo;

    @Column( name = "categoriaSocioEconomica" )
    private String _categoriaSocioEconomica;

    @Column( name = "estado" )
    private String _estado;

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_tipo() {
        return _tipo;
    }

    public void set_tipo(String _tipo) {
        this._tipo = _tipo;
    }

    public String get_categoriaSocioEconomica() {
        return _categoriaSocioEconomica;
    }

    public void set_categoriaSocioEconomica(String _categoriaSocioEconomica) {
        this._categoriaSocioEconomica = _categoriaSocioEconomica;
    }

    public Lugar get_lugar() {
        return _lugar;
    }

    public void set_lugar(Lugar _lugar) {
        this._lugar = _lugar;
    }

    @Override
    public String get_estado() {
        return _estado;
    }

    @Override
    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    @ManyToOne
    @JoinColumn( name = "fk_lugar" )
    private Lugar _lugar;

    public Lugar( long id )
    {
        super( id );
    }

    public Lugar( )
    {

    }
}
