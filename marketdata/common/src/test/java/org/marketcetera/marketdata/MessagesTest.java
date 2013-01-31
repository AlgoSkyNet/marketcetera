package org.marketcetera.marketdata;

import org.junit.Test;
import org.marketcetera.core.util.l10n.MessageComparator;

import static org.junit.Assert.assertTrue;

/* $License$ */

/**
 * @since 0.6.0
 * @version $Id: MessagesTest.java 82306 2012-02-29 23:18:25Z colin $
 */
public class MessagesTest
{
    @Test
    public void messagesMatch()
        throws Exception
    {
        MessageComparator comparator=new MessageComparator(Messages.class);
        assertTrue(comparator.getDifferences(),comparator.isMatch());
    }
}