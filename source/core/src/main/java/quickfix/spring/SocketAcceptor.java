package quickfix.spring;

import org.marketcetera.core.ClassVersion;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import quickfix.*;

/**
 * Wrapper around the @{link quickfix.SocketAcceptor} to be used for creation via Spring
 * that adds the @{link InitializingBean} and @{link DisposableBean} behaviour. 
 * @author toli
 * @version $Id$
 */
@ClassVersion("$Id$")
public class SocketAcceptor extends quickfix.SocketAcceptor implements InitializingBean, DisposableBean {
    public SocketAcceptor(Application application, MessageStoreFactory messageStoreFactory,
                          SessionSettings settings, LogFactory logFactory, MessageFactory messageFactory) throws ConfigError {
        super(application, messageStoreFactory, settings, logFactory, messageFactory);
    }


    public void afterPropertiesSet() throws Exception {
        start();
    }

    public void destroy() throws Exception {
        stop(true);
    }
}
