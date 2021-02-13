package logica.comando.tipo;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.mappers.TipoMapper;

import javax.json.JsonObject;
import javax.json.Json;

public class AddTipoComando extends BaseComando {

    public Tipo tipo;

    public AddTipoComando(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public void execute() {

        try {
            DaoTipo dao = Fabrica.crear(DaoTipo.class);
            dao.insert( this.tipo );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Tipo Añadido");
        data.setObjeto(this.tipo);

        return data;
    }

}
