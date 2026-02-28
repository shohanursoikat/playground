public class Driver{
    
    public static void main(String[] args) {
        Song s1 = new Song("Title A", "Artist A", 3);
        Song s2 = new Song("Title B", "Artist B", 5);

        
        
        Playlist p1 = new Playlist("First playlist ");
        
        
        p1.addSong(s1);
        p1.info();

    }
}