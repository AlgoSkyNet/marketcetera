package org.marketcetera.core.module;

import org.junit.Test;
import org.marketcetera.core.util.l10n.MessageComparator;

import static org.junit.Assert.assertTrue;

/* $License$ */
/**
 * Verifies that all the messages are correctly specified.
 *
 * @author anshul@marketcetera.com
 */
public class MessagesTest {
    @Test
    public void messagesMatch() throws Exception {
        MessageComparator comparator=new MessageComparator(Messages.class);
        assertTrue(comparator.getDifferences(),comparator.isMatch());
    }
    @Test
    public void testMessagesMatch() throws Exception {
        MessageComparator comparator=new MessageComparator(TestMessages.class);
        assertTrue(comparator.getDifferences(),comparator.isMatch());
    }
}