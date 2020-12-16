import java.io.*;
import java.util.*;

public class Main {

    /**
     Print the main menu without duplication
     */
    public void printMenu(){
        System.out.println("======================Main Menu======================");
        System.out.println("1. View all current songs in the default playlist");
        System.out.println("2. Add song to the default playlist");
        System.out.println("3. Add song from playlist to queue");
        System.out.println("4. Remove current playing song in your queue");
        System.out.println("5. Create new playlist");
        System.out.println("6. Modify your playlist");
        System.out.println("7. Exit");
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ModifyPlaylist modifyPlaylist = new ModifyPlaylist();
        Main main = new Main();
        System.out.println("\t\t\t\t=========================");
        System.out.println("\t\t\t\tWelcome to Music Playlist");
        System.out.println("\t\t\t\t=========================");
        main.printMenu();

        //Create the default playlist
        modifyPlaylist.defaultPlayList();

        boolean done = false;
        while(!done){
            System.out.print("Your choice: ");
            String choice = in.next();
            switch(choice){
                case "1":
                    modifyPlaylist.printDefaultPlaylist();
                    main.printMenu();
                    break;
                case "2":
                    modifyPlaylist.addSongToDefaultPlayList(new Scanner(System.in));
                    System.out.println("Your current playlist");
                    modifyPlaylist.printPlaylist();
                    main.printMenu();
                    break;
                case "3":
                    System.out.print("You want to open which playlist: ");
                    Scanner user = new Scanner(System.in);
                    String fileName = user.nextLine();
                    String playList = fileName.toUpperCase();

                    if(playList.contains("SONG")){
                        modifyPlaylist.playListToQueue(new Scanner(System.in));
                    }
                    else{
                        modifyPlaylist.userPlaylistToQueue(new Scanner(System.in), fileName);
                    }
                    main.printMenu();
                    break;
                case "4":
                    modifyPlaylist.skipQueue();
                    main.printMenu();
                    break;
                case "5":
                    modifyPlaylist.createPlaylist(new Scanner(System.in));
                    System.out.println();
                    main.printMenu();
                    break;
                case "6":
                    System.out.println("==================");
                    System.out.println("Playlist modifying");
                    System.out.println("==================");
                    System.out.println("1. Add song to playlist");
                    System.out.println("2. View the playlist upon your choice");
                    System.out.println("3. Delete a playlist");
                    System.out.println("4. Go back to the main menu");
                    System.out.print("Your choice: ");
                    choice = in.next();
                    switch (choice){
                        case "1":
                            modifyPlaylist.addSongToPlaylist(new Scanner(System.in));
                            System.out.println();
                            break;
                        case "2":
                            System.out.print("The playlist you want to view: ");
                            Scanner userPick = new Scanner(System.in);
                            String play = userPick.nextLine();
                            modifyPlaylist.printUserPlaylist(play);
                            break;
                        case "3":
                            modifyPlaylist.deletePlaylist(new Scanner(System.in));
                            break;
                        case "4":
                            break;
                    }
                    main.printMenu();
                    break;
                case "7":
                    System.out.println("Thanks for using my music playlist");
                    done = true;
                    break;
                default:
                    System.out.println("Unable to proceed, please try again");
            }
        }
        System.out.println("Good bye");
        in.close();
    }
}