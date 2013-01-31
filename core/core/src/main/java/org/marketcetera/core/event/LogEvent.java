package org.marketcetera.core.event;

/* $License$ */

/**
 * Represents a log entry event.
 *
 * @version $Id$
 * @since 1.5.0
 */
public interface LogEvent
        extends Event
{
    /**
     * Get the Level value.
     *
     * @return a <code>LogEventLevel</code> value
     */
    public LogEventLevel getLevel();
    /**
     * Get the exception value.
     *
     * @return a <code>Throwable</code> value
     */
    public Throwable getException();
    /**
     * Returns the bound event message. 
     *
     * @return a <code>String</code> value
     */
    public String getMessage();
}