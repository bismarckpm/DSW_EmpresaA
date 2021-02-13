package logica.fabrica;

import ucab.dsw.dtos.DtoBase;
import ucab.dsw.dtos.LoginDto;
import ucab.dsw.entidades.EntidadBase;
import ucab.dsw.excepciones.CustomException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Fabrica<T> {

    private Class<T> tipo;

    public Fabrica(Class<T> tipo) throws CustomException
    {
        this.tipo= tipo;
    }


    public T getInstancia() throws  CustomException{
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

    public static <T> T crearComandoCon2Id(Class<T> tipo, long parametro1, long parametro2) throws IllegalAccessException, InvocationTargetException, InstantiationException, CustomException {
        return (T) tipo.getConstructors()[0].newInstance(parametro1,parametro2);
    }

    public static <T> T crearComandoAmbos(Class<T> tipo, long parametro1, EntidadBase parametro2) throws IllegalAccessException, InvocationTargetException, InstantiationException, CustomException{
        return (T) tipo.getConstructors()[0].newInstance(parametro1, parametro2);
    }

    public static <T> T crearComandoString(Class<T> tipo, long parametro1, String parametro2) throws IllegalAccessException, InvocationTargetException, InstantiationException, CustomException {
        return (T) tipo.getConstructors()[0].newInstance(parametro1, parametro2);
    }

    public static <T> T crearComandoAutenticar(Class<T> tipo, LoginDto parametro1) throws IllegalAccessException, InvocationTargetException, InstantiationException, CustomException {
        return (T) tipo.getConstructors()[0].newInstance(parametro1, parametro1);
    }

    public static <T> T crearComandoLista(Class<T> tipo, List<?> parametro) throws IllegalAccessException, InvocationTargetException, InstantiationException, CustomException {
        return (T) tipo.getConstructors()[0].newInstance( parametro);
    }
}