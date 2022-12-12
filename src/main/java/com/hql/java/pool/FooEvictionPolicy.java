package com.hql.java.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.EvictionConfig;
import org.apache.commons.pool2.impl.EvictionPolicy;

public class FooEvictionPolicy implements EvictionPolicy {
    @Override
    public boolean evict(EvictionConfig evictionConfig, PooledObject pooledObject, int i) {
        return true;
    }
}
