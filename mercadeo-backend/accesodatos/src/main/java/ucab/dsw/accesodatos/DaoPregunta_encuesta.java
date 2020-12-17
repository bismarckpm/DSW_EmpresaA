package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Pregunta_encuesta;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


import javax.persistence.EntityManager;

public class DaoPregunta_encuesta extends Dao<Pregunta_encuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public List<Pregunta_encuesta> getConOpciones(){
        try{
            TypedQuery <Pregunta_encuesta> preguntas = this._em.createNamedQuery( "getConOpciones", Pregunta_encuesta.class);
            preguntas.getResultList();

            List<Pregunta_encuesta> resultado = preguntas.getResultList();
            return resultado;
        } catch (Exception e){
            return null;
        }
    }


    public DaoPregunta_encuesta( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }
}
