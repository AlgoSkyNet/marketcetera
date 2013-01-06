package org.marketcetera.api.symbolresolver.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.marketcetera.core.symbolresolver.Messages;
import org.marketcetera.core.symbolresolver.NoInstrumentForSymbol;
import org.marketcetera.core.symbolresolver.SymbolResolverElement;
import org.marketcetera.core.trade.Instrument;
import org.marketcetera.symbolresolver.MarketceteraSymbolResolverManager;
import org.mockito.Mock;

/* $License$ */

/**
 * Test the symbol resolver manager functionality.
 *
 * @version $Id$
 * @since $Release$
 */
public class SymbolResolverManagerImplTest {
	
	@Mock SymbolResolverElement mockSymbolResolver1 = mock(SymbolResolverElement.class);
	@Mock SymbolResolverElement mockSymbolResolver2 = mock(SymbolResolverElement.class);
	private MarketceteraSymbolResolverManager symbolResolverManager = new MarketceteraSymbolResolverManager();
	private static final String DEFAULT_SYMBOL="TEST"; 
	private Instrument mockInstrument = mock(Instrument.class);

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();	
	
	@Before
	public void setUp() {
		List<SymbolResolverElement> symbolResolverList = new ArrayList<SymbolResolverElement>();
		symbolResolverList.add(mockSymbolResolver1);
		symbolResolverList.add(mockSymbolResolver2);
		symbolResolverManager.setResolvers(symbolResolverList);
	}
	
	@Test
	public void testNoMatchingSymbolResolver() {
		expectedEx.expect(NoInstrumentForSymbol.class);
		expectedEx.expectMessage(Messages.UNABLE_TO_RESOLVE_SYMBOL.getMessageId());		
		when(mockSymbolResolver1.resolve(DEFAULT_SYMBOL, null)).thenReturn(null);
		when(mockSymbolResolver2.resolve(DEFAULT_SYMBOL, null)).thenReturn(null);
		symbolResolverManager.resolve(DEFAULT_SYMBOL);
		verify(mockSymbolResolver1, times(1)).resolve(DEFAULT_SYMBOL, null);
		verify(mockSymbolResolver2, times(1)).resolve(DEFAULT_SYMBOL, null);
	}

	@Test
	public void testFirstMatchingSymbolResolver() {
		when(mockSymbolResolver1.resolve(DEFAULT_SYMBOL, null)).thenReturn(mockInstrument);
		Instrument instrument = symbolResolverManager.resolve(DEFAULT_SYMBOL);
		verify(mockSymbolResolver1, times(1)).resolve(DEFAULT_SYMBOL, null);
		assertEquals("Didn't receive the expected instrument", instrument, mockInstrument);
	}

	@Test
	public void testSecondMatchingSymbolResolver() {
		when(mockSymbolResolver1.resolve(DEFAULT_SYMBOL, null)).thenReturn(null);
		when(mockSymbolResolver2.resolve(DEFAULT_SYMBOL, null)).thenReturn(mockInstrument);
		Instrument instrument = symbolResolverManager.resolve(DEFAULT_SYMBOL);
		verify(mockSymbolResolver1, times(1)).resolve(DEFAULT_SYMBOL, null);
		verify(mockSymbolResolver2, times(1)).resolve(DEFAULT_SYMBOL, null);
		assertEquals("Didn't receive the expected instrument", instrument, mockInstrument);
	}

}