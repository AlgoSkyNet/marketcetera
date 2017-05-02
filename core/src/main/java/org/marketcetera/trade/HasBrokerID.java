package org.marketcetera.trade;

/* $License$ */

/**
 *
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public interface HasBrokerID
{
    /**
     * Returns the ID of the broker of this response.
     *
     * @return The ID. It may be null, e.g. for responses generated by
     * the server.
     */
    BrokerID getBrokerID();
}