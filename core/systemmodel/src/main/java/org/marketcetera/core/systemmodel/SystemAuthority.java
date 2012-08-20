package org.marketcetera.core.systemmodel;

/* $License$ */

import org.marketcetera.api.security.GrantedAuthority;

/**
 * Describes authority levels in the system.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id: SystemAuthority.java 82320 2012-04-02 17:03:23Z colin $
 * @since $Release$
 */
public enum SystemAuthority
{
    /**
     * full administrative rights to change system entities
     */
    ROLE_ADMIN,
    /**
     * right to use business functions
     */
    ROLE_USER;
    /**
     * Gets this value represented as a <code>GrantedAuthority</code>.
     *
     * @return a <code>GrantedAuthority</code> value
     */
    public GrantedAuthority getAsGrantedAuthority()
    {
        return new GrantedAuthority() {
            @Override
            public String getAuthority()
            {
                return name();
            }
            private static final long serialVersionUID = 1L;
        };
    }
}
