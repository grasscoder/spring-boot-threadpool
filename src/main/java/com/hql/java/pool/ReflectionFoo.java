package com.hql.java.pool;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionFoo {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.hql.java.pool.Foo");
        Constructor constructor = c.getConstructor();
        Foo foo = (Foo)constructor.newInstance();
        //Method[] methods = c.getDeclaredMethods();
        Field f = c.getDeclaredField("password");
        f.setAccessible(true);
        System.out.println(f.get(foo));
        f.set(foo,"20");
        System.out.println(f.get(foo));
    }
}
