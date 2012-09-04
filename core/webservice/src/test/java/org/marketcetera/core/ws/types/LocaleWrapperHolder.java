package org.marketcetera.core.ws.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.lang.ArrayUtils;
import org.marketcetera.core.ws.wrappers.LocaleWrapper;

/**
 * @author tlerios@marketcetera.com
 * @since 1.0.0
 * @version $Id: LocaleWrapperHolder.java 82324 2012-04-09 20:56:08Z colin $
 */

/* $License$ */

public class LocaleWrapperHolder
    extends GenericHolder<LocaleWrapper>
{
    private LocaleWrapper[] mArray;


    @SuppressWarnings("unused")
    private LocaleWrapperHolder() {}

    public LocaleWrapperHolder
        (LocaleWrapper item,
         LocaleWrapper[] array,
         Collection<LocaleWrapper> collection,
         List<LocaleWrapper> list,
         LinkedList<LocaleWrapper> linkedList,
         Set<LocaleWrapper> set,
         HashSet<LocaleWrapper> hashSet,
         TreeSet<LocaleWrapper> treeSet,
         Map<LocaleWrapper,LocaleWrapper> map,
         HashMap<LocaleWrapper,LocaleWrapper> hashMap,
         TreeMap<LocaleWrapper,LocaleWrapper> treeMap)
    {
        super(item,collection,list,linkedList,
              set,hashSet,treeSet,map,hashMap,treeMap);
        setArray(array);
    }


    public void setArray
        (LocaleWrapper[] array)
    {
        mArray=array;
    }

    public LocaleWrapper[] getArray()
    {
        return mArray;
    }


    @Override
    public int hashCode()
    {
        return (super.hashCode()+
                ArrayUtils.hashCode(getArray()));
    }

    @Override
    public boolean equals
        (Object other)
    {
        if (this==other) {
            return true;
        }
        if ((other==null) || !getClass().equals(other.getClass())) {
            return false;
        }
        LocaleWrapperHolder o=(LocaleWrapperHolder)other;
        return (super.equals(o) &&
                ArrayUtils.isEquals(getArray(),o.getArray()));
    }
}