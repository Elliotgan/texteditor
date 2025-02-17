# CSC 207: Text Editor

**Author**: Elliot Gan

email: ganellio@grinnell.edu

Primary developer: Apache NetBeans IDE 24

## Resources Used

+ Java 23 API https://docs.oracle.com/en/java/javase/23/docs/api/java.desktop/java/awt/Font.html
+ CSC207 project reuirement https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html
+ CSC207 readings https://osera.cs.grinnell.edu/ttap/data-structures/counting-operations.html
+ CSC207 labs https://osera.cs.grinnell.edu/ttap/data-structures-labs/exploring-java.html

## SimpleStringBuffer Analysis

insert analysis

I will use the length of the array stored in buffer as n, 

and I will take copying a single char as critical operation.

since strings are immutable, when I create string with some chars,
 
I have to first put(copy) those chars in a certain address, and than let

the string operation points towards them.

That means, if the string I created is long, I have to put a lot of chars

into a different position(since different strings would point toward

different addresses)

in the insertion, the mathematical model will be

T(n) = 2n + 2

The 2n part is

String before = (this.str).substring(0, index);

String after = (this.str).substring(index, sz);

and the before and after part in

this.str = (before + retstr + after);

since in those steps, strings with length 2n in total is created


the +2 part is

String retstr = new String(retch);

and the retstr part in 

this.str = (before + retstr + after);

since in those steps, string with length 2 in total is created.

2n+2 is in O(n)

## Changelog

_(TODO: fill me in with a log of your committed changes)_
