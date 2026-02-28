public class Song{
    
     String title;
     String artist;
     int length;
     public Song(String ti, String art, int len){
      title = ti;
      artist = art;
      length = len;
     }
     void showInfo(){
        System.out.println("Title: " + title);
        System.out.println("Artist: " + artist);
        System.out.println("Length: " + length);
     }
    
}
    
    

