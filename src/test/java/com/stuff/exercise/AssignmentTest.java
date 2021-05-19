package com.stuff.exercise;

import com.stuff.MySpringBootApplication;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MySpringBootApplication.class)
public class AssignmentTest {
    @Autowired
    private IAssignment assignment;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnZeroFromEmptyString(){

        int result = 0;
        Assert.assertEquals(result, this.assignment.Add(null));  //Add(null) => 0

        Assert.assertEquals(result, this.assignment.Add(""));   //Add("") => 0

    }

    @Test
    public void shouldReturnNumberWhenOnlyOneNumber(){

        int result = 0;
        Assert.assertEquals(result, this.assignment.Add("0"));   //Add("0") => 0

        result = 100;
        Assert.assertEquals(result, this.assignment.Add("100"));   //Add("100") => 100


    }

    @Test
    public void shouldReturnSumOfTwoNumbers(){

        int result = 3;
        Assert.assertEquals(result, this.assignment.Add("1,2"));   //Add("1,2") => 3

        result = 450;
        Assert.assertEquals(result, this.assignment.Add("100,350"));   //Add(""100,350"") => 450

    }

    @Test
    public void shouldReturnSumOfUnknownAmountOfNumbers(){

        int result = 6;
        Assert.assertEquals(result, this.assignment.Add("1,2,3"));   //Add("1,2,3") => 6

        result = 21;
        Assert.assertEquals(result, this.assignment.Add("1,2,3,4,5,6"));   //Add("1,2,3,4,5,6") => 21

    }

    @Test
    public void shouldHandleNewLines(){

        int result = 6;
        Assert.assertEquals(result, this.assignment.Add("1\n2,3"));   //Add("1\n2,3") => 6

        result = 21;
        Assert.assertEquals(result, this.assignment.Add("1\n2,3\n4,5\n6"));   //Add("1\n2,3\n4,5\n6") => 21

    }

    @Test
    public void shouldHandleInvalidateData(){

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("input data is invalidate!");
        this.assignment.Add("1\n");   //Add("1\n") => throws an exception

        this.assignment.Add("1\n2\n");   //Add("1\n2\n") => throws an exception

        this.assignment.Add("1,2\n");   //Add("1,2\n") => throws an exception

    }

    @Test
    public void shouldSupportDifferentDelimiters(){

        int result = 3;
        Assert.assertEquals(result, this.assignment.Add("//;\\n1;2"));   //Add("//;\\n1;2") => 3

        result = 21;
        Assert.assertEquals(result, this.assignment.Add("1\n2//;\\3\n4;\\5\n6"));   //Add("1\n2//;\\3\n4,\\5\n6") => 21

    }

    @Test
    public void shouldSupportSpecialFormatDelimiters(){

        int result = 6;
        Assert.assertEquals(result, this.assignment.Add("//[***]\\n1***2***3"));   //Add("//[***]\n1***2***3") => 6
    }

    @Test
    public void shouldSupportMultipleDelimiters(){

        int result = 6;
        Assert.assertEquals(result, this.assignment.Add(" “//[*][%]\\n1*2%3"));   //Add(" “//[*][%]\n1*2%3") => 6
    }

    @Test
    public void shouldSupportMultipleDelimitersOfAnyLength(){

        int result = 6;
        Assert.assertEquals(result, this.assignment.Add("//[_^][-^]\\n1*2%3"));   //Add("//[_^][-^]\n1*2%3") => 6

        result = 16;
        Assert.assertEquals(result, this.assignment.Add("//[_^]1[-^]\\+12\n*%3"));   //Add("//[_^]1[-^]\\+12\n*%3") => 16
    }

}

