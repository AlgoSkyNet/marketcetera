package org.marketcetera.modules.cep.system;

import org.marketcetera.core.util.log.*;

/* $License$ */
/**
 * Internationalized messages for this package.
 *
 * @since 1.0.0
 * @version $Id: Messages.java 16063 2012-01-31 18:21:55Z colin $
 */
public interface Messages {
    /**
     * The message provider
     */
    static final I18NMessageProvider PROVIDER =
            new I18NMessageProvider("modules_cep_system",  //$NON-NLS-1$
                    Messages.class.getClassLoader());
    /**
     * The message logger.
     */
    static final I18NLoggerProxy LOGGER = new I18NLoggerProxy(PROVIDER);
    static final I18NMessage0P PROVIDER_DESCRIPTION = new I18NMessage0P(LOGGER, "provider_description");   //$NON-NLS-1$
    static final I18NMessage1P INVALID_QUERY = new I18NMessage1P(LOGGER, "invalid_query");   //$NON-NLS-1$
    static final I18NMessage1P UNSUPPORTED_TYPE = new I18NMessage1P(LOGGER, "unsupported_type"); //$NON-NLS-1$
}