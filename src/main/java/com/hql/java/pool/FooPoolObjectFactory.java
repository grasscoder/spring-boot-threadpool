package com.hql.java.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Random;

public class FooPoolObjectFactory extends BasePooledObjectFactory<Foo> {
    @Override
    public Foo create() throws Exception {
        Random random = new Random();
        return new Foo("init - username");
    }

    @Override
    public PooledObject<Foo> wrap(Foo foo) {
        return new DefaultPooledObject<>(foo);
    }
}
