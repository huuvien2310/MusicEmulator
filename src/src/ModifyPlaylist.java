import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ModifyPlaylist {

    Playlist playList = new Playlist();
    UserPlaylist userPlaylist = new UserPlaylist();
    QueueList queueList = new QueueList();

    /**
     * This method is used for adding all songs in the text file to the playlist as default
     * It will be automatically run when the program is executing
     * @throws FileNotFoundException
     */
    public void defaultPlayList() throws FileNotFoundException {
        File file = new File("Song.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            String songInfo = in.nextLine();
            String[] songInfoArr = songInfo.split(" - ");
            String songTitle = songInfoArr[0];
            String publisher = songInfoArr[1];
            String album = songInfoArr[2];
            String duration = songInfoArr[3];

            Song s = new Song(songTitle, publisher, album, duration);
            playList.addSong(s);
        }
    }

    /**
     * This method is to add new song the default playlist
     * It requires the correct format otherwise nothing will work
     * @param in - the Scanner to read from
     * @throws FileNotFoundException
     */
    public void addSongToDefaultPlayList(Scanner in) throws IOException {
        System.out.print("Enter your new song to the playlist in the format \"Song name - Publisher/Singer - Album - Duration\": ");
        String songInfo = in.nextLine();
        String[] songInfoArr = songInfo.split(" - ");

        if(songInfoArr.length > 4){
            System.out.println("Please enter the correct format!!");
            addSongToDefaultPlayList(in);
        }
        else {
            try {
                String songTitle = songInfoArr[0];
                String publisher = songInfoArr[1];
                String album = songInfoArr[2];
                String duration = "";
                if(songInfoArr[3].matches("(\\d.*):(\\d.*)")) {
                    duration = songInfoArr[3];

                    Song s = new Song(songTitle, publisher, album, duration);
                    playList.addSong(s);

                    //Append new song to the default text file
                    String fileName = "Song.txt";
                    appendPlaylist(fileName, songInfoArr[0], songInfoArr[1], songInfoArr[2], songInfoArr[3]);
                }
                else {
                    System.out.println("Please enter the correct duration format!!");
                    addSongToDefaultPlayList(in);
                }

            } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                System.out.println("Please try again");
                addSongToDefaultPlayList(in);
            }
        }
    }

    /**
     * Print the default playlist
     */
    public void printPlaylist(){
        playList.printPlaylist();
    }

    /**
     * Add song from default playlist to queue
     * @param in - the Scanner to read from
     */
    public void playListToQueue(Scanner in){
        System.out.print("Enter track's number to add song to queue: ");
        int num = in.nextInt();
        if(num < playList.playlistSize()) {
            queueList.addSong(playList.getSong(num - 1));
            printQueue();
        }
        else{
            System.out.println("Please try again, there's only " + playList.playlistSize() + " songs in this playlist");
            playListToQueue(in);
        }
    }

    /**
     * Add song from user's playlist to queue
     * @param in - the Scanner to read from
     * @param fileName - the title of the playlist that user will input song from
     * @throws FileNotFoundException
     */
    public void userPlaylistToQueue(Scanner in, String fileName) throws FileNotFoundException {
        try {
            userPlaylist.addSong(fileName);
            System.out.print("Enter track's number to add song to queue: ");
            int num = in.nextInt();
            if(num < userPlaylist.userPlaylistSize()) {
                queueList.addSong(userPlaylist.getSong(num - 1));
                printQueue();
            }
            else{
                System.out.println("Please try again, there's only " + userPlaylist.userPlaylistSize() + " songs in this playlist");
                userPlaylistToQueue(in, fileName);
            }
            userPlaylist.clearContent();
        } catch(FileNotFoundException fileNotFoundException){
            System.out.println("The playlist doesn't exist");
        }
    }

    /**
     * Print the current queue
     */
    public void printQueue(){
        queueList.printQueue();
    }

    /**
     * If there's song in the queue, skip it
     * After removed, if there is still any song left in the queue, print the queue, else, print a message
     * If there's no song in the queue initially, print a message
     */
    public void skipQueue(){
        if(queueList.songQueueSize() > 0) {
            queueList.skipSong();
            if(queueList.songQueueSize() == 0) {
                System.out.println("There's currently no playing song");
            }
            else{
                printQueue();
            }
        }
        else{
            System.out.println("There's currently no playing song");
        }
    }

    /**
     * This method serves the purpose of creating new playlist
     * @param in - the Scanner to read from
     * @throws IOException
     */
    public void createPlaylist(Scanner in) throws IOException {
        File newPlaylist;
        System.out.print("Enter the name of your new playlist: ");
        String fileName = in.nextLine();
        String path = fileName +".txt";

        newPlaylist = new File(path);
        if(!newPlaylist.exists()) {
            newPlaylist.createNewFile();
            System.out.println("You created the " + fileName + " playlist");
        }
        else{
            System.out.println("You can't create duplicate playlist!!!");
        }
    }

    /**
     * Append new song to the text file
     * @param fileName - the title of the playlist
     * @param songName - the title of the song
     * @param publisher - the publisher of the song
     * @param album - the album contains the song
     * @param duration - the duration of the song
     */
    public void appendPlaylist(String fileName, String songName, String publisher, String album, String duration){
        try{
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            out.format(Locale.ENGLISH, "%s - %s - %s - %s\n", songName, publisher, album, duration);
            out.close();
        } catch (IOException e){
            System.out.println("No input");
        }
    }

    /**
     * This method is to add song to the user's playlist
     * Only user's playlist can be modified here
     * Only existed playlist can be modified, otherwise, print a message
     * @param in - the Scanner to read from
     * @throws FileNotFoundException
     */
    public void addSongToPlaylist(Scanner in) throws FileNotFoundException {
        System.out.print("Go to playlist: ");
        String fileName = in.nextLine();
        String path = fileName + ".txt";

        File newPlaylist = new File(path);
        if(newPlaylist.exists()) {
            if(fileName.equalsIgnoreCase("Song")) {
                System.out.println("You can't modify the default playlist here!!");
            }
            else {
                System.out.print("Enter track's number to add song from default playlist to your playlist: ");
                int userChoice = in.nextInt();
                if(userChoice > playList.playlistSize()) {
                    System.out.println("The song doesn't exist in the " + fileName + " playlist");
                }
                else {
                    //Add song to user playlist
                    Song s = playList.getSong(userChoice - 1);
                    //Append it to the .txt file
                    appendPlaylist(path, s.getSongTitle(), s.getPublisher(), s.getAlbum(), s.getDuration());
                    System.out.println("The song \"" + s.getSongTitle() + "\" is added to " + fileName + " playlist");
                }
            }
        }
        else{
            System.out.println("The playlist doesn't exist");
        }
    }

    /**
     * Print user playlist upon user's choice
     * If the playlist isn't exist, simply print a message
     * @param fileName - the playlist name
     * @throws FileNotFoundException
     */
    public void printUserPlaylist(String fileName) throws FileNotFoundException{
        if(fileName.equalsIgnoreCase("Song")) {
            System.out.println("You can't view the default playlist here!!");
        }
        else {
            try {
                userPlaylist.addSong(fileName);
                userPlaylist.printPlaylist();
                userPlaylist.clearContent();
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Can't find the playlist, please try again");
            }
        }
    }

    /**
     * Print the default playlist
     */
    public void printDefaultPlaylist(){
        playList.printPlaylist();
    }

    /**
     * Delete user playlist (Default playlist won't allow to be deleted)
     * Only user playlist can be modified
     * Only existed playlist can be modified, otherwise, print a message
     * @param in - the Scanner to read from
     */
    public void deletePlaylist(Scanner in){
        System.out.print("Enter the playlist you want to delete (Note that the default playlist is indelible): ");
        String fileName = in.nextLine();
        if(fileName.equalsIgnoreCase("Song")){
            System.out.println("You can't delete the default playlist!!!");
            deletePlaylist(in);
        }
        else{
            String path = fileName + ".txt";
            File playlist = new File(path);
            if(!playlist.exists()) {
                System.out.println("There's no " + fileName + " playlist");
                deletePlaylist(in);
            }
            else{
                playlist.delete();
                System.out.println("Playlist " + fileName + " has been successfully deleted");
            }
        }
    }
}