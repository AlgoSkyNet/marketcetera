package org.marketcetera.core.util.except;

import org.junit.Test;
import org.marketcetera.core.util.l10n.MessageComparator;
import org.marketcetera.util.test.TestCaseBase;

import static org.junit.Assert.*;

/**
 * @since 0.5.0
 * @version $Id: MessagesTest.java 16063 2012-01-31 18:21:55Z colin $
 */

/* $License$ */

public class MessagesTest
    extends TestCaseBase
{
    @Test
    public void messagesMatch()
        throws Exception
    {
        MessageComparator comparator=new MessageComparator(Messages.class);
        assertTrue(comparator.getDifferences(),comparator.isMatch());

        comparator=new MessageComparator(TestMessages.class);
        assertTrue(comparator.getDifferences(),comparator.isMatch());

        // French locale not tested because omission of message
        // top_exception.msg is intentional.
    }
}