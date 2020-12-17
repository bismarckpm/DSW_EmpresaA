package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Pregunta_encuesta;
import ucab.dsw.entidades.Pregunta_estudio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoPregunta_estudio extends Dao<Pregunta_estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public List<Pregunta_estudio> getPreguntasEstudio(Estudio estudio){
        try{
            TypedQuery<Pregunta_estudio> preguntas = this._em.createNamedQuery( "getPreguntasEstudio", Pregunta_estudio.class);
            preguntas.setParameter("estudio", estudio).getResultList();
            preguntas.getResultList();

            List<Pregunta_estudio> resultado = preguntas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }

    public DaoPregunta_estudio( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
