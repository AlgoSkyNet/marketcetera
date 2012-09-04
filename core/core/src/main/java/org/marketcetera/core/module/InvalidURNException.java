package org.marketcetera.core.module;

import org.marketcetera.core.util.log.I18NBoundMessage;

/* $License$ */
/**
 * This exception is thrown if an invalid URN is used to identify
 * a module provider or a module instance.
 *
 * @author anshul@marketcetera.com
 * @version $Id: InvalidURNException.java 16063 2012-01-31 18:21:55Z colin $
 * @since 1.0.0
 */
public class InvalidURNException extends ModuleException {
    /**
     * Creates an instance.
     *
     * @param message the error message.
     */
    InvalidURNException(I18NBoundMessage message) {
        super(message);
    }

    private static final long serialVersionUID = 4417630626628513614L;
}