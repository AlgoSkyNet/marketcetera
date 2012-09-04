package org.marketcetera.core.module;

import java.util.Hashtable;
import java.util.Map;
/* $License$ */
/**
 * A mock configuration provider for testing. This class may be used
 * when unit testing module implementations that export MXBean attributes.
 * 
 * @version $Id: MockConfigProvider.java 82330 2012-04-10 16:29:13Z colin $
 * @since 1.5.0
 */
public class MockConfigProvider implements ModuleConfigurationProvider {

    @Override
    public String getDefaultFor(ModuleURN inURN, String inAttribute)
            throws ModuleException {
        return mDefaults.get(getKey(inURN, inAttribute));
    }

    @Override
    public void refresh() throws ModuleException {
    }

    /**
     * Adds a default attribute value.
     *
     * @param inURN       the URN.
     * @param inAttribute the attribute name.
     * @param inValue     the attribute value.
     */
    public void addDefault(ModuleURN inURN,
                           String inAttribute, String inValue) {
        mDefaults.put(getKey(inURN, inAttribute), inValue);
    }

    /**
     * Removes the default attribute value.
     *
     * @param inURN the URN.
     * @param inAttribute the attribute value.
     *
     * @return the old attribute value, if found.
     */
    public String removeDefault(ModuleURN inURN, String inAttribute) {
        return mDefaults.remove(getKey(inURN, inAttribute));
    }

    /**
     * Removes all the default values.
     */
    public void clear() {
        mDefaults.clear();
    }

    private static String getKey(ModuleURN inURN, String inAttribute) {
        return inURN.getValue() + ';' + inAttribute;
    }

    private final Map<String, String> mDefaults = new Hashtable<String, String>();
}
