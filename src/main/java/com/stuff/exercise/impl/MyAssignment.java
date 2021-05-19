package com.stuff.exercise.impl;

import com.stuff.exercise.IAssignment;
import org.springframework.stereotype.Component;

@Component
public class MyAssignment implements IAssignment{
    @Override
    public int[] GetNumberFromString(String numbers) {
        int[] rArray = null;

        if(numbers != null && numbers.length() > 0){
            String[] numArray = numbers.split(",");  //split string using ','

            if(numArray != null && numArray.length > 0){
                rArray = new int[numArray.length];
                for(int i = 0;i < numArray.length;i++){
                    try{
                        rArray[i] = Integer.parseInt(numArray[i]);
                    }
                    catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                }
            }
        }

        return rArray;
    }
}
