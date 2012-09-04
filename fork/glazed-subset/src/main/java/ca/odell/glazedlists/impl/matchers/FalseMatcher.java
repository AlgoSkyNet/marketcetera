/* Glazed Lists                                                 (c) 2003-2006 */
/* http://publicobject.com/glazedlists/                      publicobject.com,*/
/*                                                     O'Dell Engineering Ltd.*/
package ca.odell.glazedlists.impl.matchers;

import ca.odell.glazedlists.matchers.Matcher;

/**
 * A {@link ca.odell.glazedlists.matchers.Matcher} implementation that never matches. Use
 * {@link #getInstance()} to obtain the singleton instance.
 *
 */
public final class FalseMatcher<E> implements Matcher<E> {

	/** Singleton instance of FalseMatcher. */
	private static final Matcher INSTANCE = new FalseMatcher();

    private FalseMatcher() {}

    /**
	 * Return a singleton instance.
	 */
	public static <E> Matcher<E> getInstance() {
		return (Matcher<E>) INSTANCE;
	}

    /** {@inheritDoc} */
	public boolean matches(E item) {
        return false;
	}
}