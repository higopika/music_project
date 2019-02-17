package Music;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;  
import java.io.*;
import java.util.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import java.awt.event.*; //WindowAdapter should be within this...
import javax.swing.*;

public class Composer {
    String instrument = "Piano";
    String lyrics = "";
    Player player;
    boolean exit = false;
    JFrame frame;

    public Composer(){
        System.out.println("Construcing Composer");
        player = new Player();
        frame = new JFrame("Default Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        DocumentListener documentListener = new DocumentListener() {
                public void changedUpdate(DocumentEvent documentEvent)
                {
                    playNote(documentEvent);
                    //System.out.println("Changed");
                }

                public void insertUpdate(DocumentEvent documentEvent)
                {
                    playNote(documentEvent);
                    //System.out.println("Insert");
                }

                public void removeUpdate(DocumentEvent documentEvent)
                {
                    playNote(documentEvent);
                    //sSystem.out.println("Remove");

                }
            };
        textField.getDocument().addDocumentListener(documentListener);

        frame.setSize(250, 150);
    }

    public void chooseTone()throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the tone 1 - grand piano 2 - flute 3 - violin");
        int tone=Integer.parseInt(br.readLine());
        System.out.println("Instructions______________________________");
        System.out.println("The keys a-C s-D d-E f-F g-G h-A j-B k-C");
        System.out.println("The keys w-C# e-D# t-F# y-G# u-A#");
        System.out.println("Press 'o' to save  and Press 'x' to exit");
        System.out.println("------------------------------------------");

        switch(tone)
        {
            case 1:
            instrument = "Piano"; 
            break;
            case 2:
            instrument = "Flute";
            break;
            case 3:
            instrument = "Violin";
            break;
        }
    }
    
    String getTone()
    {
        return instrument;
    }

    public void playNotes()  {
        exit = false;
        frame.setVisible(true);
    }

    private void playNote(DocumentEvent documentEvent) 
    {
        DocumentEvent.EventType type = documentEvent.getType();
        String typeString = null;
        if (type.equals(DocumentEvent.EventType.CHANGE)) {
            typeString = "Change";
        }  else if (type.equals(DocumentEvent.EventType.INSERT)) {
            typeString = "Insert";
        }  else if (type.equals(DocumentEvent.EventType.REMOVE)) {
            typeString = "Remove";
        }

        Document source = documentEvent.getDocument();
        int length = source.getLength();

        String ch = "";
        try {
            ch = source.getText(length-1, 1);
        } catch (BadLocationException badLocationException) {
            System.out.println("Contents: Unknown");
            return;
        }
        String note = getNote(ch);
        if(exit == true)
        {
            try {
                    source.remove(0, length);
            } catch (BadLocationException badLocationException) {
                System.out.println("Contents: Unknown");
                return;
            }
            frame.setVisible(false);
        }
        else if (note != "")
        {
            //Pattern p1 = new Pattern(note).setInstrument(instrument);    
            player.play("I["+instrument + "] " +note);
        }
    }

    public String getNote(String ch_str)
    {
        char ch = ch_str.charAt(0);
        switch(ch)
        {
            case 'a':
            {  lyrics += "c ";return("c"); }            
            case 'w':
            {  lyrics += "c# ";return("c#"); }            
            case 's':
            {  lyrics += "d ";return("d"); }            
            case 'e':
            {  lyrics += "d# ";return("d#"); }            
            case 'd':
            {  lyrics += "e ";return("e"); }            
            case 'f':
            {  lyrics += "f ";return("f"); }            
            case 't':
            {  lyrics += "f# ";return("f#"); }            
            case 'g':
            {  lyrics += "g ";return("g"); }            
            case 'y':
            {  lyrics += "g# ";return("g#"); }            
            case 'h':
            {  lyrics += "a ";return("a"); }            
            case 'u':
            {  lyrics += "a# ";return("a#"); }            
            case 'j':
            {  lyrics += "b ";return("b"); } 
            case 'k':
            {  lyrics += "c6 ";return("c6"); }  
            case 'x':
            {  exit = true;
                return("exit"); } 
        }
        return "";
    }

    public String getLyrics()
    {
        return lyrics;
    }

    public boolean isExit()
    {
        return exit;
    }
}