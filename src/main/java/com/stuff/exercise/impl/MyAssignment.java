package com.stuff.exercise.impl;

import com.stuff.exercise.IAssignment;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class MyAssignment implements IAssignment{

    private int maxNumber = 1000;
    private int minNumber = 0;

    @Override
    public int[] GetNumberFromString(String numbers) {
        List<Integer> rArray = new LinkedList();

        if(numbers != null && numbers.length() > 0){
            LinkedList<Character> charQueue = new LinkedList();
            LinkedList<Integer> negativeNumbers = new LinkedList();
            StringBuffer buf = new StringBuffer();

            char[] chars = numbers.toCharArray();  //convert string to char array
            for(char c : chars){  //add all characters to the queue
                charQueue.add(c);
            }

            //get number from characters
            while(!charQueue.isEmpty()){
                char curChar = charQueue.removeFirst();

                if(curChar >= '0' && curChar <= '9'){
                    buf.append(curChar);                   //add number characters to the buffer
                }
                else if(buf.length() > 0){                //if the current character is not a number character then add the number string in the buffer to the list
                    int value = ConvertStringToIntValue(buf.toString());
                    if(value < this.minNumber){
                        negativeNumbers.add(value);
                    }
                    rArray.add(value);
                    buf.delete(0,buf.length());  //clear buf
                }
                if((curChar == '+' || curChar == '-')){   //if the current character is '+' or '-' and the next character is number then add the current character to the buffer
                    Character nextChar = charQueue.peek();
                    if(nextChar !=null && nextChar >= '0' && nextChar <= '9'){
                        buf.append(curChar);
                    }
                }
            }
            if(buf.length() > 0){ // add the last number to the list
                int value = ConvertStringToIntValue(buf.toString());
                if(value < this.minNumber){
                    negativeNumbers.add(value);
                }
                rArray.add(value);
            }
            else{  // if the last character is not number then throw an exception
                throw new IllegalArgumentException("input data is invalidate!");
            }

            VerifyNegativeNumber(negativeNumbers);
        }

        return rArray.stream()
                .mapToInt(Integer::intValue)   //convert integer to int
                .filter(value -> value <= maxNumber)   //ignore the numbers bigger than 1000
                .toArray();
    }

    /**
     * Convert number type from String to int
     * @param number this is the number that is String type
     * @return it returns the number that is String type, default value is 0
     * */
    private final int ConvertStringToIntValue(String number) {
        int value = 0;
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

    /**
     * Verify if there is negative number. If there is negative number in the list, the method will throw IllegalArgumentException.
     * @param negativeNumbers negative number list
     * */
    private final void VerifyNegativeNumber(List<Integer> negativeNumbers) {
        int negativeSize = negativeNumbers.size();
        if(negativeSize == 1){
            throw new IllegalArgumentException("negatives not allowed!");
        }
        else if(negativeSize>1){
            StringBuffer msgBuf = new StringBuffer("negatives not allowed:");
            Iterator<Integer> iterator = negativeNumbers.iterator();
            while( iterator.hasNext() ){
                Integer negativeNumber = iterator.next();
                msgBuf.append(negativeNumber);
                if(iterator.hasNext()){
                    msgBuf.append(",");
                }
            }
            throw new IllegalArgumentException(msgBuf.toString());
        }
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        this.minNumber = minNumber;
    }
}
