package Music;
import java .io.*;
import java .util.*;
public class Menu
{
    Composer cp;
    SongLibrary library;
    Scanner sc= new Scanner(System.in);
    public Menu() throws IOException
    {
        cp = new Composer();
        library =new SongLibrary();
    }

    private void displayMenu()
    {

        System.out.println("Akipog Arts Music Library :-)");
        System.out.println("_____________________________");
        System.out.println("* Press 1 to compose a song");
        System.out.println("* Press 2 to listen to the songs in the music library");
        System.out.println("* Press 3 to Exit");
        System.out.println("_____________________________");
    }

    public void acceptUserInput()throws IOException
    {
        while (true)
        {

            displayMenu();
            BufferedReader br=new BufferedReader(new InputStreamReader (System.in));
            int ch=Integer.parseInt(br.readLine());
            switch(ch)
            {
                case 1:
                {

                    cp.chooseTone();
                    cp.playNotes();
                    while (!cp.isExit()){

                        try{
                            java.lang.Thread.sleep(100);
                        }catch (InterruptedException interruptedException){
                            System.out.println("Caught InterruptedException");
                        }
                    }

                    
                    String name="";
                    String composer="";
                    String fileName = "";
                    System.out.println("Do you want to save you song?? enter 'y' for yes and 'n' for no");
                    String c= sc.next();
                    if(c.equalsIgnoreCase("y"))
                    {
                        System.out.println("Enter title of the song");
                        name =br.readLine();
                        System.out.println("Enter name of composer");
                        composer = br.readLine();

                        fileName =name+".txt";
                        System.out.println(fileName);

                        Song s=new Song(name, cp.getTone(), cp.getLyrics(),composer);
                        s.saveSong();
                        library.add(s);
                    }

                    break;
                }
                case 2:
                {
                    library.listSongs();
                    System.out.println("Enter the number of song you want to listen");
                    int choice =sc.nextInt();
                    library.playSong(choice);
                    System.out.println("Do you want to rate the song??(y or n)");
                    String rate=sc.next();
                    if(rate.equalsIgnoreCase("y"))
                    {
                        System.out.println("Enter rating");
                        double rating= sc.nextDouble();
                        library.rate(choice, rating);
                    } 
                    break;
                }
                case 3:
                {
                    return;
                }
 
            }
            System.out.println("Came out of the loop");
        }
    }
}
