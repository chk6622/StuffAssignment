package com.stuff.exercise;

public interface IAssignment {
    /**
     * Get the sum of numbers
     * @param numbers a group of number for example "" or "1,2,3"
     * @return the sum of the numbers. if numbers is null or empty, it returns 0
     * */
    default int Add(String numbers){
        int rValue = 0;  //Return value default 0

        if(numbers != null && numbers.length() > 0){
            int[] numberArray = GetNumberFromString(numbers);  //Parse the string and get all numbers
            if(numberArray != null && numberArray.length > 0){
                //Get the sum of all elements of the array
                for (int val: numberArray) {
                    rValue += val;
                }
            }
        }

        return rValue;
    }

    /**
     * Parse the string and get all numbers.
     * @param numbers a group of number for example "" or "1,2,3".
     * @return a array each element in the array is a number in the string.
     * */
    int[] GetNumberFromString(String numbers);
}
