package org.marketcetera.marketdata.core.rpc;

import org.marketcetera.marketdata.core.webservice.MarketDataServiceClientFactory;
import org.marketcetera.util.misc.ClassVersion;
import org.marketcetera.util.ws.ContextClassProvider;

/* $License$ */

/**
 * Creates <code>MarketDataServiceRpcClient</code> objects.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since 2.4.0
 */
@ClassVersion("$Id$")
public class MarketDataRpcClientFactory
        implements MarketDataServiceClientFactory
{
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.core.webservice.MarketDataServiceClientFactory#create(java.lang.String, java.lang.String, java.lang.String, int, org.marketcetera.util.ws.ContextClassProvider)
     */
    @Override
    public MarketDataRpcClient create(String inUsername,
                                      String inPassword,
                                      String inHostname,
                                      int inPort,
                                      ContextClassProvider inContextClassProvider)
    {
        MarketDataRpcClient client = new MarketDataRpcClient();
        client.setUsername(inUsername);
        client.setPassword(inPassword);
        client.setHostname(inHostname);
        client.setPort(inPort);
        client.setContextClassProvider(inContextClassProvider);
        return client;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.marketdata.core.webservice.MarketDataServiceClientFactory#create(java.lang.String, java.lang.String, java.lang.String, int)
     */
    @Override
    public MarketDataRpcClient create(String inUsername,
                                      String inPassword,
                                      String inHostname,
                                      int inPort)
    {
        return create(inUsername,
                      inPassword,
                      inHostname,
                      inPort,
                      null);
    }
}
