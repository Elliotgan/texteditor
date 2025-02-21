package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * draw the words from buffer to screen
     *
     * @param buf the buffer being drawn
     * @param screen the screen used to draw buffer
     * @throws IOException
     */
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        int row;
        int col;
        int numInRow = 60;
        for (int iter = 0; iter < buf.getSize(); iter++) {
            row = iter / numInRow;
            col = (iter % numInRow);
            TextCharacter tc = TextCharacter.fromCharacter(buf.getChar(iter))[0];
            screen.setCharacter(col, row, tc);
        }
        for (int iter = buf.getSize(); iter < buf.getrealsize(); iter++) {
            TextCharacter tc = TextCharacter.fromCharacter(' ')[0];
            row = iter / numInRow;
            col = (iter % numInRow);
            screen.setCharacter(col, row, tc);
        }
        row = buf.getCursorPosition() / numInRow;
        col = (buf.getCursorPosition() % numInRow);
        TerminalPosition pos = new TerminalPosition(col, row);
        screen.setCursorPosition(pos);
        screen.refresh();
    }

    /**
     * change the buffer using keystroke
     *
     * @param buf the buffer being changed
     * @param stroke telling which key/keys has been pressed
     * @return boolean telling if the buffer should be closed
     */
    public static boolean changeBuffer(GapBuffer buf, KeyStroke stroke) {
        KeyType type = stroke.getKeyType();
        if (type.equals(KeyType.Character)) {
            buf.insert(stroke.getCharacter());
        }
        if (type.equals(KeyType.ArrowLeft)) {
            buf.moveLeft();
        }
        if (type.equals(KeyType.ArrowRight)) {
            buf.moveRight();
        }
        if (type.equals(KeyType.Backspace)) {
            buf.delete();
        }
        if (type.equals(KeyType.Escape)) {
            return false;
        }
        return true;
    }

    /**
     * The main entry point for the TextEditor application. There might be some
     * issue in main, since Everytime I try to run this program, this pops out:
     * Exception in thread "main" java.io.IOException: To start java on Windows,
     * use javaw! (see https://github.com/mabe02/lanterna/issues/335 ) I don't
     * know what causes this issue, and I can't fix it
     *
     * @param args command-line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }

        String path = args[0];
        System.out.format("Loading %s...\n", path);
        GapBuffer buf = new GapBuffer();
        Path filePath = Paths.get(path);
        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            String input = Files.readString(filePath);
            for (int iter = 0; iter < input.length(); iter++) {
                buf.insert(input.charAt(iter));
            }
        }

        SwingTerminalFrame term = new DefaultTerminalFactory().createSwingTerminal();
        term.setVisible(true);
        Screen screen = new com.googlecode.lanterna.screen.TerminalScreen(term);

        screen.startScreen();
        Boolean isrunning = true;
        drawBuffer(buf, screen);
        while (isrunning) {
            KeyStroke stroke = screen.readInput();
            isrunning = changeBuffer(buf, stroke);
            drawBuffer(buf, screen);
            Files.writeString(filePath, buf.toString());
        }
        screen.stopScreen();
        System.err.println("Finished!");
    }
}
