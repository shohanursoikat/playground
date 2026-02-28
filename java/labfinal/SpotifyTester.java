public class SpotifyTester {
public static void main(String[] args) {
Song s1 = new Song("Song-A", "Artist-A", 3);
System.out.println("1==========="); 

System.out.println("2===========");
Playlist p1 = new Playlist("First playlist");
System.out.println("3===========");
p1.addSong(s1);

Song s2 = new Song("Lose yourself", "Eminem", 3);
Song s3 = new Song("No love", "Eminem", 4);
Song s4 = new Song("offshore", "subha", 5);
Song s5 = new Song("The diaz brothers","ap",4);

p1.addSong(s2);
p1.addSong(s3);
p1.addSong(s4, 3);
p1.addSong(s5,2);
p1.deleteSong("No love");
p1.deleteSong("hele");
p1.info();
}
}
