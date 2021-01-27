package logica.comando;

import javax.json.JsonObject;

public abstract class BaseComando {

    public abstract void execute();

    public abstract JsonObject getResult();
}
