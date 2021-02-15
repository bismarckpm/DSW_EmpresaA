package logica.comando;

import ucab.dsw.dtos.ResponseDto;
import ucab.dsw.excepciones.CustomException;

import javax.json.JsonObject;

public abstract class BaseComando {

    public abstract void execute() throws CustomException;

    public abstract ResponseDto getResult();
}
