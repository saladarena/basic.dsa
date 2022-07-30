package org.springsalad.dsa.common.test.framework;

import java.lang.reflect.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ClassOperationTestHelper {

    public void runOneCase(String[] opInput, ArrayList<int[]> argInput, Integer [] expected) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        int len = opInput.length;
        Integer[] actual = new Integer[len];


        // first op is to new object
        // constructor may have zero, 1, 2 Integer

        String clzName = opInput[0];

        int [] consArg = argInput.get(0);
        Object SUTObject = constructObject(clzName, consArg);
        actual[0] = null;

        // exe op from second to end

        for (int i = 1; i < len; i++) {
            String methodName = opInput[i];
            int [] methodArgs = argInput.get(i);
            Integer ret = exeOperation(SUTObject, methodName, methodArgs);
            actual[i] = ret;

        }

        // assertion
        assertArrayEquals(expected, actual);

    }

    private Integer exeOperation(Object o, String methodName, int[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m;
        Class [] params = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            params[i] = Integer.class;
        }

        m = o.getClass().getDeclaredMethod(methodName, params);

        Integer ret = (Integer) m.invoke(o, args);

        return ret;
    }

    private Object constructObject(String clazz, int[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor c = Class.forName(clazz).getConstructor();
        Object o = c.newInstance();
        return o;

    }


}
