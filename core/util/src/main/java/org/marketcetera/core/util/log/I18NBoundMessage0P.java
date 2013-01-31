package org.marketcetera.core.util.log;

import java.io.Serializable;

/**
 * A bound message, representing a {@link I18NMessage0P}.
 * 
 * @since 0.5.0
 * @version $Id: I18NBoundMessage0P.java 16063 2012-01-31 18:21:55Z colin $
 */

/* $License$ */

public class I18NBoundMessage0P
    extends I18NBoundMessageBase<I18NMessage0P>
{

    // CLASS DATA.

    private static final long serialVersionUID=1L;


    // CONSTRUCTORS.

    /**
     * Constructor mirroring superclass constructor.
     *
     * @see I18NBoundMessageBase#I18NBoundMessageBase(I18NMessage,Serializable...)
     */

    public I18NBoundMessage0P
        (I18NMessage0P message)
    {
        super(message);
    }
}