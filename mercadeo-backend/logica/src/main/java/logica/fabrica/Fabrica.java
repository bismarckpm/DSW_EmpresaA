package logica.fabrica;

import ucab.dsw.dtos.DtoBase;
import ucab.dsw.entidades.EntidadBase;
import ucab.dsw.excepciones.CustomException;

import java.lang.reflect.InvocationTargetException;

public class Fabrica<T> {

    private Class<T> tipo;

    public Fabrica(Class<T> tipo) throws  CustomException {

        this.tipo= tipo;
    }


    public T getInstancia() throws  CustomException {
        try {
            return tipo.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T crear(Class<T> tipo) throws  CustomException {
        return new Fabrica<T>(tipo).getInstancia();
    }

    public static <T> T crearComandoConEntidad(Class<T> tipo, EntidadBase parametro) throws  IllegalAccessException, InvocationTargetException, InstantiationException, CustomException {
        return (T) tipo.getConstructors()[0].newInstance(parametro);
    }

    public static <T> T crearComandoConId(Class<T> tipo, long parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException, CustomException {
        return (T) tipo.getConstructors()[0].newInstance(parametro);
    }
}