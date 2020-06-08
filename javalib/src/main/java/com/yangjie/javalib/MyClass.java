package com.yangjie.javalib;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {

        String[] splitString = "fewfwfwfwfwefwf1111".split("@");
        String spChapterId = "222";
        if(splitString.length >= 2){
            spChapterId = splitString[1];
        }
        System.out.println(Arrays.toString(splitString));
        System.out.println(spChapterId);
    }


}
