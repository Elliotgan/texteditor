package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    private String str;
    private int sz;
    private int index;

    /**
     * A naive implementation of a text buffer using a string.
     */
    SimpleStringBuffer() {
        this.str = "";
        this.index = 0;
        this.sz = 0;
    }

    /**
     * insert the ch in current index of buffer
     * @param ch the ch that should be inserted
     */
    public void insert(char ch) {
        char[] retch = new char[1];
        retch[0] = ch;
        String retstr = new String(retch);

        String before = (this.str).substring(0, index);
        String after = (this.str).substring(index, sz);
        this.str = (before + retstr + after);
        this.sz ++;
        this.index ++;
    }

    /**
     * delete the ch in current index of budder, if the
     * index is at 0, do nothing.
     */
    public void delete() {
        if (this.index == 0)
            return;
        String before = (this.str).substring(0, index - 1);
        String after = (this.str).substring(index, sz);
        this.str = (before + after);
        this.sz --;
        this.index --;
    }

    /**
     * get the position of cursor
     * @return int that represents position of cursor
     */
    public int getCursorPosition() {
        return this.index;
    }

    /**
     * move cursor, which is the index, towards right
     * if the index is  at rightmost position, do nothing.
     */
    public void moveLeft() {
        if (this.index > 0)
            this.index --;
    }

    /**
     * move cursor, which is the index, towards left
     * if the index is  at leftmost position, do nothing.
     */
    public void moveRight() {
        if (this.index < this.sz)
            this.index ++;
    }

    /**
     * get the current size of buffer
     * @return int that represents current size of buffer
     */
    public int getSize() {
        return this.sz;
    }

    /**
     * get the char at specific position
     * @param i int 
     * @return the char at i 
     * @throws IndexOutOfBoundsException
     */
    public char getChar(int i) throws IndexOutOfBoundsException {
        if((0 <= i) && (i < this.sz))
          return (this.str).charAt(i);
        throw new IndexOutOfBoundsException("int i not in range");
    }

    /**
     * return string stored in buffer
     * @return string stored in buffer
     */
    @Override
    public String toString() {
        return this.str;
    }
}
