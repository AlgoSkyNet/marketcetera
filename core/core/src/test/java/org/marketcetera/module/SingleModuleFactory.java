package org.marketcetera.module;

import org.marketcetera.core.attributes.ClassVersion;

/* $License$ */
/**
 * A module factory that supports single instance only and does not require
 * parameters to instantiate the instance.
 *
 * @author anshul@marketcetera.com
 */
@ClassVersion("$Id: SingleModuleFactory.java 82330 2012-04-10 16:29:13Z colin $")
public class SingleModuleFactory extends ModuleFactory {
    @Override
    public SingletonModule create(Object... parameters)
            throws ModuleCreationException {
        return new SingletonModule(INSTANCE_URN);
    }
    public SingleModuleFactory() {
        super(PROVIDER_URN, TestMessages.SINGLE_1_PROVIDER, false, false);
    }

    static final ModuleURN PROVIDER_URN = new ModuleURN("metc:test:single1");
    public static final ModuleURN INSTANCE_URN = new ModuleURN(PROVIDER_URN,"single");
}
