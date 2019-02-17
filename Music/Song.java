package Music;

import java.util .*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Song 
{
    String Title="";
    String Tone="";
    String Composer="";
    String Lyrics="";
    double rating;
    int numRatings;

    private String Directory = "C:\\SongLibrary\\";
    public Song(String title, String tone, String lyrics, String composer)
    {
        Title = title;
        Tone = tone;
        Lyrics = lyrics;
        Composer = composer;

        File file = new File(Directory);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("SongLibrary is created!");
            } else {
                System.out.println("SongLibrary Failed to create directory!");
            }
        }
    }

    public Song(String title, String tone, String lyrics, String composer, String ratings_str, String numRatings_str)
    {
        Title = title;
        Tone = tone;
        Lyrics = lyrics;
        Composer = composer;
        try{
            rating = Double.valueOf(ratings_str);
        } catch (NullPointerException nullpointerexception){
            rating = 0.0;
        }

        try{
        numRatings = Integer.valueOf(ratings_str);
    }catch (Exception e){
        numRatings = 0;
    }
        File file = new File(Directory);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("SongLibrary is created!");
            } else {
                System.out.println("SongLibrary Failed to create directory!");
            }
        }
    }

    public int saveSong()
    {

        try {
            String filename = Directory + Title;
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write("name:"+Title+"\n");
            fileWriter.write("composer:"+Composer+"\n");
            fileWriter.write("tone:"+Tone+"\n");
            fileWriter.write("lyrics:"+Lyrics+"\n");
            fileWriter.write("ratings:"+rating+"\n");
            fileWriter.write("numRatings:"+numRatings+"\n");

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;  
        }
        return 0;
    }
}

     