/* Glazed Lists                                                 (c) 2003-2006 */
/* http://publicobject.com/glazedlists/                      publicobject.com,*/
/*                                                     O'Dell Engineering Ltd.*/
package ca.odell.glazedlists.matchers;

/**
 * Determines which values should be filtered.
 *
 * <p>For best safety, implementations of {@link Matcher} should be
 * <a href="http://en.wikipedia.org/wiki/Immutable_object">immutable</a>. This
 * guarantees that {@link ca.odell.glazedlists.FilterList}s can safely call
 * {@link #matches(Object) matches()} without synchronization.
 *
 * <p>In order to create dynamic filtering, use a
 * {@link MatcherEditor}, which
 * can create immutable {@link Matcher} Objects each time the matching constraints
 * change.
 *
 * @see ca.odell.glazedlists.FilterList
 * @see MatcherEditor
 */
public interface Matcher<E> {

    /**
     * Return true if an item matches a filter.
     *
     * @param item The item possibly being filtered.
     */
    public boolean matches(E item);
}