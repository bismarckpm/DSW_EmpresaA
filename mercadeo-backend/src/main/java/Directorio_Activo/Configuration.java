package Directorio_Activo;

import lombok.Getter;
import lombok.Setter;

import Exception.LdapException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

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

    public Configuration(String _url, String _connType, String _directory, String _userDirectory, String _user,
                         String _password) {

        this._url = _url;
        this._connType = _connType;
        this._directory = _directory;
        this._userDirectory = _userDirectory;
        this._user = _user;
        this._password = _password;

    }

    public void connectLDAP(String user, String password) throws LdapException {
        try {

            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put( Context.PROVIDER_URL, _url );
            environment.put( Context.SECURITY_AUTHENTICATION, _connType );
            environment.put( Context.SECURITY_PRINCIPAL, String.format( "uid=%s,ou=system", user ) );
            environment.put( Context.SECURITY_CREDENTIALS, password );
            _ldapContext = new InitialDirContext( environment );
        } catch ( Exception ex ) {

            throw  new LdapException("Error al conectar servidor Ldap", ex.getCause());

        }
    }

    public void disconnectLDAP() throws LdapException {
        try {

            _ldapContext.close();

        }
        catch ( Exception ex ) {

            throw  new LdapException("Error al desconectar servidor Ldap", ex.getCause());

        }
    }

}
