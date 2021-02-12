package logica.fabrica;

import ucab.dsw.dtos.DtoBase;
import ucab.dsw.entidades.EntidadBase;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Fabrica<T> {

    private Class<T> tipo;

    public Fabrica(Class<T> tipo) {

        this.tipo= tipo;
    }


    public T getInstancia() {
        try {
            return tipo.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T crear(Class<T> tipo) {
        return new Fabrica<T>(tipo).getInstancia();
    }

    public static <T> T crearComandoConEntidad(Class<T> tipo, EntidadBase parametro) throws  IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parametro);
    }

    public static <T> T crearComandoConId(Class<T> tipo, long parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parametro);
    }

    public static <T> T crearComandoCon2Id(Class<T> tipo, long parametro1, long parametro2) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parametro1,parametro2);
    }

    public static <T> T crearComandoAmbos(Class<T> tipo, long parametro1, EntidadBase parametro2) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance(parametro1, parametro2);
    }

    public static <T> T crearComandoLista(Class<T> tipo, List<T> parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T) tipo.getConstructors()[0].newInstance( parametro);
    }
}