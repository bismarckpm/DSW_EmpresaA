package Directorio_Activo;

import Interfaces.ILdap;
import Model.Person;
import org.w3c.dom.Attr;

import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class ImpLdap implements ILdap {

    DirContext connection;

    public Person getListPerson() throws NamingException {


        String searchFilter = "(objectClass=inetOrgPerson)";
        String[] reqAtt = {"cn"};
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("ou=users,ou=system", searchFilter, controls);

        SearchResult result = null;

        while (users.hasMore()){
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            System.out.print("Personas" + attr.get("cn"));
        }

        return null;
    }
}
