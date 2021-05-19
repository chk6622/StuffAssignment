package com.stuff.exercise.impl;

import com.stuff.exercise.IAssignment;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class MyAssignment implements IAssignment{
    @Override
    public int[] GetNumberFromString(String numbers) {
        int[] rArray = null;

        if(numbers!=null&&numbers.length()>0){
            LinkedList<String> numList = new LinkedList();
            LinkedList<Character> charQueue = new LinkedList();
            StringBuffer buf = new StringBuffer();

            char[] chars = numbers.toCharArray();  //convert string to char array
            for(char c : chars){  //add all characters to the queue
                charQueue.add(c);
            }

            //get number from characters
            while(!charQueue.isEmpty()){
                char curChar = charQueue.removeFirst();

                if(curChar>='0'&& curChar<='9'){
                    buf.append(curChar);  //add number characters to the buffer
                }
                else if(buf.length()>0){ //if the current character is not a number character then add the number string in the buffer to the list
                    numList.add(buf.toString());
                    buf.delete(0,buf.length());
                }
                if((curChar=='+'||curChar=='-')){  //if the current character is '+' or '-' and the next character is number then add the current character to the buffer
                    Character nextChar = charQueue.peek();
                    if(nextChar!=null&&nextChar>='0'&&nextChar<='9'){
                        buf.append(curChar);
                    }
                }
            }
            if(buf.length()>0){ // add the last number to the list
                numList.add(buf.toString());
            }
            else{  // if the last character is not number then throw an exception
                throw new IllegalArgumentException("input data is invalidate!");
            }

            //convert the type of number from string to int and add the number to the return int array
            int length = numList!=null?numList.size():0;
            if(length>0){
                rArray = new int[length];
                int index = 0;
                while(!numList.isEmpty()){

                    try{
                        rArray[index++] = Integer.parseInt(numList.removeFirst());
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
