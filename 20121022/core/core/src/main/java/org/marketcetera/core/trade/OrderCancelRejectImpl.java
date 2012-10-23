package org.marketcetera.core.trade;

import javax.xml.bind.annotation.XmlRootElement;
import quickfix.Message;

/* $License$ */
/**
 * OrderCancelReject instances that wrap a FIX Message instance. This
 * class is public for the sake of JAXB and is not intended for
 * general use.
 *
 * @version $Id: OrderCancelRejectImpl.java 16063 2012-01-31 18:21:55Z colin $
 * @since 1.0.0
 */
@XmlRootElement
public class OrderCancelRejectImpl extends ReportBaseImpl
        implements OrderCancelReject {
    /**
     * Creates an instance.
     *
     * @param inMessage The FIX Message instance.
     * @param inBrokerID the brokerID from which this report originated.
     * @param inOriginator the originator of this report.
     * @param inActorID the ID of the actor user of this report. It may be null.
     * @param inViewerID the ID of the viewer user of this report. It may be null.
     */
    OrderCancelRejectImpl(Message inMessage,
                          BrokerID inBrokerID,
                          Originator inOriginator,
                          UserID inActorID,
                          UserID inViewerID) {
        super(inMessage, inBrokerID, inOriginator, inActorID, inViewerID);
    }

    /**
     * Creates an instance. This empty constructor is intended for use
     * by JAXB.
     */

    protected OrderCancelRejectImpl() {}

    @Override
    public synchronized String toString() {
        return Messages.ORDER_CANCEL_REJECT_TO_STRING.getText(
                String.valueOf(getBrokerID()),
                String.valueOf(getOrderID()),
                String.valueOf(getOrderStatus()),
                String.valueOf(getOriginalOrderID()),
                String.valueOf(getReportID()),
                String.valueOf(getSendingTime()),
                String.valueOf(getText()),
                String.valueOf(getBrokerOrderID()),
                String.valueOf(getOriginator()),
                String.valueOf(getActorID()),
                String.valueOf(getViewerID()),
                String.valueOf(getMessage())
        );
    }

    private static final long serialVersionUID = 1L;
}
