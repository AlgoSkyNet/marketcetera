package org.marketcetera.core.metrics;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import org.marketcetera.core.attributes.ClassVersion;
import org.marketcetera.core.util.log.SLF4JLoggerProxy;

/* $License$ */
/**
 * A utility class for creating conditions that can be used with
 * {@link ThreadedMetric#end(java.util.concurrent.Callable, Object[])}.
 *
 * @author anshul@marketcetera.com
 * @version $Id: ConditionsFactory.java 16063 2012-01-31 18:21:55Z colin $
 * @since 2.0.0
 */
@ClassVersion("$Id: ConditionsFactory.java 16063 2012-01-31 18:21:55Z colin $")
public final class ConditionsFactory {
    /**
     * Returns a condition that is true on every n<sup>th</sup>
     * (where n = <code>inDefaultInterval</code>) invocation for a
     * particular thread.
     * <p>
     * The returned condition maintains a separate counter for each thread.
     *
     * @param inDefaultInterval the default interval at which the condition
     * should be true. This value is used as interval value if no value
     * can be found for the condition's property name. The interval value
     * has to be greater than 0.
     * @param inName the condition's property name. This property name is
     * used to obtain the property value from the {@link Configurator} which,
     * if found, is used as the interval value.
     *
     * @return the condition.
     * 
     * @see Configurator#getProperty(String, String) 
     */
    public static Callable<Boolean> createSamplingCondition(
            final int inDefaultInterval, final String inName) {
        if(inDefaultInterval <= 0) {
            throw new IllegalArgumentException(inDefaultInterval + " <= 0");   //$NON-NLS-1$
        }
        int value = inDefaultInterval;
        String pValue = Configurator.getProperty(inName, String.valueOf(inDefaultInterval));
        if(pValue != null) {
            try {
                value = Integer.parseInt(pValue);
            } catch (NumberFormatException e) {
                Messages.LOG_NON_NUMERIC_PROPERTY.warn(ConditionsFactory.class,
                        e, inName, pValue, inDefaultInterval);
            }
        }
        SLF4JLoggerProxy.debug(ConditionsFactory.class,
                "Initializing sampling condition for interval {}",  //$NON-NLS-1$ 
                value);
        return new IntervalSampler(value);
    }

    /**
     * Utility class. No instances can be created.
     */
    private ConditionsFactory() {
    }

    /**
     * A condition that is true on every n<sup>th</sup> check. Where 'n' is
     * the interval value supplied when instantiating this condition.
     */
    private static final class IntervalSampler implements Callable<Boolean> {
        /**
         * Creates an instance.
         *
         * @param inInterval the interval value.
         */
        public IntervalSampler(final int inInterval) {
            mInterval = inInterval;
            mCounter = new ThreadLocal<AtomicLong>() {
                @Override
                protected AtomicLong initialValue() {
                    return new AtomicLong();
                }
            };
        }

        @Override
        public Boolean call() throws Exception {
            return mCounter.get().incrementAndGet() % mInterval == 0;
        }

        private final ThreadLocal<AtomicLong> mCounter;
        private final int mInterval;
    }
}
