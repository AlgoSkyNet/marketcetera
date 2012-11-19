package org.marketcetera.symbolresolver;

import javax.annotation.concurrent.Immutable;

import org.marketcetera.core.options.OptionUtils;
import org.marketcetera.core.symbolresolver.SymbolResolverElement;
import org.marketcetera.core.trade.Instrument;

/* $License$ */

/**
 * Attempts to resolve symbols to {@link org.marketcetera.core.trade.Option} instruments.
 *
 * @version $Id: OsiOptionSymbolResolver.java 82347 2012-05-03 19:30:54Z colin $
 * @since $Release$
 */
@Immutable
public class OsiOptionSymbolResolver
        implements SymbolResolverElement
{
    /* (non-Javadoc)
     * @see org.marketcetera.symbolresolver.SymbolResolver#resolve(java.lang.String)
     */
    @Override
    public Instrument resolve(String inSymbol)
    {
        return resolve(inSymbol,
                       null);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.symbolresolver.SymbolResolver#resolve(java.lang.String, java.lang.Object)
     */
    @Override
    public Instrument resolve(String inSymbol,
                              Object inContext)
    {
        try {
            return OptionUtils.getOsiOptionFromString(inSymbol);
        } catch (IllegalArgumentException e) {
            // no option, no soup
        }
        return null;
    }
}
