package org.marketcetera.core.quickfix;

import quickfix.*;

/**
 * Similar to {@link quickfix.Application} except for we "know" about the current session
 * so we don't need to pass it around all the time
 * 
 * @version $Id: QuickFIXSessionAdapter.java 16063 2012-01-31 18:21:55Z colin $
 */
public interface QuickFIXSessionAdapter
{

    /// Notification of a session succefully logging on
    public void onLogon();

    /// Notification of a session logging off or disconnecting
    public void onLogout();

    /// Notification of admin message being sent to target
    public void toAdmin(Message message);

    /// Notification of app message being sent to target
    public void toApp(Message message)
    throws DoNotSend;

    /// Notification of admin message being received from target
    public void fromAdmin(Message message)
    throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon;

    /// Notification of app message being received from target
    public void fromApp(Message message)
    throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType;

}
