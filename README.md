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

commit e5d82c59329a1acc2f3b0b6548f032d7536256b8 (HEAD -> main, origin/main, origin/HEAD)
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 09:48:27 2025 +0000

    main procedure done, even though it does not compile on my computer

commit 31f5e8f8e5150afb15e8c46d62796b9782d25290
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 07:45:28 2025 +0000

    I don't know why, but this file went blue...for some reason?

commit 91a44cab7d12d50690339c3ccd45903216b8671b
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 07:43:18 2025 +0000

    now the array size duplicate every time it has to increase

commit 7e39db1767d9c4e06a9655fbfec3d5a99a5f4a05
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 07:37:18 2025 +0000

    GapBufferTests.java completed

commit 36676d6bf10dac2ed1b6b36a58ae19b72b47eeef
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 07:34:53 2025 +0000

    main functions in GapBuffer.java completed

commit 7bb9d719bdcf29488b452d5553d2289504aaa8b0
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 05:11:09 2025 +0000

    getChar now throws IndexOutOfBoundsException

commit 1dbf4af2c0b62825496822164e61f0e056dea833
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 05:09:12 2025 +0000

    NormalCase4 added

commit 5715867b8c03132c514a3a90eaf72731287baf50
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 04:55:05 2025 +0000

    Tests completed

commit 88840f5b32e97e9403b1001ba32a5a7f4f34218a
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 04:53:27 2025 +0000

    javadoc and some small changes added

commit e0537b9b5f34d9356017e627bfb55e393c0d60ff
Author: Elliot <elliotgan@126.com>
Date:   Mon Feb 17 04:13:54 2025 +0000

    basic functions of SimpleStringBuffer.java completed

commit 32a90495f40bd92ce905d4d78fbdab4dbaa6d5f9
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Thu Feb 13 12:40:05 2025 -0600

    Project files

commit 02dc92144ecc088bcefb4a9798df0934efe300c1
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Thu Feb 13 12:39:53 2025 -0600

    initial commit
(END)
