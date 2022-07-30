package org.springsalad.dsa.questions.parisseen;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionParamTest {

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String[] opInput, int[] argInput, Integer [] expected) {

        // UtilClassLevelTestHelper.runOneCase(String[] opInput, int[] argInput, Integer [] expected);


        String integer_array = "[null, 1, -1, null, null]";
        Gson gson = new Gson();
        Integer [] e  = gson.fromJson(integer_array, Integer[].class);

        System.out.println("show array of integer");
        for (Integer item : e) {
            System.out.println(item);
        }

        Integer [] act = new Integer[] {0, 1, -1 , null, null};
        Integer [] act2 = new Integer[] {null, 1, -1 , null, null};

        //assertArrayEquals(e, act );
        assertArrayEquals(e, act2 );


        int [][] array2d = new int[][] { {1, 2}, {2, 3}};
        String array2dser = gson.toJson(array2d);

        System.out.println(array2dser);

        ArrayList<int []> list = new ArrayList<>();
        list.add(new int[] {1});
        list.add(new int[] {2, 3});

        String arrayListSer = gson.toJson(list);

        printArrayListOfIntegerArray("orig list", list);

        System.out.println("list: "  + arrayListSer);

        Type t = new TypeToken<ArrayList<int[]>>(){}.getType();

        ArrayList<int[]> dserlist = gson.<ArrayList<int[]>>fromJson(arrayListSer, t);

        printArrayListOfIntegerArray("d ser  list", dserlist);




        for (int i = 0; i < opInput.length; i ++) {
            System.out.print("this is op:" + i + "::: ");
            System.out.print(" op - " + opInput[i]);
            System.out.print(", arg - " + argInput[i]);
            System.out.print(", expect - " + expected[i]);
            System.out.println();
        }
        //assertEquals(expected, input);
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(new String[]{"op1", "op2" }, new int[] {1, 2}, new Integer[] { null, 2})
        );
    }

    public static void printArrayListOfIntegerArray(String desc, ArrayList<int[]> list) {

        System.out.println("show array list of integer array: " + desc);

        for (int[] a : list) {
            System.out.print('[');
            for (Integer i : a) {
                System.out.print(i);
                System.out.print(", ");

            }
            System.out.println(']');
        }
    }
}
