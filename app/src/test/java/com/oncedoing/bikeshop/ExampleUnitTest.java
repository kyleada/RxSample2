package com.oncedoing.bikeshop;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        System.out.print(convertMoney(389100));
        assertEquals(4, 2 + 2);
    }

    public static String convertMoney(int money){
        return String.format("1.2f%", money/100.0f);
    }
}