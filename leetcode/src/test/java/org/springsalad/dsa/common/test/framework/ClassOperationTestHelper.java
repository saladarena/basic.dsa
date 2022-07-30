package org.springsalad.dsa.common.test.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ClassOperationTestHelper {

    public void runOneCase(String testName, String[] opInput, ArrayList<int[]> argInput, Integer [] expected) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        showTestCaseInfo(testName, opInput, argInput, expected);

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

        showActual(actual);

        // assertion
        assertArrayEquals(expected, actual);

    }



    private Integer exeOperation(Object o, String methodName, int[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m;
        Class [] params = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            params[i] = int.class;
        }

        m = o.getClass().getDeclaredMethod(methodName, params);

        Object [] varargs = convertIntArgs2Varargs(args);

        Integer ret = (Integer) m.invoke(o, varargs);

        return ret;
    }

    private Object constructObject(String clazz, int[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Constructor constructor = null;


        Class [] params = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            params[i] = int.class;
        }

        constructor = Class.forName(clazz).getConstructor(params);


        Object [] consargs = convertIntArgs2Varargs(args);
        Object o = constructor.newInstance(consargs);

        return o;
    }

    private Object[] convertIntArgs2Varargs(int[] args) {

        Object [] varargs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            varargs[i] = args[i];
        }
        return varargs;
    }

    private void showTestCaseInfo (String testName, String[] opInput, ArrayList<int[]> argInput, Integer [] expected){
        System.out.println("Test case : " + testName);
        printArray("Input ", opInput);

        List<Integer[]> inputArgs2 = argInput.stream().map( (int[] a) -> {
            Integer[] what = Arrays.stream( a ).boxed().toArray( Integer[]::new );
            return what;
        }).collect(Collectors.toList());

        printArrayListOfArray("Input args" , inputArgs2);

        printArray("Expected ", expected);
    }

    private void showActual(Integer[] actual) {
        printArray("Actual ", actual);

    }

    public static <T> void  printArrayListOfArray(String desc, List<T []> list) {

        System.out.println("" + desc + ":");
        System.out.print('[');

        for (T[] tmp : list) {
            System.out.print('[');
            for (T item: tmp) {
                System.out.print(item);
                System.out.print(",");
            }
            System.out.print("],");

        }

        System.out.println(']');
    }

    public static <T> void  printArray(String desc, T[] array) {

        System.out.println("" + desc);
        System.out.print('[');

        for (T tmp : array) {
            System.out.print(tmp);
            System.out.print(", ");
        }

        System.out.println(']');
    }


}
