package Services;

import Directorio_Activo.ImpLdap;
import Interfaces.ILdap;
import Model.Person;

import javax.naming.NamingException;

public class LdapService {

    private ImpLdap impLdap = new ImpLdap();

    public Person getListPerson() throws NamingException {
        return impLdap.getListPerson();
    }
}
