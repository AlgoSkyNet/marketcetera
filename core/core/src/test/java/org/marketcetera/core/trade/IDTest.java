package org.marketcetera.core.trade;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.marketcetera.core.ExpectedFailure;
import org.marketcetera.core.IDFactory;
import org.marketcetera.core.InMemoryIDFactory;
import org.marketcetera.core.LoggerConfiguration;
import org.marketcetera.core.NoMoreIDsException;
import org.marketcetera.util.test.ComparableAssert;
import org.marketcetera.util.test.EqualityAssert;
import org.marketcetera.util.test.SerializableAssert;

import static org.junit.Assert.*;

/* $License$ */
/**
 * Tests all the ID classes in this package. Tested classes
 * include {@link org.marketcetera.core.trade.OrderID}, {@link org.marketcetera.core.trade.UserID} &amp; {@link org.marketcetera.core.trade.BrokerID}.
 * <p>
 * Also tests 
 * {@link org.marketcetera.core.trade.Factory#setOrderIDFactory(org.marketcetera.core.IDFactory)}
 *
 * @author anshul@marketcetera.com
 * @version $Id: IDTest.java 16063 2012-01-31 18:21:55Z colin $
 * @since 1.0.0
 */
public class IDTest
{
    /**
     * Run once before all tests.
     *
     * @throws Exception if an unexpected error occurs
     */
    @BeforeClass
    public static void once()
            throws Exception
    {
        LoggerConfiguration.logSetup();
    }
    /**
     * Run after each test.
     *
     * @throws Exception if an unexpected error occurs
     */
    @After
    public void cleanup()
            throws Exception
    {
        Factory.getInstance().setOrderIDFactory(new InMemoryIDFactory(0));
    }
    /**
     * Verify {@link org.marketcetera.core.trade.OrderID}
     *
     * @throws Exception if there were errors
     */
    @Test
    public void orderID() throws Exception {
        new ExpectedFailure<NullPointerException>(){
            protected void run() throws Exception {
                new OrderID(null);
            }
        };
        EqualityAssert.assertEquality(new OrderID("ord-132"),
                new OrderID("ord-132"),
                new OrderID(""), new OrderID("ora-132"),
                new OrderID("ord-133"), new OrderID("xyzkji3948992"));
        
        OrderID id = new OrderID("yes");
        assertEquals("yes", id.getValue());
        assertEquals("yes", id.toString());
        SerializableAssert.assertSerializable(id);
    }

    /**
     * Verify {@link org.marketcetera.core.trade.BrokerID}
     *
     * @throws Exception if there were errors.
     */
    @Test
    public void brokerID() throws Exception {
        new ExpectedFailure<NullPointerException>(){
            protected void run() throws Exception {
                new BrokerID(null);
            }
        };
        EqualityAssert.assertEquality(new BrokerID("broke-132"),
                new BrokerID("broke-132"),
                new BrokerID(""), new BrokerID("brokr-132"),
                new BrokerID("broke-133"),
                new BrokerID("xyzkji3948992"));

        BrokerID id = new BrokerID("yes");
        assertEquals("yes", id.getValue());
        assertEquals("yes", id.toString());
        SerializableAssert.assertSerializable(id);
    }

    /**
     * Verify {@link org.marketcetera.core.trade.UserID}
     *
     * @throws Exception if there were errors.
     */
    @Test
    public void userID() throws Exception {
        EqualityAssert.assertEquality(new UserID(1),
                new UserID(1),
                new UserID(0));

        UserID id = new UserID(2);
        assertEquals(2, id.getValue());
        assertEquals("2", id.toString());
        SerializableAssert.assertSerializable(id);
    }

    /**
     * Verify {@link org.marketcetera.core.trade.ReportID}
     *
     * @throws Exception if there were errors
     */
    @Test
    public void reportID() throws Exception {
        EqualityAssert.assertEquality(new ReportID(23l),new ReportID(23),
                new ReportID(1), new ReportID(Integer.MAX_VALUE),
                new ReportID(Long.MAX_VALUE));
        ReportID id = new ReportID(2343l);
        assertEquals("2343", id.toString());
        assertEquals(2343l, id.longValue());
        SerializableAssert.assertSerializable(id);
        ComparableAssert.assertComparable(new ReportID(12), new ReportID(12),
                new ReportID(13));
    }

    @Test
    public void orderIDFactory() throws Exception {
        final Factory factory = Factory.getInstance();
        final String prefix = "cetera";
        //verify that the current factory IDs do not have this prefix
        assertFalse(factory.createOrderSingle().getOrderID().toString().startsWith(prefix));
        assertFalse(factory.createOrderCancel(null).getOrderID().toString().startsWith(prefix));
        assertFalse(factory.createOrderReplace(null).getOrderID().toString().startsWith(prefix));

        //now set the factory to the one that uses prefix
        factory.setOrderIDFactory(new TestIDFactory(10101, prefix));
        //Verify all IDs have the prefix.
        assertTrue(factory.createOrderSingle().getOrderID().toString().startsWith(prefix));
        assertTrue(factory.createOrderCancel(null).getOrderID().toString().startsWith(prefix));
        assertTrue(factory.createOrderReplace(null).getOrderID().toString().startsWith(prefix));
        //Verify that the factory cannot be set to null
        new ExpectedFailure<NullPointerException>(){
            protected void run() throws Exception {
                factory.setOrderIDFactory(null);
            }
        };
        //set an ID factory that fails to generate IDs
        factory.setOrderIDFactory(new TestIDFactory(1, "wha"){
            @Override
            public String getNext() throws NoMoreIDsException {
                throw new NoMoreIDsException(new IllegalArgumentException("test"));
            }
        });
        new ExpectedFailure<IllegalArgumentException>(
                org.marketcetera.core.trade.Messages.UNABLE_TO_GENERATE_IDS.getText()){
            protected void run() throws Exception {
                factory.createOrderSingle();
            }
        };

    }

    @SuppressWarnings("unused")
    private ReportID getNextReportID() throws MessageCreationException {
        return Factory.getInstance().createExecutionReport(
                TypesTestBase.createEmptyExecReport(),
                new BrokerID("bro"), Originator.Server, null, null).
            getReportID();
    }

    /**
     * TestIDFactory that generates IDs based on an initial value
     * and a prefix.
     *
     */
        public static class TestIDFactory implements IDFactory {
        public TestIDFactory(long initValue, String inPrefix) {
            mPrefix = inPrefix;
            mGenerator.set(initValue);
        }

        @Override
        public String getNext() throws NoMoreIDsException {
            return mPrefix + mGenerator.incrementAndGet();
        }

        @Override
        public void init() {
            //Do nothing.
        }
        private final AtomicLong mGenerator = new AtomicLong();
        private final String mPrefix;
    }
}