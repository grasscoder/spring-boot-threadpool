package com.hql.java.pool;

import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Main {
    private final GenericObjectPool<Foo> fooGenericObjectPool = fooGenericObjectPool();

    public GenericObjectPool<Foo> fooGenericObjectPool() {
        GenericObjectPoolConfig<Foo> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setEvictionPolicy(new FooEvictionPolicy());
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setJmxEnabled(false);
        poolConfig.setMaxWaitMillis(1000 * 10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60 * 1000);
        poolConfig.setMinEvictableIdleTimeMillis(20 * 1000);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setMaxTotal(3);
        // 设置抛弃策略
        AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        return new GenericObjectPool<>(new FooPoolObjectFactory(), poolConfig, abandonedConfig);
    }

    public Foo borrowFoo() {

        try {
            return fooGenericObjectPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void returnObject(Foo foo) {
        fooGenericObjectPool.returnObject(foo);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Foo foo = main.borrowFoo();
        main.returnObject(foo);
        System.out.println(foo.getUserName());
    }
}
