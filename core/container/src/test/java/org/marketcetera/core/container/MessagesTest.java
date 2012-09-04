package org.marketcetera.core.container;

import org.junit.Test;
import org.marketcetera.core.util.l10n.MessageComparator;
import org.marketcetera.util.test.TestCaseBase;

import static org.junit.Assert.*;

/**
 * @since 0.5.0
 * @version $Id: MessagesTest.java 82323 2012-04-09 16:32:23Z colin $
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
    }
}
