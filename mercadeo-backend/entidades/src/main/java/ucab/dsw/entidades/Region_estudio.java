package ucab.dsw.entidades;

import javax.persistence.*;

@Entity
@Table( name = "region_estudio" )
@NamedQueries({
        @NamedQuery(name = "getRegionesActualizar", query = "SELECT re FROM Region_estudio re WHERE re._solicitudEstudio._id = :id_solicitud ")
})
public class Region_estudio extends EntidadBase{

    @Column( name = "estado" )
    private String _estado;

    public Region_estudio( long id )
    {
        super( id );
    }

    public Region_estudio()
    {

    }

    public Lugar get_lugar() {
        return _lugar;
    }

    public void set_lugar(Lugar _lugar) {
        this._lugar = _lugar;
    }

    public Solicitud_estudio get_solicitudEstudio() {
        return _solicitudEstudio;
    }

    public void set_solicitudEstudio(Solicitud_estudio _solicitudEstudio) {
        this._solicitudEstudio = _solicitudEstudio;
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
    @JoinColumn( name = "fk_lugar" )
    private Lugar _lugar;

    @ManyToOne
    @JoinColumn( name = "fk_solicitudEstudio" )
    private Solicitud_estudio _solicitudEstudio;
}
