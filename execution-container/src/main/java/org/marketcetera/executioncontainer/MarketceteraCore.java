package org.marketcetera.executioncontainer;

import org.marketcetera.core.module.ModuleManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/* $License$ */

/**
 * Initializes Marketcetera core components.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: MarketceteraCore.java 82351 2012-05-04 21:46:58Z colin $
 * @since $Release$
 */
public class MarketceteraCore
        implements InitializingBean
{
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet()
            throws Exception
    {
        moduleManager.init();
    }
    /**
     * system-wide module manager instance
     */
    @Autowired
    private volatile ModuleManager moduleManager;
}