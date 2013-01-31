package org.marketcetera.core.symbology;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @version $Id: PropertiesExchangeMapTest.java 16063 2012-01-31 18:21:55Z colin $
 */
public class PropertiesExchangeMapTest extends TestCase{
    public PropertiesExchangeMapTest(String inName) {
        super(inName);
    }

    public static Test suite() {
        return new TestSuite(PropertiesExchangeMapTest.class);
    }

    public void testHyperfeed() throws IOException {
        PropertiesExchangeMap map = new PropertiesExchangeMap("hyperfeed-exchanges.properties"); //$NON-NLS-1$
        assertEquals("XASE", map.getExchange("A ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XBOS", map.getExchange("B ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XCIS", map.getExchange("C ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XISX", map.getExchange("I ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XCHI", map.getExchange("M ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XNYS", map.getExchange("N ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XARC", map.getExchange("P ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XNAS", map.getExchange("Q ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XPHL", map.getExchange("X ").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
    }


    public void testBasic() throws IOException {
        PropertiesExchangeMap map = new PropertiesExchangeMap("basic-exchanges.properties"); //$NON-NLS-1$
        assertEquals("XASE", map.getExchange("A").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XBOS", map.getExchange("B").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XISX", map.getExchange("IO").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XNYS", map.getExchange("N").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XARC", map.getExchange("PO").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("XNAS", map.getExchange("Q").getMarketIdentifierCode()); //$NON-NLS-1$ //$NON-NLS-2$
    }

}