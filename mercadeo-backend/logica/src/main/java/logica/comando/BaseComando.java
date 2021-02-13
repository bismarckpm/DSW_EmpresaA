package logica.comando;

import ucab.dsw.dtos.ResponseDto;

import javax.json.JsonObject;

public abstract class BaseComando {

    public abstract void execute();

    public abstract ResponseDto getResult();
}
