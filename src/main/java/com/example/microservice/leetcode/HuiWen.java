package com.example.microservice.leetcode;


import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class HuiWen {
    public static void main(String[] args) {
        String input = "abbabbaabb";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            stringBuilder.append("#");
            stringBuilder.append(input.charAt(i));
        }

        stringBuilder.append("#");

        System.out.println(stringBuilder);

        input = stringBuilder.toString();

        for (int i = 0; i < input.length(); i++) {
            String myString = getLongestString(input, i).replaceAll("#","");
            if(StringUtils.isNotEmpty(myString)){
                System.out.println(myString);
            }
        }

        getHuiwenString(input.replaceAll("#",""));
    }

    private static String getLongestString(String input, int mid) {
        int len = input.length();
        StringBuilder stringBuilderLeft = new StringBuilder();
        StringBuilder stringBuilderRight = new StringBuilder();
        stringBuilderRight.append(input.charAt(mid));
        for (int i = mid, j = mid; i > 0 && j<len-1; i--, j++) {
            if (input.charAt(i - 1) != input.charAt(j + 1)) {
                return stringBuilderLeft.reverse().append(stringBuilderRight).toString();
            }

            stringBuilderLeft.append(input.charAt(i - 1));
            stringBuilderRight.append(input.charAt(j + 1));
        }

        return stringBuilderLeft.reverse().append(stringBuilderRight).toString();
    }

    private static void getHuiwenString(String input){
        System.out.println("-------------------------------------------------------------------");
        for(int i=0; i<input.length();i++){
            StringBuilder stringBuilder =new StringBuilder();
            stringBuilder.append(input.charAt(i));
            if(stringBuilder.toString().equals(stringBuilder.reverse().toString())){
                System.out.println(stringBuilder.toString());
            }
            for(int j=i+1; j<input.length(); j++){
                stringBuilder.append(input.charAt(j));
                if(stringBuilder.toString().equals(stringBuilder.reverse().toString())){
                    System.out.println(stringBuilder.toString());
                }
            }
        }
    }
}