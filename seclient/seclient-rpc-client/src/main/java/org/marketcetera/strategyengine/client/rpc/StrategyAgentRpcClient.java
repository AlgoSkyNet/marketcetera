package org.marketcetera.strategyengine.client.rpc;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.concurrent.GuardedBy;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.marketcetera.core.Util;
import org.marketcetera.core.Version;
import org.marketcetera.core.VersionInfo;
import org.marketcetera.module.ModuleInfo;
import org.marketcetera.module.ModuleURN;
import org.marketcetera.rpc.base.BaseRpc;
import org.marketcetera.rpc.client.AbstractRpcClient;
import org.marketcetera.seclient.rpc.SEClientRpc;
import org.marketcetera.seclient.rpc.SEClientServiceRpcGrpc;
import org.marketcetera.seclient.rpc.SEClientServiceRpcGrpc.SEClientServiceRpcBlockingStub;
import org.marketcetera.seclient.rpc.SEClientServiceRpcGrpc.SEClientServiceRpcStub;
import org.marketcetera.strategyengine.client.ConnectionException;
import org.marketcetera.strategyengine.client.ConnectionStatusListener;
import org.marketcetera.strategyengine.client.CreateStrategyParameters;
import org.marketcetera.strategyengine.client.DataReceiver;
import org.marketcetera.strategyengine.client.SAClient;
import org.marketcetera.strategyengine.client.XmlValue;
import org.marketcetera.util.except.ExceptUtils;
import org.marketcetera.util.ws.ContextClassProvider;
import org.marketcetera.util.ws.tags.AppId;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.grpc.Channel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

/* $License$ */

/**
 * Provides an RPC {@link SEClient} interface.
 *
 * @author <a href="mailto:colin@marketcetera.com">Colin DuPlantis</a>
 * @version $Id$
 * @since $Release$
 */
public class StrategyAgentRpcClient
        extends AbstractRpcClient<SEClientServiceRpcBlockingStub,SEClientServiceRpcStub,StrategyAgentRpcClientParameters>
        implements SAClient<StrategyAgentRpcClientParameters>
{
    /**
     * Create a new StrategyAgentRpcClient instance.
     *
     * @param inParameters a <code>StrategyAgentRpcClientParameters</code> value
     */
    StrategyAgentRpcClient(StrategyAgentRpcClientParameters inParameters)
    {
        super(inParameters);
        contextClassProvider = inParameters.getContextClassProvider();
        parameters = inParameters;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#start()
     */
    @Override
    public void start()
            throws Exception
    {
        synchronized(contextLock) {
            context = JAXBContext.newInstance(contextClassProvider==null?new Class<?>[0]:contextClassProvider.getContextClasses());
            marshaller = context.createMarshaller();
            unmarshaller = context.createUnmarshaller();
        }
        super.start();
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#getProviders()
     */
    @Override
    public List<ModuleURN> getProviders()
            throws ConnectionException
    {
        return executeCall(new Callable<List<ModuleURN>>(){
            @Override
            public List<ModuleURN> call()
                    throws Exception
            {
                SEClientRpc.ProvidersResponse response = getBlockingStub().getProviders(SEClientRpc.ProvidersRequest.newBuilder().setSessionId(getSessionId().getValue()).build());
                List<ModuleURN> providers = Lists.newArrayList();
                for(SEClientRpc.ModuleURN provider : response.getProviderList()) {
                    providers.add(new ModuleURN(provider.getValue()));
                }
                return providers;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#getInstances(org.marketcetera.module.ModuleURN)
     */
    @Override
    public List<ModuleURN> getInstances(ModuleURN inProviderURN)
            throws ConnectionException
    {
        return executeCall(new Callable<List<ModuleURN>>(){
            @Override
            public List<ModuleURN> call()
                    throws Exception
            {
                SEClientRpc.InstancesResponse response = getBlockingStub().getInstances(SEClientRpc.InstancesRequest.newBuilder().setSessionId(getSessionId().getValue())
                                                                                        .setProvider(SEClientRpc.ModuleURN.newBuilder().setValue(inProviderURN.getValue())).build());
                List<ModuleURN> instances = Lists.newArrayList();
                for(SEClientRpc.ModuleURN instance : response.getInstanceList()) {
                    instances.add(new ModuleURN(instance.getValue()));
                }
                return instances;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#getModuleInfo(org.marketcetera.module.ModuleURN)
     */
    @Override
    public ModuleInfo getModuleInfo(ModuleURN inURN)
            throws ConnectionException
    {
        return executeCall(new Callable<ModuleInfo>(){
            @Override
            public ModuleInfo call()
                    throws Exception
            {
                SEClientRpc.ModuleInfoResponse response = getBlockingStub().getModuleInfo(SEClientRpc.ModuleInfoRequest.newBuilder().setSessionId(getSessionId().getValue())
                                                                                          .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue())).build());
                ModuleInfo info = null;
                if(response.hasInfo()) {
                    info = unmarshal(response.getInfo().getPayload());
                }
                return info;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#start(org.marketcetera.module.ModuleURN)
     */
    @Override
    public void start(final ModuleURN inURN)
            throws ConnectionException
    {
        executeCall(new Callable<Void>(){
            @Override
            public Void call()
                    throws Exception
            {
                getBlockingStub().start(SEClientRpc.StartRequest.newBuilder().setSessionId(getSessionId().getValue())
                                        .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue())).build());
                return null;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#stop(org.marketcetera.module.ModuleURN)
     */
    @Override
    public void stop(ModuleURN inURN)
            throws ConnectionException
    {
        executeCall(new Callable<Void>(){
            @Override
            public Void call()
                    throws Exception
            {
                getBlockingStub().stop(SEClientRpc.StopRequest.newBuilder().setSessionId(getSessionId().getValue())
                                       .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue())).build());
                return null;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#delete(org.marketcetera.module.ModuleURN)
     */
    @Override
    public void delete(ModuleURN inURN)
            throws ConnectionException
    {
        executeCall(new Callable<Void>(){
            @Override
            public Void call()
                    throws Exception
            {
                getBlockingStub().delete(SEClientRpc.DeleteRequest.newBuilder().setSessionId(getSessionId().getValue())
                                         .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue())).build());
                return null;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#getProperties(org.marketcetera.module.ModuleURN)
     */
    @Override
    public Map<String,Object> getProperties(ModuleURN inURN)
            throws ConnectionException
    {
        return executeCall(new Callable<Map<String,Object>>(){
            @Override
            public Map<String,Object> call()
                    throws Exception
            {
                SEClientRpc.GetPropertiesResponse response = getBlockingStub().getProperties(SEClientRpc.GetPropertiesRequest.newBuilder().setSessionId(getSessionId().getValue())
                                                                                             .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue())).build());
                Map<String,Object> properties = Maps.newHashMap();
                for(SEClientRpc.Entry entry : response.getProperties().getEntryList()) {
                    String key = entry.getKey();
                    Object value = ((XmlValue)unmarshal(entry.getValue())).getValue();
                    properties.put(key,
                                   value);
                }
                return properties;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#setProperties(org.marketcetera.module.ModuleURN, java.util.Map)
     */
    @Override
    public Map<String,Object> setProperties(ModuleURN inURN,
                                            Map<String,Object> inProperties)
            throws ConnectionException
    {
        return executeCall(new Callable<Map<String,Object>>(){
            @Override
            public Map<String,Object> call()
                    throws Exception
            {
                SEClientRpc.Properties.Builder propertiesBuilder = SEClientRpc.Properties.newBuilder();
                try {
                    for(Map.Entry<String,Object> entry : inProperties.entrySet()) {
                        SEClientRpc.Entry.Builder entryBuilder = SEClientRpc.Entry.newBuilder();
                        entryBuilder.setKey(entry.getKey());
                        // note that this assumes that all values are marshallable
                        entryBuilder.setValue(marshal(new XmlValue(entry.getValue())));
                        propertiesBuilder.addEntry(entryBuilder.build());
                    }
                } catch (JAXBException e) {
                    throw new StatusRuntimeException(Status.ABORTED.withCause(e).withDescription(ExceptionUtils.getRootCauseMessage(e)));
                }
                SEClientRpc.SetPropertiesResponse response = getBlockingStub().setProperties(SEClientRpc.SetPropertiesRequest.newBuilder().setSessionId(getSessionId().getValue())
                                                                                         .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue()))
                                                                                         .setProperties(propertiesBuilder.build()).build());
                Map<String,Object> properties = Maps.newHashMap();
                for(SEClientRpc.Entry entry : response.getProperties().getEntryList()) {
                    String key = entry.getKey();
                    Object value = ((XmlValue)unmarshal(entry.getValue())).getValue();
                    properties.put(key,
                                   value);
                }
                return properties;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#createStrategy(org.marketcetera.SEClient.CreateStrategyParameters)
     */
    @Override
    public ModuleURN createStrategy(CreateStrategyParameters inParameters)
            throws ConnectionException
    {
        return executeCall(new Callable<ModuleURN>(){
            @Override
            public ModuleURN call()
                    throws Exception
            {
                SEClientRpc.CreateStrategyResponse response = getBlockingStub().createStrategy(SEClientRpc.CreateStrategyRequest.newBuilder().setSessionId(getSessionId().getValue())
                                                                                               .setCreateStrategyParameters(SEClientRpc.CreateStrategyParameters.newBuilder().setPayload(marshal(inParameters)).build()).build());
                ModuleURN instance = new ModuleURN(response.getInstance().getValue());
                return instance;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#getStrategyCreateParms(org.marketcetera.module.ModuleURN)
     */
    @Override
    public CreateStrategyParameters getStrategyCreateParms(ModuleURN inURN)
            throws ConnectionException
    {
        return executeCall(new Callable<CreateStrategyParameters>(){
            @Override
            public CreateStrategyParameters call()
                    throws Exception
            {
                SEClientRpc.StrategyCreateParmsResponse response = getBlockingStub().getStrategyCreateParms(SEClientRpc.StrategyCreateParmsRequest.newBuilder().setSessionId(getSessionId().getValue())
                                                                                                            .setInstance(SEClientRpc.ModuleURN.newBuilder().setValue(inURN.getValue())).build());
                CreateStrategyParameters params = unmarshal(response.getCreateStrategyParameters().getPayload());
                return params;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#sendData(java.lang.Object)
     */
    @Override
    public void sendData(Object inData)
            throws ConnectionException
    {
        executeCall(new Callable<Void>(){
            @Override
            public Void call()
                    throws Exception
            {
                // note that inData must be JAXB marshallable
                getBlockingStub().sendData(SEClientRpc.SendDataRequest.newBuilder().setSessionId(getSessionId().getValue())
                                           .setPayload(marshal(new XmlValue(inData))).build());
                return null;
            }
        });
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#addDataReceiver(org.marketcetera.SEClient.DataReceiver)
     */
    @Override
    public void addDataReceiver(DataReceiver inReceiver)
    {
        if(inReceiver == null) {
            throw new NullPointerException();
        }
        synchronized (receivers) {
            receivers.addFirst(inReceiver);
        }
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#removeDataReciever(org.marketcetera.SEClient.DataReceiver)
     */
    @Override
    public void removeDataReciever(DataReceiver inReceiver)
    {
        if(inReceiver == null) {
            throw new NullPointerException();
        }
        synchronized (receivers) {
            receivers.removeFirstOccurrence(inReceiver);
        }
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#addConnectionStatusListener(org.marketcetera.SEClient.ConnectionStatusListener)
     */
    @Override
    public void addConnectionStatusListener(ConnectionStatusListener inListener)
    {
        if(inListener == null) {
            throw new NullPointerException();
        }
        synchronized (listeners) {
            listeners.addFirst(inListener);
        }
        inListener.receiveConnectionStatus(isRunning());
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#removeConnectionStatusListener(org.marketcetera.SEClient.ConnectionStatusListener)
     */
    @Override
    public void removeConnectionStatusListener(ConnectionStatusListener inListener)
    {
        if(inListener == null) {
            throw new NullPointerException();
        }
        synchronized (listeners) {
            listeners.removeFirstOccurrence(inListener);
        }
    }
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#getParameters()
     */
    @Override
    public StrategyAgentRpcClientParameters getParameters()
    {
        return parameters;
    }
    // TODO received data - this is currently sent via JMS and DataEmitter/RemoteDataEmitter, prob want to switch to XML
    /* (non-Javadoc)
     * @see org.marketcetera.SEClient.SEClient#close()
     */
    @Override
    public void close()
    {
        try {
            stop();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#onStatusChange(boolean)
     */
    @Override
    protected void onStatusChange(boolean inIsConnected)
    {
        synchronized(listeners) {
            for(ConnectionStatusListener listener: listeners) {
                try {
                    listener.receiveConnectionStatus(inIsConnected);
                } catch (Exception e) {
                    Messages.LOG_ERROR_RECEIVE_CONNECT_STATUS.warn(this,
                                                                   e,
                                                                   inIsConnected);
                    ExceptUtils.interrupt(e);
                }
            }
        }
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#getBlockingStub(io.grpc.Channel)
     */
    @Override
    protected SEClientServiceRpcBlockingStub getBlockingStub(Channel inChannel)
    {
        return SEClientServiceRpcGrpc.newBlockingStub(inChannel);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#getAsyncStub(io.grpc.Channel)
     */
    @Override
    protected SEClientServiceRpcStub getAsyncStub(Channel inChannel)
    {
        return SEClientServiceRpcGrpc.newStub(inChannel);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#executeLogin(org.marketcetera.rpc.base.BaseRpc.LoginRequest)
     */
    @Override
    protected BaseRpc.LoginResponse executeLogin(BaseRpc.LoginRequest inRequest)
    {
        return getBlockingStub().login(inRequest);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#executeLogout(org.marketcetera.rpc.base.BaseRpc.LogoutRequest)
     */
    @Override
    protected BaseRpc.LogoutResponse executeLogout(BaseRpc.LogoutRequest inRequest)
    {
        return getBlockingStub().logout(inRequest);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#executeHeartbeat(org.marketcetera.rpc.base.BaseRpc.HeartbeatRequest, io.grpc.stub.StreamObserver)
     */
    @Override
    protected void executeHeartbeat(BaseRpc.HeartbeatRequest inRequest,
                                    StreamObserver<BaseRpc.HeartbeatResponse> inObserver)
    {
        getAsyncStub().heartbeat(inRequest,
                                 inObserver);
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#getAppId()
     */
    @Override
    protected AppId getAppId()
    {
        return APP_ID;
    }
    /* (non-Javadoc)
     * @see org.marketcetera.rpc.client.AbstractRpcClient#getVersionInfo()
     */
    @Override
    protected VersionInfo getVersionInfo()
    {
        return APP_ID_VERSION;
    }
    /**
     * Marshals the given object to an XML stream.
     *
     * @param inObject an <code>Object</code> value
     * @return a <code>String</code> value
     * @throws JAXBException if an error occurs marshalling the data
     */
    private String marshal(Object inObject)
            throws JAXBException
    {
        StringWriter output = new StringWriter();
        synchronized(contextLock) {
            marshaller.marshal(inObject,
                               output);
        }
        return output.toString();
    }
    /**
     * Unmarshals an object from the given XML stream.
     *
     * @param inData a <code>String</code> value
     * @return a <code>Clazz</code> value
     * @throws JAXBException if an error occurs unmarshalling the data
     */
    @SuppressWarnings("unchecked")
    private <Clazz> Clazz unmarshal(String inData)
            throws JAXBException
    {
        synchronized(contextLock) {
            return (Clazz)unmarshaller.unmarshal(new StringReader(inData));
        }
    }
    /**
     * guards access to JAXB context objects
     */
    private final Object contextLock = new Object();
    /**
     * context used to serialize and unserialize messages as necessary
     */
    @GuardedBy("contextLock")
    private JAXBContext context;
    /**
     * marshals messages
     */
    @GuardedBy("contextLock")
    private Marshaller marshaller;
    /**
     * unmarshals messages
     */
    @GuardedBy("contextLock")
    private Unmarshaller unmarshaller;
    /**
     * parameters used to create the client
     */
    private final StrategyAgentRpcClientParameters parameters;
    /**
     * receivers of remove data
     */
    private final Deque<DataReceiver> receivers = new LinkedList<DataReceiver>();
    /**
     * connection status listeners collection
     */
    private final Deque<ConnectionStatusListener> listeners = new LinkedList<ConnectionStatusListener>();
    /**
     * provides context classes for marshalling/unmarshalling, may be <code>null</code>
     */
    private ContextClassProvider contextClassProvider;
    /**
     * The client's application ID: the application name.
     */
    private static final String APP_ID_NAME = StrategyAgentRpcClient.class.getSimpleName();
    /**
     * The client's application ID: the version.
     */
    private static final VersionInfo APP_ID_VERSION = new VersionInfo(Version.pomversion);
    /**
     * The client's application ID: the ID.
     */
    private static final AppId APP_ID = Util.getAppId(APP_ID_NAME,APP_ID_VERSION.getVersionInfo());
}