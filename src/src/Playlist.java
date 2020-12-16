import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private LinkedList<Song> playList;

    ListIterator<Song> playListIterator;

    public Playlist(){
        playList = new LinkedList<>();
    }

    /**
     * Add song to the playList
     * @param s - Song to add
     */
    public void addSong(Song s){
        playList.add(s);
    }

    /**
     * Get the song upon user choice
     * @param num - user choice
     * @return the song at a specific point
     */
    public Song getSong(int num){
        return playList.get(num);
    }

    /**
     * Print the playlist
     */
    public void printPlaylist(){
        int count = 1;
        playListIterator = playList.listIterator();
        while(playListIterator.hasNext()) {
            Song s = playListIterator.next();
            System.out.println("Track " + count + ".\n" + s);
            count++;
        }
    }

    /**
     * Size is used for some implementations in ModifyPlaylist
     * @return the size of the playlist
     */
    public int playlistSize(){
        return playList.size();
    }
}