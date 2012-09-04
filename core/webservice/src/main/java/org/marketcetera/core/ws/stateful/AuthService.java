package org.marketcetera.core.ws.stateful;

import javax.jws.WebParam;
import javax.jws.WebService;
import org.marketcetera.core.ws.stateless.StatelessClientContext;
import org.marketcetera.core.ws.tags.SessionId;
import org.marketcetera.core.ws.wrappers.RemoteException;

/**
 * An authentication service interface.
 * 
 * @since 1.0.0
 * @version $Id: AuthService.java 82324 2012-04-09 20:56:08Z colin $
 */

/* $License$ */

@WebService(targetNamespace="http://marketcetera.org/services")
public interface AuthService
    extends ServiceBase
{

    /**
     * Logs in the client with the given context, provided the given
     * credentials are acceptable.
     *
     * @param context The context.
     * @param user The user name.
     * @param password The password. Upon return, its contents are
     * cleared (on the server) by overwriting all prior characters
     * with the nul ('\0') character.
     *
     * @return The ID of the new session.
     *
     * @throws RemoteException Thrown if the operation cannot be
     * completed.
     */

    SessionId login
        (@WebParam(name="context") StatelessClientContext context,
         @WebParam(name="user") String user,
         @WebParam(name="password") char[] password)
        throws RemoteException;

    /**
     * Logs out the client with the given context. This method is a
     * no-op if there is no active session for that client. 
     *
     * @param context The context.
     *
     * @throws RemoteException Thrown if the operation cannot be
     * completed.
     */

    void logout
        (@WebParam(name="context") ClientContext context)
        throws RemoteException;
}
