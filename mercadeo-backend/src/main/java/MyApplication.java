import Controllers.UserController;
import Directorio_Activo.Configuration;
import lombok.SneakyThrows;

import javax.naming.Context;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


@ApplicationPath("/api")
public class MyApplication extends Application {


    @SneakyThrows
    @Override
    public Set<Class<?>> getClasses() {

        Configuration confLdap =  new Configuration("ldap://localhost:10389","secret");

        confLdap.connectLDAP();

        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add( UserController.class );

        return set;
    }
}
