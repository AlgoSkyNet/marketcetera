package org.marketcetera.web.view.cluster;

import java.util.Properties;

import org.marketcetera.cluster.ClusterData;
import org.marketcetera.util.log.SLF4JLoggerProxy;
import org.marketcetera.web.SessionUser;
import org.marketcetera.web.service.WebMessageService;
import org.marketcetera.web.view.AbstractGridView;
import org.marketcetera.web.view.PagedDataContainer;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.VaadinSession;

/* $License$ */

/**
 * Provides a view for system Fix Sessions.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public class ClusterView
        extends AbstractGridView<ClusterData>
{
    /* (non-Javadoc)
     * @see com.marketcetera.web.view.AbstractGridView#attach()
     */
    @Override
    public void attach()
    {
        super.attach();
        getActionSelect().setVisible(false);
        getCreateNewButton().setVisible(false);
        getPageSizeSelect().setVisible(false);
        getCurrentPageLabel().setVisible(false);
        getFirstPageButton().setVisible(false);
        getLastPageButton().setVisible(false);
        getNextPageButton().setVisible(false);
        getPrevPageButton().setVisible(false);
    }
    /* (non-Javadoc)
     * @see com.marketcetera.web.view.ContentView#getViewName()
     */
    @Override
    public String getViewName()
    {
        return NAME;
    }
    /**
     * Create a new ClusterView instance.
     *
     * @param inViewProperties
     */
    ClusterView(Properties inViewProperties)
    {
    }
    /* (non-Javadoc)
     * @see com.marketcetera.web.view.AbstractGridView#setGridColumns()
     */
    @Override
    protected void setGridColumns()
    {
        getGrid().setColumns("mutableView",
                             "hostId",
                             "uuid",
                             "hostNumber",
                             "instanceNumber",
                             "totalInstances");
        getGrid().getColumn("mutableView").setHeaderCaption("Instance");
        getGrid().getColumns().forEach(column -> column.setSortable(false));
    }
    /* (non-Javadoc)
     * @see com.marketcetera.web.view.AbstractGridView#onActionSelect(com.vaadin.data.Property.ValueChangeEvent)
     */
    @Override
    protected void onActionSelect(ValueChangeEvent inEvent)
    {
        ClusterData selectedItem = getSelectedItem();
        if(selectedItem == null || inEvent.getProperty().getValue() == null) {
            return;
        }
        String action = String.valueOf(inEvent.getProperty().getValue());
        SLF4JLoggerProxy.info(this,
                              "{}: {} {} '{}'",
                              String.valueOf(VaadinSession.getCurrent().getAttribute(SessionUser.class)),
                              getViewName(),
                              action,
                              selectedItem);
    }
    /* (non-Javadoc)
     * @see com.marketcetera.web.view.AbstractGridView#createBeanItemContainer()
     */
    @Override
    protected PagedDataContainer<ClusterData> createDataContainer()
    {
        return new ClusterPagedDataContainer(this);
    }
    /* (non-Javadoc)
     * @see com.marketcetera.web.view.AbstractGridView#getViewSubjectName()
     */
    @Override
    protected String getViewSubjectName()
    {
        return "Cluster Instances";
    }
    /**
     * Get the webMessageService value.
     *
     * @return a <code>WebMessageService</code> value
     */
    WebMessageService getWebMessageService()
    {
        return webMessageService;
    }
    /**
     * Sets the webMessageService value.
     *
     * @param inWebMessageService a <code>WebMessageService</code> value
     */
    void setWebMessageService(WebMessageService inWebMessageService)
    {
        webMessageService = inWebMessageService;
    }
    /**
     * provides access to web message services
     */
    private WebMessageService webMessageService;
    /**
     * global name of this view
     */
    private static final String NAME = "Cluster View";
    private static final long serialVersionUID = -3527055937483045524L;
}