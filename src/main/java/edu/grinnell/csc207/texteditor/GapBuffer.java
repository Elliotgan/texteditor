package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private char[] charr;
    private int sz;
    private int index;
    private int gap_end;

    GapBuffer() {
        this.charr = new char[5];
        this.sz = 5;
        this.index = 0;
        this.gap_end = 5;
    }

    /**
     * copy charr_cpy to charr_pst, the chars in charr_cpy before breakpoint
     * goes to head of charr_pst, chars after breakpoint goes to tail of
     * charr_pst. length of charr_cpy should not be longer than length of
     * charr_pst
     *
     * @param charr_cpy
     * @param charr_pst
     */
    private static void copychar(char[] charr_cpy, int breakpoint, char[] charr_pst) {
        System.arraycopy(charr_cpy, 0, charr_pst, 0, breakpoint);
        System.arraycopy(charr_cpy, breakpoint, charr_pst, (charr_pst.length - (charr_cpy.length - breakpoint)), (charr_cpy.length - breakpoint));
    }

    /**
     * insert the ch in current index of buffer
     *
     * @param ch the ch that should be inserted
     */
    public void insert(char ch) {
        if (this.index == this.gap_end) {
            char[] newcharr = new char[this.sz * 2];
            copychar(this.charr, this.index, newcharr);
            this.charr = newcharr;
            this.gap_end += this.sz;
            this.sz *= 2;
        }
        this.charr[this.index] = ch;
        this.index++;
    }

    /**
     * delete the ch in current index of budder, if the index is at 0, do
     * nothing.
     */
    public void delete() {
        if (this.index == 0) {
            return;
        }
        this.index--;
    }

    /**
     * get the position of cursor
     *
     * @return int that represents position of cursor
     */
    public int getCursorPosition() {
        return this.index;
    }

    /**
     * move cursor, which is the index, towards left if the index is at leftmost
     * position, do nothing.
     */
    public void moveLeft() {
        if (this.index <= 0) {
            return;
        }
        this.charr[this.gap_end - 1] = this.charr[this.index - 1];
        this.gap_end--;
        this.index--;
    }

    /**
     * move cursor, which is the index, towards right if the index is at
     * rightmost position, do nothing.
     */
    public void moveRight() {
        if (this.gap_end >= this.sz) {
            return;
        }
        this.charr[this.index] = this.charr[this.gap_end];
        this.gap_end++;
        this.index++;
    }

    /**
     * get the current size of buffer
     *
     * @return int that represents current size of buffer
     */
    public int getSize() {
        return (this.sz - (this.gap_end - this.index));
    }

    /**
     * get the char at specific position
     *
     * @param i int
     * @return the char at i
     * @throws IndexOutOfBoundsException
     */
    public char getChar(int i) throws IndexOutOfBoundsException {
        if (!(0 <= i) && (i < this.getSize())) {
            throw new IndexOutOfBoundsException("invalid position");
        }
        if (i < index) {
            return this.charr[i];
        }
        return this.charr[i + (this.gap_end - this.index)];

    }

    /**
     * return string stored in buffer. Gaps are skipped
     *
     * @return string stored in buffer
     */
    @Override
    public String toString() {
        char[] ret = new char[this.getSize()];
        int head_length = index;
        int tail_length = (this.sz - this.gap_end);
        System.arraycopy(this.charr, 0, ret, 0, head_length);
        System.arraycopy(this.charr, this.gap_end, ret, (this.getSize() - tail_length), (this.sz - this.gap_end));
        return (new String(ret));
    }

    /**
     * return the capacity of the current buffer
     *
     * @return capacity of the current buffer
     */
    public int getrealsize() {
        return this.sz;
    }
}
