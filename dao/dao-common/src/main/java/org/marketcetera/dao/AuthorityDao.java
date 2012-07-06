package org.marketcetera.dao;

import java.util.List;

import org.marketcetera.core.systemmodel.Authority;
import org.marketcetera.core.attributes.ClassVersion;
import org.springframework.security.access.prepost.PreAuthorize;

/* $License$ */

/**
 * Provides data access for {@link Authority} objects.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: AuthorityDao.java 82353 2012-05-10 21:56:11Z colin $
 * @since $Release$
 */
@ClassVersion("$Id: AuthorityDao.java 82353 2012-05-10 21:56:11Z colin $")
public interface AuthorityDao
{
    /**
     * Adds the given <code>Authority</code> to the database.
     *
     * @param inData an <code>Authority</code> value
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void add(Authority inData);
    /**
     * Saves the given <code>Authority</code> to the database.
     *
     * @param inData an <code>Authority</code> value
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void save(Authority inData);
    /**
     * Gets the <code>Authority</code> corresponding to the given name.
     *
     * @param inName a <code>String</code> value
     * @return an <code>Authority</code> value
     */
    public Authority getByName(String inName);
    /**
     * Gets the <code>Authority</code> corresponding to the given id.
     *
     * @param inId a <code>long</code> value
     * @return an <code>Authority</code> value
     */
    public Authority getById(long inId);
    /**
     * Gets all <code>Authority</code> values.
     *
     * @return a <code>List&lt;Authority&gt;</code> value
     */
    public List<Authority> getAll();
}
