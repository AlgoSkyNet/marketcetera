package org.marketcetera.api.dao;

import java.util.Set;

import org.marketcetera.api.systemmodel.NamedObject;
import org.marketcetera.api.systemmodel.SystemObject;
import org.marketcetera.api.systemmodel.VersionedObject;

/* $License$ */

/**
 * Represents a role granted to a {@link org.marketcetera.api.security.User}.
 *
 * @version $Id$
 * @since $Release$
 */
public interface Permission
        extends SystemObject, NamedObject, VersionedObject
{
    /**
     * Get the permissions attributes assigned to this permission.
     *
     * @return a <code>Set&lgt;PermissionAttribute&gt;</code> value
     */
    Set<PermissionAttribute> getMethod();
    /**
     * Get the permission subject.
     *
     * @return a <code>String</code> value
     */
    String getPermission();
}
