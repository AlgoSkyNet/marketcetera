package org.marketcetera.core.marketdata;

import org.marketcetera.core.CoreException;
import org.marketcetera.core.util.log.I18NBoundMessage;

/**
 * Indicates an error throw during Market Data Feed operations.
 * 
 * @version $Id: FeedException.java 16063 2012-01-31 18:21:55Z colin $
 */
public class FeedException
    extends CoreException
{
    private static final long serialVersionUID = 1963381129325900265L;

    public FeedException(Throwable nested, I18NBoundMessage message) {
        super(nested, message);
    }

    public FeedException(I18NBoundMessage message) {
        super(message);
    }

    public FeedException(Throwable nested) {
        super(nested);
    }

}