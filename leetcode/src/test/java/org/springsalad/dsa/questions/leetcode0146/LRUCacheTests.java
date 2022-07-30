package org.springsalad.dsa.questions.leetcode0146;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springsalad.dsa.common.test.framework.ClassOperationTestHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LRUCacheTests<T> {


    @ParameterizedTest
    @MethodSource("provideTestData")
    void LRUCacheDataDrivenTests(String testCaseName, String[] opInput, ArrayList<int[]> argInput, Integer [] expected) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ClassOperationTestHelper helper = new ClassOperationTestHelper();
        helper.runOneCase(testCaseName, opInput, argInput, expected);
    }

    private static Stream<Arguments> provideTestData() {
        String packageName = LRUCacheTests.class.getPackage().getName();


        String opInputString = "[\"LRUCache\", \"put\", \"put\", \"get\", \"put\", \"get\", \"put\", \"get\", \"get\", \"get\"]";
        String inputArgsString = "[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]";
        String expectedString = "[null, null, null, 1, null, -1, null, -1, 3, 4]";

        Gson gson = new Gson();
        String[] opInput = gson.fromJson(opInputString, String[].class);
        opInput[0] = packageName + "." + opInput[0];

        Type t = new TypeToken<ArrayList<int[]>>(){}.getType();
        ArrayList<int[]> inputArgs = gson.<ArrayList<int[]>>fromJson(inputArgsString, t);



        Integer[] expected = gson.fromJson(expectedString, Integer[].class);

        return Stream.of(
                Arguments.of("Test 1", opInput, inputArgs, expected)
        );
    }

    public static <T> void  printArrayList(String desc, ArrayList<T> list) {

        System.out.println("show array list : " + desc);
        System.out.print('[');

        for (T tmp : list) {
                System.out.print(tmp);
                System.out.print(", ");
        }

        System.out.println(']');
    }

    public static <T> void  printArray(String desc, T[] array) {

        System.out.println("show array  : " + desc);
        System.out.print('[');

        for (T tmp : array) {
            System.out.print(tmp);
            System.out.print(", ");
        }

        System.out.println(']');
    }

}
