import java.util.LinkedList;
import java.util.Queue;

public class QueueList {
    private Queue<Song> songQueue;

    public QueueList(){
        songQueue = new LinkedList<>();
    }

    /**
     * Add song to the playList
     * @param s - Song to add
     */
    public void addSong(Song s){
        songQueue.add(s);
    }

    /**
     * Remove the first song of the playlist
     */
    public void skipSong(){
        songQueue.poll();
    }

    /**
     * The size of the queue for some implementation in ModifyPlaylist
     * @return the size of the queue
     */
    public int songQueueSize(){
        return songQueue.size();
    }

    /**
     * Print the queue
     */
    public void printQueue(){
        int count = 1;
        System.out.println("Your current Queue");
        System.out.println("------------------");
        for(Song s : songQueue){
            System.out.println("Track " + count + ".\n" + s);
            count++;
        }
    }
}