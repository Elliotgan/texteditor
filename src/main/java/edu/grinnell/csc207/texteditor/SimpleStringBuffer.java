package edu.grinnell.csc207.texteditor;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    private String str;
    private int sz;
    private int index;

    SimpleStringBuffer() {
        this.str = "";
        this.index = 0;
        this.sz = 0;
    }

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

    public void delete() {
        if (this.index == 0)
            return;
        String before = (this.str).substring(0, index - 1);
        String after = (this.str).substring(index, sz);
        this.str = (before + after);
        this.sz --;
        this.index --;
    }

    public int getCursorPosition() {
        return this.index;
    }

    public void moveLeft() {
        if (this.index > 0)
            this.index --;
    }

    public void moveRight() {
        if (this.index < this.sz)
            this.index ++;
    }

    public int getSize() {
        return this.sz;
    }

    public char getChar(int i) {
        return (this.str).charAt(this.index);
    }

    @Override
    public String toString() {
        return this.str;
    }
}
