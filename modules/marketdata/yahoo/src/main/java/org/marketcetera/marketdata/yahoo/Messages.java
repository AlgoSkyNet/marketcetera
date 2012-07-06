package org.marketcetera.marketdata.yahoo;

import org.marketcetera.core.util.log.I18NLoggerProxy;
import org.marketcetera.core.util.log.I18NMessage0P;
import org.marketcetera.core.util.log.I18NMessage1P;
import org.marketcetera.core.util.log.I18NMessageProvider;
import org.marketcetera.core.attributes.ClassVersion;

/* $License$ */

/**
 * Messages for Yahoo market data adapter package.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: Messages.java 16063 2012-01-31 18:21:55Z colin $
 * @since 2.1.4
 */
@ClassVersion("$Id: Messages.java 16063 2012-01-31 18:21:55Z colin $")
public interface Messages
{
    static final I18NMessageProvider PROVIDER = new I18NMessageProvider("yahoo", //$NON-NLS-1$
                                                                        Messages.class.getClassLoader());
    static final I18NLoggerProxy LOGGER = new I18NLoggerProxy(PROVIDER);
    static final I18NMessage0P PROVIDER_DESCRIPTION = new I18NMessage0P(LOGGER,
                                                                        "provider_description"); //$NON-NLS-1$
    static final I18NMessage1P UNEXPECTED_DATA = new I18NMessage1P(LOGGER,
                                                                   "unexpected_data"); //$NON-NLS-1$
    static final I18NMessage1P UNEXPECTED_FIELD_CODE = new I18NMessage1P(LOGGER,
                                                                         "unexpected_field_code"); //$NON-NLS-1$
    static final I18NMessage0P MISSING_URL = new I18NMessage0P(LOGGER,
                                                               "missing_url"); //$NON-NLS-1$
    static final I18NMessage1P NO_COMPARATOR = new I18NMessage1P(LOGGER,
                                                                 "no_comparator"); //$NON-NLS-1$
}
