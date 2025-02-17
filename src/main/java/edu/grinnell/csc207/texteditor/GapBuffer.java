package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    private char[] charr;
    private int sz;
    private int index;
    private int gap_end;
    
    GapBuffer(){
        this.charr = new char[5];
        this.sz = 5;
        this.index = 0;
        this.gap_end = 5;
    }
    
    /**
     * copy charr_cpy to charr_pst, the chars in charr_cpy before breakpoint goes to head
     * of charr_pst, chars after breakpoint goes to tail of charr_pst.
     * length of charr_cpy should not be longer than length of charr_pst
     * @param charr_cpy
     * @param charr_pst
     */
    private static void copychar(char[] charr_cpy, int breakpoint, char[] charr_pst){
        System.arraycopy(charr_cpy, 0, charr_pst, 0, breakpoint);
        System.arraycopy(charr_cpy, breakpoint, charr_pst, (charr_pst.length - (charr_cpy.length - breakpoint)), (charr_cpy.length - breakpoint));
    }
    
    public void insert(char ch) {
        if (this.index == this.gap_end){
            char[] newcharr = new char[this.sz + 5];
            copychar(this.charr, this.index, newcharr);
            this.charr = newcharr;
            this.gap_end += 5;
            this.sz += 5;
        }
        this.charr[this.index] = ch;
        this.index++;
    }

    public void delete() {
        if (this.index == 0){
            return;
        }
        this.index--;
    }

    public int getCursorPosition() {
        return this.index;
    }

    public void moveLeft() {
        if (this.index <= 0)
            return;
        this.charr[this.gap_end - 1] = this.charr[this.index - 1];
        this.gap_end --;
        this.index --;
    }

    public void moveRight() {
        if (this.gap_end >= this.sz)
            return;
        this.charr[this.index] = this.charr[this.gap_end];
        this.gap_end ++;
        this.index ++;
    }

    public int getSize() {
        return (this.sz - (this.gap_end - this.index));
    }

    public char getChar(int i) throws IndexOutOfBoundsException{
        if (!(0 <= i) && (i < this.getSize()))
            throw new IndexOutOfBoundsException("invalid position");
        if (i < index)
            return this.charr[i];
        return this.charr[i + (this.gap_end - this.index)];
        
    }

    public String toString() {
        char[] ret = new char[this.getSize()];
        int head_length = index;
        int tail_length = (this.sz - this.gap_end);
        System.arraycopy(this.charr, 0, ret, 0, head_length);
        System.arraycopy(this.charr, this.gap_end, ret, (this.getSize() - tail_length), (this.sz - this.gap_end));
        return (new String(ret));
    }
}
