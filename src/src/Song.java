
public class Song {
    private String songTitle;
    private String publisher;
    private String album;
    private String duration;

    public Song(String songTitle, String publisher, String album, String duration){
        this.songTitle = songTitle;     //song Title
        this.publisher = publisher;     //song publisher/singer
        this.album = album;             //album
        this.duration = duration;       //duration of song
    }

    public void setSongTitle(String songTitle){
        this.songTitle = songTitle;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public void setAlbum(String album){
        this.album = album;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }

    public String getSongTitle(){
        return songTitle;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getAlbum(){
        return album;
    }

    public String getDuration(){
        return duration;
    }

    @Override
    public String toString(){
        return songTitle + " | " + album + " " + duration + "\n" + publisher + "\n";
    }
}