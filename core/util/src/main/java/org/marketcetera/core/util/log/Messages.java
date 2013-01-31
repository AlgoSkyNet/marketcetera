package org.marketcetera.core.util.log;

/**
 * The internationalization constants used by this package.
 *
 * @since 0.5.0
 * @version $Id: Messages.java 16063 2012-01-31 18:21:55Z colin $
 */

/* $License$ */

public interface Messages
{

    /**
     * The message provider.
     */

    static final I18NMessageProvider PROVIDER=
        new I18NMessageProvider("util_log"); //$NON-NLS-1$

    /**
     * The logger.
     */

    static final I18NLoggerProxy LOGGER=
        new I18NLoggerProxy(PROVIDER);

    /*
     * The messages.
     */

    static final I18NMessage2P MESSAGE_FILE_NOT_FOUND=
        new I18NMessage2P(LOGGER,"message_file_not_found"); //$NON-NLS-1$
    static final I18NMessage4P MESSAGE_NOT_FOUND=
        new I18NMessage4P(LOGGER,"message_not_found"); //$NON-NLS-1$
    static final I18NMessage4P UNEXPECTED_EXCEPTION=
        new I18NMessage4P(LOGGER,"unexpected_exception"); //$NON-NLS-1$
}