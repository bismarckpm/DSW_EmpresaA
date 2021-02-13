package logica.comando.categoria;

import logica.comando.BaseComando;
import logica.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.CustomException;

public class EditCategoriaComando extends BaseComando {

    public Categoria categoria;

    public EditCategoriaComando(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void execute() throws CustomException {
        try{
            DaoCategoria dao = Fabrica.crear(DaoCategoria.class);
            dao.update(this.categoria);
        }catch ( CustomException ex ) {
            throw ex;
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

    }

    @Override
    public ResponseDto getResult() {
        ResponseDto data = new ResponseDto();
        data.setEstado("000");
        data.setMensaje("Categoria actualizada");
        data.setObjeto(this.categoria.get_id());

        return data;
    }

}
