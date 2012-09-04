package org.marketcetera.core.trade;

/**
 * Represents an ORS response that wraps a generic FIX message which
 * cannot be wrapped by any other FIX Agnostic wrapper.
 *
 * @author tlerios@marketcetera.com
 * @since 2.0.0
 * @version $Id: FIXResponse.java 16063 2012-01-31 18:21:55Z colin $
 */

/* $License$ */

public interface FIXResponse
    extends TradeMessage,
            FIXMessageSupport
{
    /**
     * Returns the ID of the broker of this response.
     *
     * @return The ID. It may be null, e.g. for responses generated by
     * the server.
     */

    BrokerID getBrokerID();

    /**
     * Returns the originator of this response.
     *
     * @return The originator.
     */

    Originator getOriginator();

    /**
     * Returns the ID of the actor user of this response.
     *
     * @return The ID. It may be null, e.g. if the actor is unknown
     * (the system cannot associate this response with an order).
     */

    UserID getActorID();

    /**
     * Returns the ID of the viewer user of this response.
     *
     * @return The ID. It may be null, e.g. if the viewer is unknown
     * (the system cannot associate this response with an order).
     */

    UserID getViewerID();
}