package org.marketcetera.strategy;

import java.util.Properties;

import org.marketcetera.core.util.misc.ClassVersion;

/* $License$ */

/**
 * A <code>Strategy</code> object to be executed.
 *
 * @version $Id: Strategy.java 16063 2012-01-31 18:21:55Z colin $
 * @since 1.0.0
 */
public interface Strategy
{
    /**
     * logger category which collects strategy messages
     */
    public static final String STRATEGY_MESSAGES = org.marketcetera.core.Messages.USER_MSG_CATEGORY;
    /**
     * environment property name used to indicate the source directory of included strategy scripts
     */
    public static final String CLASSPATH_PROPERTYNAME = "strategy.classpath"; //$NON-NLS-1$
    /**
     * Sends data received from an external source to a strategy.
     *
     * @param inData an <code>Object</code> value
     */
    public void dataReceived(Object inData);
    /**
     * Gets the script to execute.
     *
     * @return a <code>String</code> value
     */
    public String getScript();
    /**
     * Gets the language in which to interpret the strategy script.
     *
     * @return a <code>Language</code> value
     */
    public Language getLanguage();
    /**
     * Get the name value.
     *
     * @return a <code>String</code> value
     */
    public String getName();
    /**
     * Gets the parameters to pass to the strategy script.
     *
     * @return a <code>Properties</code> value
     */
    public Properties getParameters();
    /**
     * Starts the execution of the strategy.
     *
     * @throws StrategyException if the strategy cannot start
     */
    public void start()
        throws StrategyException;
    /**
     * Stops the execution of the strategy.
     *
     * @throws Exception  if an error occurred while stopping the strategy
     */
    public void stop()
        throws Exception;
    /**
     * Gets the strategy status.
     *
     * @return a <code>Status</code> value
     */
    public Status getStatus();
    /**
     * Returns the services provider for this <code>Strategy</code> to use.
     *
     * @return a <code>ServicesProvider</code> value
     */
    public ServicesProvider getServicesProvider();
    /**
     * Returns the default namespace for this strategy.
     *
     * @return a <code>String</code> value
     */
    public String getDefaultNamespace();
    /**
     * Returns the executor used to execute this strategy, if one has been assigned.
     *
     * @return an <code>Executor</code> value or null depending on the progress of the
     *  strategy in its lifecycle
     */
    public Executor getExecutor();
}
