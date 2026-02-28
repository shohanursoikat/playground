public class Song{
    public String title;
    public String artist;
    public int length;
    public Song(String t, String a, int l){
        title=t; 
        artist=a;
        length=l;
    }
    public void songInfo(){
        System.out.println("Title: " +title);
        System.out.println("Artist: "+artist);
        System.out.println("Length: "+ length);
    }
}