package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException{
        int row;
        int col;
        for(int iter = 0; iter < buf.getSize(); iter++)
        {
            row = (int)(iter / 10);
            col = iter % 11;
            TextCharacter tc = TextCharacter.fromCharacter(buf.getChar(iter))[0];
            screen.setCharacter(row, col, tc);
        }
        row = (int) (buf.getCursorPosition() / 10);
        col = (buf.getCursorPosition() % 11);
        TerminalPosition pos = new TerminalPosition(row, col);
        screen.setCursorPosition(pos);
        screen.refresh();
    }
    
    public static boolean changeBuffer(GapBuffer buf, KeyStroke stroke){
        KeyType type = stroke.getKeyType();
        if (type.equals(KeyType.Character))
            buf.insert(stroke.getCharacter());
        if (type.equals (KeyType.ArrowLeft))
            buf.moveLeft();
        if (type.equals(KeyType.ArrowRight))
            buf.moveRight();
        if (type.equals(KeyType.Backspace))
            buf.delete();
        if (type.equals(KeyType.Escape))
            return false;
        return true;
    }
    
    /**
     * The main entry point for the TextEditor application.
     * There might be some issue in main, since Everytime I try to run this program, this pops out:
     * Exception in thread "main" java.io.IOException: To start java on Windows, use javaw! (see https://github.com/mabe02/lanterna/issues/335 )
     * I don't know what causes this issue, and I can't fix it
     * @param args command-line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }
        
        String path = args[0];
        System.out.format("Loading %s...\n", path);
        GapBuffer buf = new GapBuffer();
        Path file_path = Paths.get(path);
        if (Files.exists(file_path) && Files.isRegularFile(file_path)){
        String input = Files.readString(file_path);
        for(int iter = 0; iter < input.length(); iter++)
            buf.insert(input.charAt(iter));
        }
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        Boolean isrunning = true;
        while(isrunning){
        KeyStroke stroke = screen.readInput();
        isrunning = changeBuffer(buf, stroke);
        drawBuffer(buf, screen);
        Files.writeString(file_path, buf.toString());
        }
        screen.stopScreen();

    }
}
