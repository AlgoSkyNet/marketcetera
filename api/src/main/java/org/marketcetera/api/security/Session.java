package org.marketcetera.api.security;

import java.io.Serializable;

/**
 * Provides long-running context for a {@link Subject} across multiple stateless calls.
 *
 * @version $Id$
 * @date 8/19/12 4:00 AM
 */

public interface Session {
    Serializable identifier();
    void invalidate();
}
