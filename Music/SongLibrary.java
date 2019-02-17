package Music;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.io.RandomAccessFile;
import java.io.FileReader;
import java.io.File;
import java.util.Iterator;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

class SongLibrary 
{
    Scanner sc;
    File folder;
    File[] listOfFiles;
    Song[] song_library;
    List<Song> myList = new ArrayList<Song>();

    public SongLibrary() throws IOException
    {
        //sc = new Scanner(System.in);
        //folder =new File("C:\\SongLibrary\\");
        //listOfFiles=folder.listFiles();
        readSongs();
    }

    public void listSongs() throws IOException
    {
        //Read the list of songs
        //Print on the screen with serial number, the song name, composer and ratings
        //
        System.out.println("---------------------------------------");
        //System.out.println("List Size " +myList.size());
        System.out.println("Title\tComposer\tRating");
        System.out.println("---------------------------------------");
        for(int i = 0; i < myList.size(); i++){
            Song s = myList.get(i);
            System.out.print((i+1)+")");
            System.out.print(s.Title);
            System.out.print("\t"+s.Composer);
            System.out.println("    "+s.rating);
        }
        System.out.println("---------------------------------------");
    }

    public void readSongs() throws IOException
    {

        File folder = new File("C:\\SongLibrary\\");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                readSong(listOfFiles[i].getAbsolutePath());
            }
        }
    }

    public void readSong(String fileName) throws IOException
    {
        String thisLine = null; 
        FileReader fr = null;
        String title = null, tone = null, lyrics = null, composer = null, ratings_str = null, numRatings_str = null;

        // open input stream test.txt for reading purpose.
        try{
            fr=new FileReader(fileName);
        } catch (IOException ioException){
            System.out.println("File not present");
        }
        BufferedReader br = new BufferedReader(fr);

        while ((thisLine = br.readLine()) != null) {
            try {
                String name = thisLine.substring(0,thisLine.indexOf(":"));
                if (name.equals("name"))
                    title = thisLine.substring(thisLine.indexOf(":")+1);
                else if (name.equals("composer"))
                    composer = thisLine.substring(thisLine.indexOf(":")+1);
                else if (name.equals("tone")) 
                    tone = thisLine.substring(thisLine.indexOf(":")+1);
                else if (name.equals("lyrics"))
                    lyrics = thisLine.substring(thisLine.indexOf(":")+1);
                else if (name.equals("ratings"))
                    ratings_str = thisLine.substring(thisLine.indexOf(":")+1);
                else if (name.equals("numRatings"))
                    numRatings_str = thisLine.substring(thisLine.indexOf(":")+1);
            }
            catch(Exception e) {

                e.printStackTrace();
            }

        }
        Song s = new Song(title, tone, lyrics, composer, ratings_str, numRatings_str);
        myList.add(s);
        System.out.println("*******************");
    }

    public void add(Song s)
    {
        myList.add(s);
    }

    public void readFile()throws IOException
    {
        System.out.println("Enter the number of file you want to play");
        int n=sc.nextInt();

        String fname = listOfFiles[n-1].getName();
        System.out.println(fname);
        BufferedReader br = new BufferedReader(new FileReader(fname));
        String st;
        while((st=br.readLine()) != null){
            System.out.println(st);
        }
    }

    public void playSong(int numChoice)
    {
        Song s1 = myList.get(numChoice-1); 
        Pattern p = new Pattern(s1.Lyrics).setInstrument(s1.Tone);
        Player player = new Player();
        player.play(p);
    }

    public void rate(int numChoice, double rating)
    {
        Song s1 = myList.get(numChoice-1);
        double old_rating = s1.rating;
        int num_ratings = s1.numRatings;

        double new_rating = ((old_rating * num_ratings) + rating)/(++num_ratings);
        s1.numRatings = num_ratings;
        s1.rating = new_rating;
        s1.saveSong();
    }
   
}

