package org.marketcetera.core.ws.wrappers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @since 1.0.0
 * @version $Id: BaseWrapperTest.java 82324 2012-04-09 20:56:08Z colin $
 */

/* $License$ */

public class BaseWrapperTest
    extends WrapperTestBase
{
    private static final class TestWrapper
        extends BaseWrapper<String>
    {
        public TestWrapper
            (String value)
        {
            super(value);
        }

        private TestWrapper() {}

        public void setTestValue
            (String testValue)
        {
            setValue(testValue);
        }

        public String getTestValue()
        {
            return getValue();
        }
    }


    @Test
    public void all()
        throws Exception
    {
        TestWrapper empty=new TestWrapper();
        assertNull(empty.getTestValue());
        TestWrapper nullArg=new TestWrapper(null);
        assertNull(nullArg.getTestValue());
        single(new TestWrapper(TEST_VALUE),
               new TestWrapper(TEST_VALUE),
               empty,nullArg,
               TEST_VALUE);

        TestWrapper wrapper=new TestWrapper(TEST_VALUE);
        assertEquals(TEST_VALUE,wrapper.getTestValue());

        wrapper.setTestValue(TEST_VALUE_D);
        assertEquals(TEST_VALUE_D,wrapper.getTestValue());

        wrapper.setTestValue(null);
        assertNull(wrapper.getTestValue());
    }
}
