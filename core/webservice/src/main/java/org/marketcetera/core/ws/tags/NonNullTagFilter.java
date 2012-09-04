package org.marketcetera.core.ws.tags;

import org.marketcetera.core.util.except.I18NException;
import org.marketcetera.core.util.log.I18NBoundMessage;

/**
 * A tag filter that accepts any non-null tag.
 * 
 * @since 1.0.0
 * @version $Id: NonNullTagFilter.java 82324 2012-04-09 20:56:08Z colin $
 */

/* $License$ */

public class NonNullTagFilter
    implements TagFilter
{

    // INSTANCE DATA.

    private final I18NBoundMessage mMessage;


    // CONSTRUCTORS.

    /**
     * Creates a new filter with the given mismatch message.
     *
     * @param message The message for the exception thrown when the
     * filter rejects a tag.
     */

    public NonNullTagFilter
        (I18NBoundMessage message)
    { 
        mMessage=message;
    }


    // INSTANCE METHODS.

    /**
     * Returns the message for the exception thrown when the receiver
     * filter rejects a tag.
     *
     * @return The message.
     */

    public I18NBoundMessage getMessage()
    {
        return mMessage;
    }


    // TagFilter.

    @Override
    public void assertMatch
        (Tag tag)
        throws I18NException
    {
        if (tag!=null) {
            return;
        }
        throw new I18NException(getMessage());
    }
}
