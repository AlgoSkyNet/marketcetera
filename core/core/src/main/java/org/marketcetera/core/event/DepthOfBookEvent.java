package org.marketcetera.core.event;

import java.util.List;

/* $License$ */

/**
 * Represents the Depth-of-Book for a specific instrument.
 *
 * @version $Id$
 * @since 1.5.0
 */
public interface DepthOfBookEvent
        extends AggregateEvent, HasInstrument
{
    /**
     * Get the asks value.
     *
     * @return a <code>List&lt;AskEvent&gt;</code> value
     */
    public List<AskEvent> getAsks();
    /**
     * Get the bids value.
     *
     * @return a <code>List&lt;BidEvent&gt;</code> value
     */
    public List<BidEvent> getBids();
}