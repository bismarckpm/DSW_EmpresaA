package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelAcademicoMapper;

import javax.json.Json;
import javax.json.JsonObject;

public class EditNivel_academicoComando extends BaseComando {

    public long _id;
    public Nivel_academico nivel_academico;

    public EditNivel_academicoComando(long _id, Nivel_academico nivel_academico) {
        this._id = _id;
        this.nivel_academico = nivel_academico;
    }

    @Override
    public void execute() {
        try{
            DaoNivel_academico dao = Fabrica.crear(DaoNivel_academico.class);
            Nivel_academico resul = dao.update(this.nivel_academico);
            this.nivel_academico=resul;
        }catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_academico actualizado")
                .add("nivel_academico_nombre",this.nivel_academico.get_id()).build();

        return data;
    }

}
