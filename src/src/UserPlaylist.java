import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class UserPlaylist {
    private LinkedList<Song> userPlayList;

    ListIterator<Song> userPlayListIterator;

    public UserPlaylist(){
        userPlayList = new LinkedList<>();
    }

    /**
     * Add song to user playlist
     * @param fileName - the playlist name
     * @throws FileNotFoundException
     */
    public void addSong(String fileName) throws FileNotFoundException {
        String path = fileName + ".txt";
        File file = new File(path);
        Scanner in = new Scanner(file);

        while(in.hasNextLine()) {
            String songInfo = in.nextLine();
            String[] songInfoArr = songInfo.split(" - ");
            String songTitle = songInfoArr[0];
            String publisher = songInfoArr[1];
            String album = songInfoArr[2];
            String duration = songInfoArr[3];

            Song s = new Song(songTitle, publisher, album, duration);
            userPlayList.add(s);
        }
    }

    /**
     * Get the song upon user choice
     * @param num - user choice
     * @return the song at a specific point
     */
    public Song getSong(int num){
        return userPlayList.get(num);
    }

    /**
     * Clear the content from this playlist to make it as a temporary list
     */
    public void clearContent(){
        userPlayList.clear();
    }

    /**
     * Print the user's playlist
     */
    public void printPlaylist(){
        int count = 1;
        userPlayListIterator = userPlayList.listIterator();
        while(userPlayListIterator.hasNext()) {
            Song s = userPlayListIterator.next();
            System.out.println("Track " + count + ".\n" + s);
            count++;
        }
    }

    /**
     * Size is used for some implementation in ModifyPlaylist
     * @return the size of the list
     */
    public int userPlaylistSize(){
        return userPlayList.size();
    }
}
