package org.marketcetera.core.event;

/* $License$ */

/**
 * Represents a Trade for a given security at a specific time.
 *
 * @version $Id$
 * @since 0.5.0
 */
public interface TradeEvent
        extends MarketDataEvent
{
    /**
     * Gets the time the event occurred. 
     *
     * <p>The format of the returned value is dependent on the
     * originating market data provider.
     * 
     * <p>This is the same as {@link #getExchangeTimestamp()}.
     *
     * @return a <code>String</code> value
     */
    public String getTradeDate();
}