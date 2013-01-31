package org.marketcetera.core;

import org.marketcetera.core.util.except.ExpectedFailure;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Id: AccessViolatorTest.java 16063 2012-01-31 18:21:55Z colin $
 */
public class AccessViolatorTest extends TestCase {


    public AccessViolatorTest(String inName) {
        super(inName);
    }

    public static Test suite() {
        return new MarketceteraTestSuite(AccessViolatorTest.class);
    }

    public void testReturnValues() throws Exception {
        AccessViolator violator = new AccessViolator(ViolatedClass.class);
        ViolatedClass violated = new ViolatedClass();
        assertEquals(7, ((Integer)violator.getField("YOU_CANT_READ_ME", violated)).intValue()); //$NON-NLS-1$
        assertTrue(((String)violator.invokeMethod("youCantCallMe", violated, "2+2 is 4")).endsWith("2+2 is 4")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public void testException() throws Exception {
        final AccessViolator violator = new AccessViolator(ViolatedClass.class);
        final ViolatedClass violated = new ViolatedClass();
        new ExpectedFailure<Exception>() {
            @Override
            protected void run()
                    throws Exception
            {
                violator.invokeMethod("youCantGetMyException", violated); //$NON-NLS-1$
            }
        };
    }

    public void testSetter() throws Exception {
        AccessViolator violator = new AccessViolator(ViolatedClass.class);
        ViolatedClass violated = new ViolatedClass();
        assertEquals(ViolatedClass.HIDDEN_VALUE, violator.getField("hidden", violated)); //$NON-NLS-1$

        // now set it
        violator.setField("hidden", violated, "violated"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("violated", violator.getField("hidden", violated)); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public void testPrimitiveTypeGetter() throws Exception {
        final AccessViolator violator = new AccessViolator(ViolatedClass.class);
        final ViolatedClass violated = new ViolatedClass();

        new ExpectedFailure<NoSuchMethodException>(){
            @Override
            protected void run()
                    throws Exception
            {
                violator.invokeMethod("doSomethingWithTwoBooleans", violated, true, true); //$NON-NLS-1$
            }
        };
        violator.invokeMethod("doSomethingWithTwoBooleans", violated, new Object[] {true, true}, new Class<?>[] {Boolean.TYPE, Boolean.TYPE}); //$NON-NLS-1$
        
    }
}