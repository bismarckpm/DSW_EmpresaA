package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.EstudioMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddEstudioComando extends BaseComando {

    public Estudio estudio;

    public AddEstudioComando(Estudio estudio) {
        this.estudio = estudio;
    }

    @Override
    public void execute() {

        try {
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            Estudio resul = dao.insert( this.estudio );
            this.estudio=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Estudio añadido")
                .add("estudio_id",this.estudio.get_id()).build();

        return data;
    }

}
