package logica.comando.marca;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.MarcaMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddMarcaComando extends BaseComando {

    public Marca marca;

    public AddMarcaComando(Marca marca) {
        this.marca = marca;
    }

    @Override
    public void execute() {

        try {
            DaoMarca dao = Fabrica.crear(DaoMarca.class);
            Marca resul = dao.insert( this.marca);
            this.marca=resul;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public JsonObject getResult() {
        JsonObject data= Json.createObjectBuilder()
                .add("estado","Éxito")
                .add("mensaje","Marca añadida")
                .add("marca_id",this.marca.get_id()).build();

        return data;
    }

}
