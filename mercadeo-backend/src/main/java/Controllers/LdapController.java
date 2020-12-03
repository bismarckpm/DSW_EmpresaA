package Controllers;

import Directorio_Activo.Configuration;
import Model.Person;
import Services.LdapService;
import lombok.SneakyThrows;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ldap")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LdapController {

    private LdapService ldapService = new LdapService();

    private DirContext connection;

    Configuration confLdap =  new Configuration("ldap://localhost:10389","secret");

    @SneakyThrows
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRol() throws NamingException {

            confLdap.connectLDAP();
         /*   String searchFilter = "(objectClass=inetOrgPerson)";
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
            }*/

            return "Hola papi";

    }
}
