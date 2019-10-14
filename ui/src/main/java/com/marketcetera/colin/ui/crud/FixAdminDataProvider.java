package com.marketcetera.colin.ui.crud;

import java.util.List;

import org.marketcetera.fix.ActiveFixSession;
import org.marketcetera.fix.FixAdminClient;
import org.marketcetera.util.log.SLF4JLoggerProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

import com.google.common.collect.Lists;
import com.marketcetera.colin.backend.client.FixAdminClientService;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.provider.QuerySortOrder;

/* $License$ */

/**
 *
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public class FixAdminDataProvider
        extends FilterablePageableDataProvider<ActiveFixSession,String>
{
    public FixAdminDataProvider(FixAdminClientService inClientService)
    {
        clientService = inClientService;
    }
    /* (non-Javadoc)
     * @see org.vaadin.artur.spring.dataprovider.PageableDataProvider#fetchFromBackEnd(com.vaadin.flow.data.provider.Query, org.springframework.data.domain.Pageable)
     */
    @Override
    protected Page<ActiveFixSession> fetchFromBackEnd(Query<ActiveFixSession,String> inQuery,
                                                      Pageable inPage)
    {
        SLF4JLoggerProxy.debug(this,
                               "fetchFromBackEnd {} {}",
                               inQuery,
                               inPage);
        FixAdminClient fixAdminClient = getClient();
        List<ActiveFixSession> activeFixSessions = fixAdminClient.readFixSessions();
        Page<ActiveFixSession> outputPage = new PageImpl<>(activeFixSessions,
                                                           inPage,
                                                           activeFixSessions.size());
        SLF4JLoggerProxy.debug(this,
                               "returning {}",
                               outputPage);
        return outputPage;
    }
    /* (non-Javadoc)
     * @see org.vaadin.artur.spring.dataprovider.PageableDataProvider#getDefaultSortOrders()
     */
    @Override
    protected List<QuerySortOrder> getDefaultSortOrders()
    {
        return Lists.newArrayList(QuerySortOrder.asc("FixSession").build());
    }
    /* (non-Javadoc)
     * @see com.vaadin.flow.data.provider.AbstractBackEndDataProvider#sizeInBackEnd(com.vaadin.flow.data.provider.Query)
     */
    @Override
    protected int sizeInBackEnd(Query<ActiveFixSession,String> inQuery)
    {
        FixAdminClient fixAdminClient = getClient();
        List<ActiveFixSession> activeFixSessions = fixAdminClient.readFixSessions();
        return activeFixSessions.size();
    }
    private FixAdminClient getClient()
    {
        try {
            return clientService.getClient();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private FixAdminClientService clientService;
    private static final long serialVersionUID = 1052645706835426435L;
}
