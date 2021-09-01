package com.example.baseic.metthodHandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        Son son = new Son();
        son.thinking();
        
        MethodHandles.Lookup lookup = MethodHandlesUtil.lookup(Father.class);
        MethodType mt = MethodType.methodType(void.class);
        MethodHandle thinking = lookup.findSpecial(Father.class, "thinking", mt, Father.class);
        thinking.invoke(son);

        MethodHandles.Lookup lookup2 = MethodHandlesUtil.lookup(GrandFather.class);
        MethodHandle thinking2 = lookup2.findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
        thinking2.invoke(son);

        MethodHandle specialMethodHandle = MethodHandlesUtil.getSpecialMethodHandle(
                GrandFather.class.getDeclaredMethod("thinking"));
        specialMethodHandle.invoke(son);

        //反射调用
        Method hello1 = Father.class.getDeclaredMethod("hello");
        hello1.setAccessible(true);
        hello1.invoke(son);

        //调用父类中的私有方法,因为子类没法重写父类的私有方法,所以调用结果跟反射一样.
        MethodHandles.Lookup lookup1 = MethodHandlesUtil.lookup(Father.class);
        MethodHandle methodHandle = lookup1.unreflectSpecial(hello1, Father.class);
        methodHandle.invoke(son);

    }
}
