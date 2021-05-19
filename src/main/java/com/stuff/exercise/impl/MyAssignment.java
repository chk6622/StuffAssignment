package com.stuff.exercise.impl;

import com.stuff.exercise.IAssignment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MyAssignment implements IAssignment{
    @Override
    public int[] GetNumberFromString(String numbers) {
        List<Integer> rArray = new LinkedList();

        if(numbers != null && numbers.length() > 0){
            LinkedList<Character> charQueue = new LinkedList();
            StringBuffer buf = new StringBuffer();

            char[] chars = numbers.toCharArray();  //convert string to char array
            for(char c : chars){  //add all characters to the queue
                charQueue.add(c);
            }

            //get number from characters
            while(!charQueue.isEmpty()){
                char curChar = charQueue.removeFirst();

                if(curChar >= '0'&& curChar <= '9'){
                    buf.append(curChar);  //add number characters to the buffer
                }
                else if(buf.length() > 0){ //if the current character is not a number character then add the number string in the buffer to the list
                    int value = ConvertStringToIntValue(buf.toString());
                    rArray.add(value);
                    buf.delete(0,buf.length());  //clear buf
                }
                if((curChar == '+'||curChar == '-')){  //if the current character is '+' or '-' and the next character is number then add the current character to the buffer
                    Character nextChar = charQueue.peek();
                    if(nextChar != null && nextChar >= '0' && nextChar <= '9'){
                        buf.append(curChar);
                    }
                }
            }
            if(buf.length()>0){ // add the last number to the list
                int value = ConvertStringToIntValue(buf.toString());
                rArray.add(value);
            }
            else{  // if the last character is not number then throw an exception
                throw new IllegalArgumentException("input data is invalidate!");
            }
        }

        return rArray.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Convert number type from String to int
     * @param number this is the number that is String type
     * @return it returns the number that is String type, default value is 0
     * */
    private int ConvertStringToIntValue(String number) {
        int value=0;
        if(number.length() > 0){
            try{
                value = Integer.parseInt(number);
            }
            catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        return value;
    }
}
