package logica.comando.estudio;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.EstudioMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditEstudioComando extends BaseComando {

    public long _id;
    public Estudio estudio;

    public EditEstudioComando(long _id, Estudio estudio) {
        this._id = _id;
        this.estudio = estudio;
    }

    @Override
    public void execute() {
        try{
            DaoEstudio dao = Fabrica.crear(DaoEstudio.class);
            Estudio resul = dao.update(this.estudio);
            this.estudio=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Estudio actualizado")
                .add("estudio_nombre",this.estudio.get_id()).build();

        return data;
    }

}
