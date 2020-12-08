package ConfigurationLdap;

import lombok.Getter;
import lombok.Setter;


import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Properties;

@Getter
@Setter
public class Configuration {

    private DirContext _ldapContext;
    private String _url;
    private String _connType;
    private String _directory;
    private String _userDirectory;
    private String _user;
    private String _password;
    private Properties env = new Properties();

    public Configuration(String _url, String _password) {

        this._url = _url;
        this._password = _password;

    }

    public DirContext connectLDAP() throws NamingException {
        try {

            env.put( Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
            env.put( Context.PROVIDER_URL, _url);
            env.put( Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
            env.put( Context.SECURITY_CREDENTIALS, _password );
            _ldapContext = new InitialDirContext(env);

           return _ldapContext;

        }catch ( Exception ex ) {

            throw ex;

        }
    }

    public void disconnectLDAP() throws NamingException {
        try {

            _ldapContext.close();

        }
        catch ( Exception ex ) {

            throw ex;

        }
    }

}
