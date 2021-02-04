package logica.comando.nivel_academico;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoNivel_academico;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Nivel_academico;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.NivelAcademicoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddNivel_academicoComando extends BaseComando {

    public Nivel_academico nivel_academico;

    public AddNivel_academicoComando(Nivel_academico nivel_academico) {
        this.nivel_academico = nivel_academico;
    }

    @Override
    public void execute() {

        try {
            DaoNivel_academico dao = Fabrica.crear(DaoNivel_academico.class);
            Nivel_academico resul = dao.insert( this.nivel_academico );
            this.nivel_academico=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Nivel_academico añadido")
                .add("nivel_academico_id",this.nivel_academico.get_id()).build();

        return data;
    }

}
