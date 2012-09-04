package org.marketcetera.core.util.l10n;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.marketcetera.util.test.EqualityAssert.*;

/**
 * @since 0.6.0
 * @version $Id: MessageInfoPairTest.java 16063 2012-01-31 18:21:55Z colin $
 */

/* $License$ */

public class MessageInfoPairTest
    extends MessageInfoTestBase
{
    @Test
    public void all()
    {
        MessageInfoPair info=
            new MessageInfoPair(TEST_I18N_INFO,TEST_PROPERTY_INFO);
        assertEquals(TEST_I18N_INFO,info.getSrcInfo());
        assertEquals(TEST_PROPERTY_INFO,info.getDstInfo());

        assertEquality
            (info,
             new MessageInfoPair(TEST_I18N_INFO_SAME,TEST_PROPERTY_INFO_SAME),
             new MessageInfoPair(TEST_I18N_INFO_KD,TEST_PROPERTY_INFO),
             new MessageInfoPair(TEST_I18N_INFO,TEST_PROPERTY_INFO_KD));
    }
}
