package edu.grinnell.csc207.texteditor;

import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import net.jqwik.api.constraints.*;
import net.jqwik.api.*;

public class SimpleStringBufferTests {
    
    /**
     * Compare if the content in string are equal or not
     * @param str1 a string
     * @param str2 a string
     * @return boolean that represent if two strings are identical
     */
    public static boolean strcompare (String str1, String str2){
        if (str1.length() != str2.length())
            return false;
        for (int iter = 0; iter < str1.length(); iter++)
            if ((str1.charAt(iter)) != (str2.charAt(iter)))
                return false;
        return true;
    }
    @Test
    public void NormalCase1(){
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        String teststr = testbuffer.toString();
        assert (strcompare(teststr, "aaaaa"));
    }
    
    @Test
    public void NormalCase2(){
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.insert('b');
        testbuffer.moveRight();
        testbuffer.insert('c');
        String teststr = testbuffer.toString();
        assert (strcompare(teststr, "aaabaca"));
    }
    
    @Test
    public void NormalCase3(){
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.insert('a');
        testbuffer.insert('b');
        testbuffer.insert('c');
        testbuffer.insert('d');
        testbuffer.insert('e');
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.delete();
        testbuffer.moveLeft();
        testbuffer.delete();
        String teststr = testbuffer.toString();
        assert (strcompare(teststr, "bde"));
    }
    
    @Test
    public void NormalCase4() throws InterruptedException{
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.insert('a');
        testbuffer.insert('b');
        testbuffer.insert('c');
        assertEquals (testbuffer.getCursorPosition(), 3);
        testbuffer.moveLeft();
        assertEquals (testbuffer.getCursorPosition(), 2);
        assert (testbuffer.getChar(0) == 'a');
        assert (testbuffer.getChar(1) == 'b');
        assert (testbuffer.getChar(2) == 'c');
    }
    
    @Test
    public void EdgeCase1(){
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.moveRight();
        testbuffer.moveRight();
        testbuffer.moveRight();
        testbuffer.insert('b');
        String teststr = testbuffer.toString();
        assert (strcompare(teststr, "aaaaab"));
    }
    
    @Test
    public void EdgeCase2(){
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        testbuffer.delete();
        testbuffer.delete();
        testbuffer.delete();
        testbuffer.insert('a');
        testbuffer.insert('a');
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.moveLeft();
        testbuffer.insert('b');
        String teststr = testbuffer.toString();
        assert (strcompare(teststr, "baa"));
    }
    
    @Property
    public boolean PropertyTest1 (@ForAll @IntRange(min = 0, max = 50) int sz){
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        String str = "b";
        for(int iter = 0; iter < sz; iter++){
            testbuffer.insert('a');
            str = (str + "a");
        }
        for(int iter = 0; iter < sz; iter++){
            testbuffer.moveLeft();
        }
        testbuffer.insert('b');
        return strcompare(str, testbuffer.toString());
    }
    
    @Property
    public boolean PropertyTest2 (@ForAll @IntRange(min = 0, max = 50) int sz1){
        int half = sz1 / 2;
        SimpleStringBuffer testbuffer = new SimpleStringBuffer();
        String str = "";
        for(int iter = 0; iter < sz1; iter++){
            testbuffer.insert('a');
        }
        for(int iter = 0; iter < half; iter++){
            str = (str + "a");
        }
        for(int iter = 0; iter < (sz1 - half); iter++){
            testbuffer.delete();
        }
        return strcompare(str, testbuffer.toString());
    }
}
