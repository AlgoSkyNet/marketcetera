package org.marketcetera.dao.impl;

import java.util.Set;

/* $License$ */

/**
 * Specifies a user and its member groups.
 *
 * @version $Id$
 * @since $Release$
 */
public class UserSpecification
{
    /**
     * Sets the username value.
     *
     * @param a <code>String</code> value
     */
    public void setUsername(String inUsername)
    {
        username = inUsername;
    }
    /**
     * Sets the password value.
     *
     * @param a <code>String</code> value
     */
    public void setPassword(String inPassword)
    {
        password = inPassword;
    }
    /**
     * Sets the groups value.
     *
     * @param a <code>Set&lt;String&gt;</code> value
     */
    public void setRoles(Set<String> inRoles)
    {
        roles = inRoles;
    }
    /**
     * Get the username value.
     *
     * @return a <code>String</code> value
     */
    public String getUsername()
    {
        return username;
    }
    /**
     * Get the password value.
     *
     * @return a <code>String</code> value
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * Get the groups value.
     *
     * @return a <code>Set&lt;String&gt;</code> value
     */
    public Set<String> getRoles()
    {
        return roles;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserSpecification)) {
            return false;
        }
        UserSpecification other = (UserSpecification) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("UserSpecification [username=").append(username).append(", groups=").append(roles).append("]");
        return builder.toString();
    }
    /**
     * username value
     */
    private String username;
    /**
     * password value
     */
    private String password;
    /**
     * groups value
     */
    private Set<String> roles;
}